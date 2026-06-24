package com.localmart.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import com.localmart.dto.AISearchRequest;
import com.localmart.model.Shop; 
import com.localmart.repository.ShopRepository; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") 
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
            // 1. Grab all registered shops from MySQL
            List<Shop> allShops = shopRepository.findAll();
            
            List<Map<String, Object>> smallShopList = new ArrayList<>();
            for (Shop s : allShops) {
                // Using a regular HashMap instead of Map.of() to completely prevent NullPointerExceptions on missing values
                Map<String, Object> shopMap = new HashMap<>();
                shopMap.put("id", s.getId());
                shopMap.put("name", s.getName() != null ? s.getName() : "Unnamed Shop");
                shopMap.put("category", s.getMainCategory() != null ? s.getMainCategory() : "General");
                shopMap.put("description", s.getDescription() != null ? s.getDescription() : "");
                
                smallShopList.add(shopMap);
            }

            // 2. Draft strict system routing instructions for the AI model
            String prompt = String.format(
                "You are an intelligent marketplace assistant for LocalMart. The customer is typing: \"%s\".\n\n" +
                "Here is the database selection of active shops in JSON configuration:\n%s\n\n" +
                "Evaluate the customer's request and match it against the categories and descriptions. " +
                "Provide your response as a valid, flat JSON array containing ONLY the matching Integer database IDs (e.g., [2, 7]). " +
                "Do not format the JSON using markdown wrappers, blocks, text notes, or backticks like ```json.",
                userQuery, objectMapper.writeValueAsString(smallShopList)
            );

            // 3. Connect to Gemini using the Java client SDK
            Client client = new Client();
            GenerateContentResponse response = client.models.generateContent(
                "gemini-2.5-flash",
                prompt,
                null
            );

            String responseText = response.text().trim();

            // 4. Clean fallback safety check if markdown backticks leak into response text
            if (responseText.contains("```")) {
                responseText = responseText.replaceAll("```json|```", "").trim();
            }

            // 5. Transform the AI string text back into a standard Java List structure
            List<Long> matchedIds = objectMapper.readValue(responseText, new TypeReference<List<Long>>() {});

            if (matchedIds.isEmpty()) {
                return ResponseEntity.ok(Map.of("results", Collections.emptyList()));
            }

            // 6. Look up and resolve the full SQL objects from MySQL
            List<Shop> matchedShops = shopRepository.findAllById(matchedIds);

            return ResponseEntity.ok(Map.of("results", matchedShops));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(Map.of("error", "AI engine context lookup processing failure."));
        }
    }
}