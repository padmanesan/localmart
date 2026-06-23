package com.localmart.controller;

import com.localmart.model.Shop;
import com.localmart.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shops")
@CrossOrigin(origins = "*")
public class ShopController {

    @Autowired
    private ShopService shopService;

    // Register a new shop
    @PostMapping("/register")
    public Shop registerShop(@RequestBody Shop shop) {
        return shopService.registerShop(shop);
    }

    // Get all shops
    @GetMapping("/all")
    public List<Shop> getAllShops() {
        return shopService.getAllShops();
    }

    // Get shops by city
    @GetMapping("/city/{city}")
    public List<Shop> getShopsByCity(@PathVariable String city) {
        return shopService.getShopsByCity(city);
    }

    // Get shops by main category
    @GetMapping("/category/{mainCategory}")
    public List<Shop> getShopsByMainCategory(@PathVariable String mainCategory) {
        return shopService.getShopsByMainCategory(mainCategory);
    }

    // Get shops by main and sub category
    @GetMapping("/category/{mainCategory}/{subCategory}")
    public List<Shop> getShopsByMainAndSubCategory(
            @PathVariable String mainCategory,
            @PathVariable String subCategory) {
        return shopService.getShopsByMainAndSubCategory(mainCategory, subCategory);
    }

    // Get shops by city and main category
    @GetMapping("/city/{city}/category/{mainCategory}")
    public List<Shop> getShopsByCityAndMainCategory(
            @PathVariable String city,
            @PathVariable String mainCategory) {
        return shopService.getShopsByCityAndMainCategory(city, mainCategory);
    }

    // Search shops by name
    @GetMapping("/search")
    public List<Shop> searchShops(@RequestParam String query) {
        return shopService.searchShops(query);
    }

    // Get shops by district
    @GetMapping("/district/{district}")
    public List<Shop> getShopsByDistrict(@PathVariable String district) {
        return shopService.getShopsByDistrict(district);
    }
    // Get shop by ID
@GetMapping("/{id}")
public Shop getShopById(@PathVariable Long id) {
    return shopService.getShopById(id);
}
}