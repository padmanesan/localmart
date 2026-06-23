package com.localmart.service;

import com.localmart.model.Shop;
import com.localmart.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService {

    @Autowired
    private ShopRepository shopRepository;

    // Register a new shop
    public Shop registerShop(Shop shop) {
        return shopRepository.save(shop);
    }

    // Get all shops
    public List<Shop> getAllShops() {
        return shopRepository.findAll();
    }

    // Get shops by city
    public List<Shop> getShopsByCity(String city) {
        return shopRepository.findByCity(city);
    }

    // Get shops by main category
    public List<Shop> getShopsByMainCategory(String mainCategory) {
        return shopRepository.findByMainCategory(mainCategory);
    }

    // Get shops by main and sub category
    public List<Shop> getShopsByMainAndSubCategory(String mainCategory, String subCategory) {
        return shopRepository.findByMainCategoryAndSubCategory(mainCategory, subCategory);
    }

    // Get shops by city and main category
    public List<Shop> getShopsByCityAndMainCategory(String city, String mainCategory) {
        return shopRepository.findByCityAndMainCategory(city, mainCategory);
    }

    // Search shops by name
    public List<Shop> searchShops(String query) {
        return shopRepository.findByNameContainingIgnoreCase(query);
    }

    // Get shops by district
    public List<Shop> getShopsByDistrict(String district) {
        return shopRepository.findByDistrict(district);
    }
}