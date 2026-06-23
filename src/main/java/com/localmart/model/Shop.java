package com.localmart.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "shops")
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Basic Info
    private String name;
    private String ownerName;
    private String phone;
    private String address;
    private String mainCategory;
    private String subCategory;
    private String description;

    // Location Info (New!)
    private String city;
    private String district;
    private String state;
    private String pincode;
    private Double latitude;
    private Double longitude;

    // Extra Info (New!)
    private String openTime;
    private String closeTime;
    private Double rating;
    private Integer totalReviews;
    private Boolean isOpen;
}