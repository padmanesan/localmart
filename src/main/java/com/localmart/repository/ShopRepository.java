package com.localmart.repository;

import com.localmart.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {

    // Find shops by city
    List<Shop> findByCity(String city);

    List<Shop> findByMainCategory(String mainCategory);
List<Shop> findByMainCategoryAndSubCategory(String mainCategory, String subCategory);
List<Shop> findByCityAndMainCategory(String city, String mainCategory);

    // Search shops by name
    List<Shop> findByNameContainingIgnoreCase(String name);

    // Find shops by district
    List<Shop> findByDistrict(String district);
}