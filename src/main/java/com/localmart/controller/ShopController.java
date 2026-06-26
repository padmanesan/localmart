package com.localmart.controller;

import com.localmart.model.Shop;
import com.localmart.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/shops")
@CrossOrigin(origins = "*")
public class ShopController {

    @Autowired
    private ShopService shopService;

    // ── Register a new shop ──────────────────────────────────────────────
    @PostMapping("/register")
    public Shop registerShop(@RequestBody Shop shop) {
        return shopService.registerShop(shop);
    }

    // ── Get all shops ────────────────────────────────────────────────────
    @GetMapping("/all")
    public List<Shop> getAllShops() {
        return shopService.getAllShops();
    }

    // ── MAIN: Get nearby shops based on user's GPS location ──────────────
    // Returns: { shops: [...], locationBased: true/false, city: "Chennai", message: "..." }
    // Frontend reads response.data.shops and response.data.city
    @GetMapping("/nearby")
    public ResponseEntity<Map<String, Object>> getNearbyShops(
            @RequestParam double lat,
            @RequestParam double lng) {

        List<Shop> nearbyShops = shopService.getNearbyShops(lat, lng);

        Map<String, Object> response = new HashMap<>();
        response.put("shops", nearbyShops);

        if (!nearbyShops.isEmpty()) {
            // Grab the city from the closest shop (first in list, already sorted by distance)
            String detectedCity = nearbyShops.get(0).getCity();
            response.put("locationBased", true);
            response.put("city", detectedCity);
            response.put("message", "Showing shops near you in " + detectedCity);
        } else {
            response.put("locationBased", false);
            response.put("city", "");
            response.put("message", "Showing all shops");
        }

        return ResponseEntity.ok(response);
    }

    // ── Nearby shops filtered by category ────────────────────────────────
    // e.g. GET /api/shops/nearby/category?lat=13.08&lng=80.27&category=Gym
    @GetMapping("/nearby/category")
    public ResponseEntity<Map<String, Object>> getNearbyShopsByCategory(
            @RequestParam double lat,
            @RequestParam double lng,
            @RequestParam String category) {

        List<Shop> shops = shopService.getNearbyShopsByCategory(lat, lng, category);

        Map<String, Object> response = new HashMap<>();
        response.put("shops", shops);
        response.put("category", category);
        response.put("count", shops.size());

        return ResponseEntity.ok(response);
    }

    // ── Other existing endpoints (unchanged) ─────────────────────────────
    @GetMapping("/city/{city}")
    public List<Shop> getShopsByCity(@PathVariable String city) {
        return shopService.getShopsByCity(city);
    }

    @GetMapping("/category/{mainCategory}")
    public List<Shop> getShopsByMainCategory(@PathVariable String mainCategory) {
        return shopService.getShopsByMainCategory(mainCategory);
    }

    @GetMapping("/category/{mainCategory}/{subCategory}")
    public List<Shop> getShopsByMainAndSubCategory(
            @PathVariable String mainCategory,
            @PathVariable String subCategory) {
        return shopService.getShopsByMainAndSubCategory(mainCategory, subCategory);
    }

    @GetMapping("/city/{city}/category/{mainCategory}")
    public List<Shop> getShopsByCityAndMainCategory(
            @PathVariable String city,
            @PathVariable String mainCategory) {
        return shopService.getShopsByCityAndMainCategory(city, mainCategory);
    }

    @GetMapping("/search")
    public List<Shop> searchShops(@RequestParam String query) {
        return shopService.searchShops(query);
    }

    @GetMapping("/district/{district}")
    public List<Shop> getShopsByDistrict(@PathVariable String district) {
        return shopService.getShopsByDistrict(district);
    }

    @GetMapping("/{id}")
    public Shop getShopById(@PathVariable Long id) {
        return shopService.getShopById(id);
    }

    @DeleteMapping("/clear-all-duplicates-reset")
    public String clearAndResetTable() {
        shopService.clearAllShops();
        return "Database cleared!";
    }
}