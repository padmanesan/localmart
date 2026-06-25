package com.localmart.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.HashMap;

@RestController
@CrossOrigin(origins = "*") // Allows your Vercel frontend to connect smoothly
public class AISearchController {

    // Assuming you have your Gemini Service injected here
    // @Autowired 
    // private GeminiService geminiService;

    @PostMapping("/api/ai-search")
    public ResponseEntity<?> aiSearch(@RequestBody Map<String, String> request) {
        String query = request.get("query");
        Map<String, Object> response = new HashMap<>();

        try {
            // 1. Try to process the search query via your Gemini service
            // String aiResult = geminiService.generate(query);
            // return ResponseEntity.ok(aiResult);
            
            // NOTE: Keep your exact existing Gemini calling code here, 
            // just make sure it sits inside this 'try' block!
            
            return ResponseEntity.ok("Your working Gemini response data here");

        } catch (Exception e) {
            // 2. If Gemini rate limits or times out, catch it here cleanly!
            System.err.println("Gemini Integration Warning: " + e.getMessage());
            
            response.put("success", false);
            response.put("isRateLimited", true);
            response.put("message", "Gemini AI is currently processing multiple requests. Please wait 10-15 seconds and try searching again!");
            
            // Returning HTTP 200 OK with an error message payload so your frontend doesn't crash!
            return ResponseEntity.ok(response);
        }
    }
}