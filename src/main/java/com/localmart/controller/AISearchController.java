package com.localmart.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import com.localmart.dto.AISearchRequest;
import com.localmart.model.Shop; // Ensure this matches your exact entity package package path
import com.localmart.repository.ShopRepository; // Ensure this matches your exact repository package path
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // Allows clean CORS access for local & live React builds
public class AISearchController {

    @Autowired
    private ShopRepository shopRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/ai-search")
    public ResponseEntity<?> handleAISearch(@RequestBody AISearchRequest request) {
        String userQuery = request.getQuery();
        if (userQuery == null || userQuery.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Search query cannot be blank"));
        }

        try {
            // 1. Grab all registered shops from MySQL to feed as contextual options to Gemini
            List<Shop> allShops = shopRepository.findAll();
            
            List<Map<String, Object>> smallShopList = new ArrayList<>();
            for (Shop s : allShops) {
                smallShopList.add(Map.of(
                    "id", s.getId(),
                    "name", s.getName(),
                    "category", s.getCategory() != null ? s.getCategory() : "General",
                    "description", s.getDescription() != null ? s.getDescription() : ""
                ));
            }

            // 2. Draft strict system routing instructions for the AI model mapping layer
            String prompt = String.format(
                "You are an intelligent marketplace assistant for LocalMart. The customer is typing: \"%s\".\n\n" +
                "Here is the database selection of active shops in JSON configuration:\n%s\n\n" +
                "Evaluate the customer's request and match it against the categories and descriptions. " +
                "Provide your response as a valid, flat JSON array containing ONLY the matching Integer database IDs (e.g., [2, 7]). " +
                "Do not format the JSON using markdown wrappers, blocks, text notes, or backticks like ```json.",
                userQuery, objectMapper.writeValueAsString(smallShopList)
            );

            // 3. Connect to Gemini 2.5 Flash using the Java client SDK
            // Note: The client will automatically discover your system's GOOGLE_API_KEY environment variable.
            Client client = new Client();
            GenerateContentResponse response = client.models.generateContent(
                "gemini-2.5-flash",
                prompt,
                null
            );

            String responseText = response.text().trim();

            // 4. Clean fallback safety check if markdown backticks leak into response text anyway
            if (responseText.contains("```")) {
                responseText = responseText.replaceAll("```json|```", "").trim();
            }

            // 5. Transform the AI string text back into a standard Java List structure
            List<Long> matchedIds = objectMapper.readValue(responseText, new TypeReference<List<Long>>() {});

            if (matchedIds.isEmpty()) {
                return ResponseEntity.ok(Map.of("results", Collections.emptyList()));
            }

            // 6. Look up and resolve the full SQL objects from MySQL to return straight to React
            List<Shop> matchedShops = shopRepository.findAllById(matchedIds);

            return ResponseEntity.ok(Map.of("results", matchedShops));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(Map.of("error", "AI engine context lookup processing failure."));
        }
    }
}