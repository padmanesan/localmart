package com.localmart.controller;

import com.localmart.model.Shop;
import com.localmart.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

@RestController
@CrossOrigin(origins = "*")
public class AISearchController {

    @Autowired 
    private ShopService shopService;

    @PostMapping("/api/ai-search")
    public ResponseEntity<?> aiSearch(@RequestBody Map<String, String> request) {
        String query = request.get("query");
        if (query == null || query.trim().isEmpty()) {
            return ResponseEntity.ok(new ArrayList<>());
        }

        try {
            String lowerQuery = query.toLowerCase();
            List<Shop> allShops = shopService.getAllShops();
            List<Shop> matchedShops = new ArrayList<>();

            // AI Smart Search Matching Engine
            for (Shop shop : allShops) {
                boolean matches = false;

                // 1. Match by name
                if (shop.getName() != null && shop.getName().toLowerCase().contains(lowerQuery)) {
                    matches = true;
                }
                // 2. Match by categories (e.g. food, drinks, shopping, wellness)
                else if (shop.getMainCategory() != null && shop.getMainCategory().toLowerCase().contains(lowerQuery)) {
                    matches = true;
                }
                else if (shop.getSubCategory() != null && shop.getSubCategory().toLowerCase().contains(lowerQuery)) {
                    matches = true;
                }
                // 3. Match by keywords in description (e.g., "biriyani", "furniture", "sofa", "pharmacy")
                else if (shop.getDescription() != null && shop.getDescription().toLowerCase().contains(lowerQuery)) {
                    matches = true;
                }

                if (matches) {
                    matchedShops.add(shop);
                }
            }

            // Return the matching items to your frontend card component
            return ResponseEntity.ok(matchedShops);

        } catch (Exception e) {
            System.err.println("AI Search Processing Error: " + e.getMessage());
            return ResponseEntity.status(500).body("Error processing search intent");
        }
    }
}   