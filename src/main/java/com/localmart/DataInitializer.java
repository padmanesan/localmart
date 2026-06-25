package com.localmart;

import com.localmart.model.Shop;
import com.localmart.repository.ShopRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ShopRepository shopRepository;

    public DataInitializer(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // 1. WIPE THE TABLE CLEAN TO REMOVE DUPLICATES
        System.out.println("🧹 Clearing old shop data to fix duplicates...");
        shopRepository.deleteAll();
        
        // 2. INSERT EXACTLY ONE CLEAN COPY OF EACH SHOP
        System.out.println(" Seeding database with a fresh, clean list of local shops...");

        // Shop 1: Venkateswara Wood Works (Single, Clean Copy)
        Shop shop1 = new Shop();
        shop1.setName("Venkateswara Wood Works");
        shop1.setOwnerName("Ramesh Kumar");
        shop1.setPhone("9876543210");
        shop1.setAddress("10, C VOC Nagar, Dharapuram Road");
        shop1.setMainCategory("Home & Living");
        shop1.setSubCategory("Furniture");
        shop1.setDescription("Premium quality wooden furniture, custom sofas, tables, and interior home woodworking.");
        shop1.setCity("Oddanchatram");
        shop1.setDistrict("Dindigul");
        shop1.setState("Tamil Nadu");
        shop1.setPincode("624619");
        shop1.setLatitude(10.4851);
        shop1.setLongitude(77.7465);
        shop1.setOpenTime("09:00 AM");
        shop1.setCloseTime("08:00 PM");
        shop1.setRating(4.5);
        shop1.setTotalReviews(24);
        shop1.setIsOpen(true);
        shopRepository.save(shop1);

        // Shop 2: Sri Lakshmi Medicals
        Shop shop2 = new Shop();
        shop2.setName("Sri Lakshmi Medicals");
        shop2.setOwnerName("Dr. S. Anand");
        shop2.setPhone("9876543211");
        shop2.setAddress("42, Palani Main Road");
        shop2.setMainCategory("Health & Wellness");
        shop2.setSubCategory("Medical");
        shop2.setDescription("24/7 retail pharmacy offering prescription medicines, baby care products, and wellness checks.");
        shop2.setCity("Oddanchatram");
        shop2.setDistrict("Dindigul");
        shop2.setState("Tamil Nadu");
        shop2.setPincode("624619");
        shop2.setLatitude(10.4870);
        shop2.setLongitude(77.7430);
        shop2.setOpenTime("12:00 AM");
        shop2.setCloseTime("11:59 PM");
        shop2.setRating(4.8);
        shop2.setTotalReviews(112);
        shop2.setIsOpen(true);
        shopRepository.save(shop2);

        // Shop 3: Namma Ooru Biriyani
        Shop shop3 = new Shop();
        shop3.setName("Namma Ooru Biriyani");
        shop3.setOwnerName("M. Selvam");
        shop3.setPhone("9876543212");
        shop3.setAddress("15, Bye Pass Road");
        shop3.setMainCategory("Food & Drinks");
        shop3.setSubCategory("Restaurants");
        shop3.setDescription("Authentic local wood-fired chicken and mutton biriyani served with traditional sides.");
        shop3.setCity("Oddanchatram");
        shop3.setDistrict("Dindigul");
        shop3.setState("Tamil Nadu");
        shop3.setPincode("624619");
        shop3.setLatitude(10.4812);
        shop3.setLongitude(77.7490);
        shop3.setOpenTime("11:00 AM");
        shop3.setCloseTime("10:00 PM");
        shop3.setRating(4.3);
        shop3.setTotalReviews(85);
        shop3.setIsOpen(true);
        shopRepository.save(shop3);

        // Shop 4: Trendz Boutique
        Shop shop4 = new Shop();
        shop4.setName("Trendz Boutique");
        shop4.setOwnerName("Priya Dharshini");
        shop4.setPhone("9876543213");
        shop4.setAddress("5, Car Street");
        shop4.setMainCategory("Shopping");
        shop4.setSubCategory("Clothes");
        shop4.setDescription("Latest ethnic wear, designer sarees, modern western outfits, and custom bridal alterations.");
        shop4.setCity("Oddanchatram");
        shop4.setDistrict("Dindigul");
        shop4.setState("Tamil Nadu");
        shop4.setPincode("624619");
        shop4.setLatitude(10.4866);
        shop4.setLongitude(77.7444);
        shop4.setOpenTime("10:00 AM");
        shop4.setCloseTime("09:00 PM");
        shop4.setRating(4.6);
        shop4.setTotalReviews(42);
        shop4.setIsOpen(true);
        shopRepository.save(shop4);
        
        System.out.println(" Success: Database cleared and reset with 4 unique shop listings!");
    }
}