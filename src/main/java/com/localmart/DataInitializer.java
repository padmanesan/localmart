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
        System.out.println("🧹 Clearing old duplicate shop data...");
        shopRepository.deleteAll();
        
        System.out.println("🌱 Seeding database with geographically diverse shops...");

        // 1. Oddanchatram/Dindigul Area Shop
        Shop shop1 = new Shop();
        shop1.setName("Venkateswara Wood Works");
        shop1.setOwnerName("Ramesh Kumar");
        shop1.setPhone("9876543210");
        shop1.setAddress("10, C VOC Nagar, Dharapuram Road");
        shop1.setMainCategory("Home & Living");
        shop1.setSubCategory("Furniture");
        shop1.setDescription("Premium quality wooden furniture, custom sofas, tables, and interior woodworking.");
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

        // 2. Chennai Area Shop
        Shop shop2 = new Shop();
        shop2.setName("Chennai Mega Grocery Mart");
        shop2.setOwnerName("S. Krishnan");
        shop2.setPhone("9876543299");
        shop2.setAddress("Ayaravadi Street, T-Nagar");
        shop2.setMainCategory("Food & Drinks");
        shop2.setSubCategory("Grocery");
        shop2.setDescription("Complete supermarket for organic foods, imports, household staples, and daily essentials.");
        shop2.setCity("Chennai");
        shop2.setDistrict("Chennai");
        shop2.setState("Tamil Nadu");
        shop2.setPincode("600017");
        shop2.setLatitude(13.0418); // Chennai Latitude
        shop2.setLongitude(80.2341); // Chennai Longitude
        shop2.setOpenTime("07:00 AM");
        shop2.setCloseTime("10:00 PM");
        shop2.setRating(4.7);
        shop2.setTotalReviews(310);
        shop2.setIsOpen(true);
        shopRepository.save(shop2);

        // 3. Oddanchatram Area Restaurant
        Shop shop3 = new Shop();
        shop3.setName("Namma Ooru Biriyani");
        shop3.setOwnerName("M. Selvam");
        shop3.setPhone("9876543212");
        shop3.setAddress("15, Bye Pass Road");
        shop3.setMainCategory("Food & Drinks");
        shop3.setSubCategory("Restaurants");
        shop3.setDescription("Authentic local wood-fired chicken and mutton biriyani.");
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

        System.out.println("✨ Success: Database cleared and reset with regional samples!");
    }
}