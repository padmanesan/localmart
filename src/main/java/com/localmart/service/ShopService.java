package com.localmart.service;

import com.localmart.model.Shop;
import com.localmart.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    // LOCATION LOGIC: Sort shops by proximity to the user's coordinates
    public List<Shop> getNearbyShops(double userLat, double userLng) {
        List<Shop> allShops = shopRepository.findAll();

        return allShops.stream()
            .sorted((shop1, shop2) -> {
                // Safeguard against null data coordinates in the database
                double lat1 = shop1.getLatitude() != null ? shop1.getLatitude() : 0.0;
                double lng1 = shop1.getLongitude() != null ? shop1.getLongitude() : 0.0;
                
                double lat2 = shop2.getLatitude() != null ? shop2.getLatitude() : 0.0;
                double lng2 = shop2.getLongitude() != null ? shop2.getLongitude() : 0.0;

                double dist1 = calculateDistance(userLat, userLng, lat1, lng1);
                double dist2 = calculateDistance(userLat, userLng, lat2, lng2);
                return Double.compare(dist1, dist2);
            })
            .collect(Collectors.toList());
    }

    // Haversine Formula to calculate distance between two coordinates in Kilometers
    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Radius of the earth in km
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
                
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c; 
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

    // Get shop by ID
    public Shop getShopById(Long id) {
        return shopRepository.findById(id).orElse(null);
    }

    // EMERGENCY RESET: Clears the entire table
    public void clearAllShops() {
        shopRepository.deleteAll();
    }
}