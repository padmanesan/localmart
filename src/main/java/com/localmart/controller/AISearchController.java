package com.localmart.controller;

import com.localmart.model.Shop;
import com.localmart.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
            // Split query into individual words — "biriyani in trichy" → ["biriyani", "trichy"]
            String[] keywords = query.toLowerCase().trim().split("\\s+");

            List<Shop> allShops = shopService.getAllShops();
            List<Shop> matchedShops = new ArrayList<>();

            for (Shop shop : allShops) {
                String name        = (shop.getName()        != null ? shop.getName()        : "").toLowerCase();
                String mainCat     = (shop.getMainCategory() != null ? shop.getMainCategory() : "").toLowerCase();
                String subCat      = (shop.getSubCategory()  != null ? shop.getSubCategory()  : "").toLowerCase();
                String description = (shop.getDescription()  != null ? shop.getDescription()  : "").toLowerCase();
                String city        = (shop.getCity()         != null ? shop.getCity()         : "").toLowerCase();
                String district    = (shop.getDistrict()     != null ? shop.getDistrict()     : "").toLowerCase();
                String address     = (shop.getAddress()      != null ? shop.getAddress()      : "").toLowerCase();

                // Combined searchable text for this shop
                String combined = name + " " + mainCat + " " + subCat + " "
                                + description + " " + city + " " + district + " " + address;

                // Shop matches if ANY keyword matches something in the combined text
                boolean matches = false;
                for (String keyword : keywords) {
                    // Skip filler words
                    if (keyword.equals("in") || keyword.equals("near") ||
                        keyword.equals("at") || keyword.equals("the")  ||
                        keyword.equals("a")  || keyword.equals("an")   ||
                        keyword.equals("me") || keyword.equals("find") ||
                        keyword.equals("show") || keyword.equals("get")) {
                        continue;
                    }
                    if (combined.contains(keyword)) {
                        matches = true;
                        break;
                    }
                }

                if (matches) {
                    matchedShops.add(shop);
                }
            }

            return ResponseEntity.ok(matchedShops);

        } catch (Exception e) {
            System.err.println("AI Search Error: " + e.getMessage());
            return ResponseEntity.status(500).body("Search error");
        }
    }
}