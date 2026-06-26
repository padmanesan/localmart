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

    // Nearby radius in KM — shops beyond this are NOT shown to the user
    private static final double NEARBY_RADIUS_KM = 25.0;

    // ─── Register ───────────────────────────────────────────────────────
    public Shop registerShop(Shop shop) {
        return shopRepository.save(shop);
    }

    // ─── All shops (used by AI search as the pool) ───────────────────────
    public List<Shop> getAllShops() {
        return shopRepository.findAll();
    }

    // ─── MAIN LOCATION LOGIC ─────────────────────────────────────────────
    // Returns only shops within NEARBY_RADIUS_KM of the user, sorted nearest first.
    // If zero shops found within radius → returns all shops as fallback.
    public List<Shop> getNearbyShops(double userLat, double userLng) {
        List<Shop> allShops = shopRepository.findAll();

        List<Shop> nearbyShops = allShops.stream()
            .filter(shop -> {
                if (shop.getLatitude() == null || shop.getLongitude() == null) return false;
                if (shop.getLatitude() == 0.0 && shop.getLongitude() == 0.0) return false;
                return calculateDistance(userLat, userLng,
                        shop.getLatitude(), shop.getLongitude()) <= NEARBY_RADIUS_KM;
            })
            .sorted((s1, s2) -> {
                double d1 = calculateDistance(userLat, userLng, s1.getLatitude(), s1.getLongitude());
                double d2 = calculateDistance(userLat, userLng, s2.getLatitude(), s2.getLongitude());
                return Double.compare(d1, d2);
            })
            .collect(Collectors.toList());

        // Fallback: if no shops are found nearby, return all (so screen is never empty)
        return nearbyShops.isEmpty() ? allShops : nearbyShops;
    }

    // ─── NEARBY + CATEGORY FILTER ─────────────────────────────────────────
    // Used when user clicks a category tab (e.g. "Gym", "Restaurant")
    // Only shows shops from the user's area AND that category.
    public List<Shop> getNearbyShopsByCategory(double userLat, double userLng, String category) {
        return getNearbyShops(userLat, userLng).stream()
            .filter(shop -> {
                String main = shop.getMainCategory() != null ? shop.getMainCategory().toLowerCase() : "";
                String sub  = shop.getSubCategory()  != null ? shop.getSubCategory().toLowerCase()  : "";
                String cat  = category.toLowerCase();
                return main.contains(cat) || sub.contains(cat);
            })
            .collect(Collectors.toList());
    }

    // ─── Haversine formula ────────────────────────────────────────────────
    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                 + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                 * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        return R * 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    }

    // ─── Other existing methods (unchanged) ───────────────────────────────
    public List<Shop> getShopsByCity(String city) {
        return shopRepository.findByCity(city);
    }

    public List<Shop> getShopsByMainCategory(String mainCategory) {
        return shopRepository.findByMainCategory(mainCategory);
    }

    public List<Shop> getShopsByMainAndSubCategory(String mainCategory, String subCategory) {
        return shopRepository.findByMainCategoryAndSubCategory(mainCategory, subCategory);
    }

    public List<Shop> getShopsByCityAndMainCategory(String city, String mainCategory) {
        return shopRepository.findByCityAndMainCategory(city, mainCategory);
    }

    public List<Shop> searchShops(String query) {
        return shopRepository.findByNameContainingIgnoreCase(query);
    }

    public List<Shop> getShopsByDistrict(String district) {
        return shopRepository.findByDistrict(district);
    }

    public Shop getShopById(Long id) {
        return shopRepository.findById(id).orElse(null);
    }

    public void clearAllShops() {
        shopRepository.deleteAll();
    }
}