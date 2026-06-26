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

        // Only seed if the database is empty — prevents wiping on every restart
        if (shopRepository.count() > 0) {
            System.out.println("✅ Database already has shops. Skipping seed.");
            return;
        }

        System.out.println("🌱 Seeding database with shops across Tamil Nadu...");

        // ─────────────────────────────────────────────────────────────────
        // CHENNAI SHOPS (lat ~13.08, lng ~80.27)
        // ─────────────────────────────────────────────────────────────────

        // Food & Drinks
        saveShop("Murugan Idli Shop",        "Annamalai", "9876500001",
                 "Usman Road, T Nagar, Chennai",
                 "Food & Drinks", "Restaurant",
                 "Chennai's most famous soft idli and crispy dosa. Served with 5 types of chutney.",
                 "Chennai", "Chennai", "600017", 13.0418, 80.2341,
                 "06:00 AM", "11:00 PM", 4.8, 420);

        saveShop("Hotel Saravana Bhavan",    "Suresh",    "9876500002",
                 "Nelson Manickam Road, Chennai",
                 "Food & Drinks", "Restaurant",
                 "Legendary vegetarian meals and tiffin. Must try: pongal and vada.",
                 "Chennai", "Chennai", "600029", 13.0600, 80.2430,
                 "07:00 AM", "11:00 PM", 4.7, 890);

        saveShop("Anjappar Chettinad",       "Rajan",     "9876500003",
                 "Anna Salai, Chennai",
                 "Food & Drinks", "Restaurant",
                 "Authentic Chettinad non-veg meals. Famous for pepper chicken and kal dosa.",
                 "Chennai", "Chennai", "600002", 13.0550, 80.2590,
                 "11:00 AM", "11:00 PM", 4.5, 310);

        saveShop("Amudham Bakery",           "Lakshmi",   "9876500004",
                 "Mylapore, Chennai",
                 "Food & Drinks", "Bakery",
                 "Fresh bread, cakes, and traditional sweets. Open since 1985.",
                 "Chennai", "Chennai", "600004", 13.0335, 80.2690,
                 "07:00 AM", "09:00 PM", 4.3, 145);

        saveShop("Chennai Juice Corner",     "Mani",      "9876500005",
                 "Velachery Main Road, Chennai",
                 "Food & Drinks", "Juice Shop",
                 "Fresh sugarcane, fruit juices, and tender coconut. No artificial flavors.",
                 "Chennai", "Chennai", "600042", 12.9815, 80.2176,
                 "08:00 AM", "10:00 PM", 4.2, 98);

        // Shopping
        saveShop("Saravana Stores",          "Saravanan", "9876500006",
                 "Pondy Bazaar, T Nagar, Chennai",
                 "Shopping", "Clothing",
                 "Chennai's biggest clothing destination. Sarees, salwars, and designer wear at best prices.",
                 "Chennai", "Chennai", "600017", 13.0450, 80.2399,
                 "09:00 AM", "10:00 PM", 4.6, 1200);

        saveShop("Viveks Electronics",       "Vivek",     "9876500007",
                 "Anna Salai, Chennai",
                 "Shopping", "Electronics",
                 "Trusted electronics store. TVs, fridges, ACs, and mobile phones with best after-sale service.",
                 "Chennai", "Chennai", "600002", 13.0600, 80.2490,
                 "09:00 AM", "09:00 PM", 4.5, 567);

        saveShop("Chennai Book Centre",      "Priya",     "9876500008",
                 "Mount Road, Chennai",
                 "Shopping", "Books",
                 "Largest book shop in South India. Tamil, English, competitive exam books and stationery.",
                 "Chennai", "Chennai", "600002", 13.0620, 80.2510,
                 "09:00 AM", "08:00 PM", 4.4, 230);

        // Health & Wellness
        saveShop("Apollo Pharmacy",          "Arun",      "9876500009",
                 "Anna Nagar, Chennai",
                 "Health & Wellness", "Pharmacy",
                 "24/7 pharmacy. All medicines, health supplements, and home medical equipment.",
                 "Chennai", "Chennai", "600040", 13.0850, 80.2101,
                 "12:00 AM", "11:59 PM", 4.7, 890);

        saveShop("FitZone Gym",              "Karthik",   "9876500010",
                 "Adyar, Chennai",
                 "Health & Wellness", "Gym",
                 "Fully equipped gym with personal trainers. Cardio, weights, Zumba, and yoga classes.",
                 "Chennai", "Chennai", "600020", 13.0012, 80.2565,
                 "05:00 AM", "11:00 PM", 4.6, 180);

        saveShop("Sooriya Hospital",         "Dr. Priya", "9876500011",
                 "Vadapalani, Chennai",
                 "Health & Wellness", "Hospital",
                 "Multi-speciality hospital. 24/7 emergency. Expert doctors in all departments.",
                 "Chennai", "Chennai", "600026", 13.0510, 80.2120,
                 "12:00 AM", "11:59 PM", 4.5, 340);

        // Home & Living
        saveShop("Wood Castle Furniture",    "Pandian",   "9876500012",
                 "Poonamallee, Chennai",
                 "Home & Living", "Furniture",
                 "Custom wooden furniture factory outlet. Beds, wardrobes, dining sets at factory price.",
                 "Chennai", "Chennai", "600056", 13.0474, 80.1012,
                 "09:00 AM", "08:00 PM", 4.3, 95);

        saveShop("Decor Home Chennai",       "Meena",     "9876500013",
                 "Porur, Chennai",
                 "Home & Living", "Home Decor",
                 "Wall art, curtains, bedsheets, and home accessories. Transform your home beautifully.",
                 "Chennai", "Chennai", "600116", 13.0368, 80.1573,
                 "10:00 AM", "08:00 PM", 4.2, 67);

        // Education & Services
        saveShop("ZICA Animation Academy",   "Raj",       "9876500014",
                 "Nungambakkam, Chennai",
                 "Education & Services", "Training Institute",
                 "Animation, graphic design, VFX, and multimedia courses. Job placement support.",
                 "Chennai", "Chennai", "600034", 13.0587, 80.2435,
                 "09:00 AM", "07:00 PM", 4.4, 112);

        saveShop("Speed Wash Laundry",       "Thomas",    "9876500015",
                 "Perungudi, Chennai",
                 "Education & Services", "Laundry",
                 "Express dry cleaning and laundry. Pickup and delivery in 24 hours.",
                 "Chennai", "Chennai", "600096", 12.9677, 80.2452,
                 "07:00 AM", "09:00 PM", 4.1, 78);

        // Transport & Vehicles
        saveShop("Raja Motors",              "Raja",      "9876500016",
                 "Ambattur, Chennai",
                 "Transport & Vehicles", "Bike Service",
                 "Hero and Bajaj authorized service center. Quick service, genuine spare parts.",
                 "Chennai", "Chennai", "600053", 13.1143, 80.1548,
                 "08:00 AM", "07:00 PM", 4.3, 145);

        saveShop("Chennai Car Rentals",      "Selvam",    "9876500017",
                 "Egmore, Chennai",
                 "Transport & Vehicles", "Car Rental",
                 "Self-drive and chauffeur cars. Airport pickup, outstation, and local packages.",
                 "Chennai", "Chennai", "600008", 13.0738, 80.2614,
                 "06:00 AM", "11:00 PM", 4.5, 203);

        // Grocery Supermarket
        saveShop("Nilgiris Supermarket",     "Gopal",     "9876500018",
                 "Besant Nagar, Chennai",
                 "Food & Drinks", "Supermarket",
                 "Premium supermarket. Imported goods, organic produce, dairy, and bakery section.",
                 "Chennai", "Chennai", "600090", 12.9989, 80.2707,
                 "08:00 AM", "10:00 PM", 4.6, 430);

        saveShop("More Supermarket",         "Anitha",    "9876500019",
                 "Anna Nagar West, Chennai",
                 "Food & Drinks", "Supermarket",
                 "Budget-friendly daily groceries. Fresh vegetables, fruits, and household needs.",
                 "Chennai", "Chennai", "600040", 13.0911, 80.1959,
                 "08:00 AM", "10:00 PM", 4.2, 310);

        // Hotel / Hostel
        saveShop("Bloom Inn Chennai",        "Manager",   "9876500020",
                 "Koyambedu, Chennai",
                 "Education & Services", "Hotel",
                 "Budget hotel near bus stand. Clean rooms, free WiFi, AC and non-AC options.",
                 "Chennai", "Chennai", "600107", 13.0694, 80.1948,
                 "12:00 AM", "11:59 PM", 3.9, 189);

        // ─────────────────────────────────────────────────────────────────
        // TRICHY SHOPS (lat ~10.79, lng ~78.70)
        // ─────────────────────────────────────────────────────────────────

        saveShop("Ponni Hotel & Mess",       "Kannan",    "9876500021",
                 "Chathiram Bus Stand, Trichy",
                 "Food & Drinks", "Restaurant",
                 "Unlimited thali meals with fresh rasam and sambhar. Best value in Trichy.",
                 "Trichy", "Tiruchirappalli", "620001", 10.8050, 78.6856,
                 "07:00 AM", "10:00 PM", 4.4, 210);

        saveShop("Buhari Restaurant Trichy", "Jabir",     "9876500022",
                 "Junction Road, Trichy",
                 "Food & Drinks", "Restaurant",
                 "Famous for Trichy-style biriyani. Mutton, chicken, and seeraga samba rice.",
                 "Trichy", "Tiruchirappalli", "620001", 10.7969, 78.6895,
                 "11:00 AM", "11:00 PM", 4.6, 390);

        saveShop("Trichy Fancy Stores",      "Devi",      "9876500023",
                 "Big Bazaar Street, Trichy",
                 "Shopping", "Clothing",
                 "Sarees, dress materials, and readymade garments. Best quality at wholesale price.",
                 "Trichy", "Tiruchirappalli", "620001", 10.7959, 78.6940,
                 "09:00 AM", "09:00 PM", 4.3, 167);

        saveShop("Rockfort Medical Hall",    "Ganesh",    "9876500024",
                 "Rockfort Road, Trichy",
                 "Health & Wellness", "Pharmacy",
                 "All medicines available. Home delivery for senior citizens. Open 24 hours.",
                 "Trichy", "Tiruchirappalli", "620002", 10.8148, 78.6928,
                 "12:00 AM", "11:59 PM", 4.5, 220);

        saveShop("Iron Gym Trichy",          "Prabhu",    "9876500025",
                 "Thillai Nagar, Trichy",
                 "Health & Wellness", "Gym",
                 "Best gym in Trichy with modern equipment, personal training, and diet counseling.",
                 "Trichy", "Tiruchirappalli", "620018", 10.8101, 78.6921,
                 "05:30 AM", "10:00 PM", 4.5, 134);

        saveShop("Sri Renganatha Sweets",    "Murali",    "9876500026",
                 "Srirangam, Trichy",
                 "Food & Drinks", "Bakery",
                 "Temple town famous sweets. Mysore pak, halwa, and traditional snacks.",
                 "Trichy", "Tiruchirappalli", "620006", 10.8651, 78.6942,
                 "07:00 AM", "09:00 PM", 4.6, 298);

        saveShop("Raja Electronics Trichy",  "Kumar",     "9876500027",
                 "Main Guard Gate, Trichy",
                 "Shopping", "Electronics",
                 "Mobile phones, laptops, and home appliances. Service center for all brands.",
                 "Trichy", "Tiruchirappalli", "620001", 10.7920, 78.7015,
                 "09:00 AM", "09:00 PM", 4.3, 188);

        saveShop("Trichy Supermarket",       "Vijay",     "9876500028",
                 "Thennur, Trichy",
                 "Food & Drinks", "Supermarket",
                 "Everything under one roof. Fresh produce, groceries, and household products.",
                 "Trichy", "Tiruchirappalli", "620017", 10.8230, 78.7001,
                 "08:00 AM", "10:00 PM", 4.3, 245);

        saveShop("Trichy Hostel & Lodge",    "Sundaram",  "9876500029",
                 "Williams Road, Trichy",
                 "Education & Services", "Hotel",
                 "Budget lodge near bus stand and railway station. Clean and safe for solo travelers.",
                 "Trichy", "Tiruchirappalli", "620001", 10.8010, 78.6890,
                 "12:00 AM", "11:59 PM", 3.8, 143);

        saveShop("Trichy Bike Zone",         "Murugan",   "9876500030",
                 "Karur Bypass Road, Trichy",
                 "Transport & Vehicles", "Bike Service",
                 "TVS and Honda service center. Quick oil change, brake service, and repairs.",
                 "Trichy", "Tiruchirappalli", "620001", 10.7855, 78.7123,
                 "08:00 AM", "07:00 PM", 4.2, 98);

        // ─────────────────────────────────────────────────────────────────
        // COIMBATORE SHOPS (lat ~11.01, lng ~76.96)
        // ─────────────────────────────────────────────────────────────────

        saveShop("Annapoorna Restaurant",    "Shanmugam", "9876500031",
                 "RS Puram, Coimbatore",
                 "Food & Drinks", "Restaurant",
                 "100-year-old restaurant. Famous for their signature tiffin and filter coffee.",
                 "Coimbatore", "Coimbatore", "641002", 11.0110, 76.9580,
                 "06:30 AM", "10:00 PM", 4.8, 650);

        saveShop("Textile Hub Coimbatore",   "Lakshmi",   "9876500032",
                 "Cross Cut Road, Coimbatore",
                 "Shopping", "Clothing",
                 "Coimbatore textile city's best saree and fabric showroom. Mill price direct sales.",
                 "Coimbatore", "Coimbatore", "641012", 11.0020, 76.9660,
                 "09:00 AM", "09:00 PM", 4.5, 310);

        saveShop("LifeCare Pharmacy",        "Nirmala",   "9876500033",
                 "Gandhipuram, Coimbatore",
                 "Health & Wellness", "Pharmacy",
                 "All medicines, surgical items, and health monitors. Home delivery available.",
                 "Coimbatore", "Coimbatore", "641012", 11.0163, 76.9673,
                 "08:00 AM", "10:00 PM", 4.4, 178);

        saveShop("Gold's Gym Coimbatore",    "Fitness",   "9876500034",
                 "Peelamedu, Coimbatore",
                 "Health & Wellness", "Gym",
                 "Premium gym with international equipment. Steam, sauna, and nutrition guidance.",
                 "Coimbatore", "Coimbatore", "641004", 11.0218, 76.9830,
                 "05:00 AM", "11:00 PM", 4.7, 220);

        saveShop("Metro Supermarket CBE",    "Ravi",      "9876500035",
                 "Saibaba Colony, Coimbatore",
                 "Food & Drinks", "Supermarket",
                 "Large supermarket with fresh bakery, dairy, and imported goods section.",
                 "Coimbatore", "Coimbatore", "641011", 11.0265, 76.9382,
                 "08:00 AM", "10:00 PM", 4.4, 380);

        // ─────────────────────────────────────────────────────────────────
        // MADURAI SHOPS (lat ~9.93, lng ~78.12)
        // ─────────────────────────────────────────────────────────────────

        saveShop("Pandian Hotel Madurai",    "Pandian",   "9876500036",
                 "West Masi Street, Madurai",
                 "Food & Drinks", "Restaurant",
                 "Traditional Madurai meals. Famous for their non-veg thali and mutton kuzhambu.",
                 "Madurai", "Madurai", "625001", 9.9195, 78.1177,
                 "07:00 AM", "10:00 PM", 4.5, 470);

        saveShop("Meenakshi Saree Centre",   "Kavitha",   "9876500037",
                 "South Avani Moola Street, Madurai",
                 "Shopping", "Clothing",
                 "Temple city saree shop. Kanjivaram, Madurai cotton, and silk sarees.",
                 "Madurai", "Madurai", "625001", 9.9196, 78.1218,
                 "09:00 AM", "09:00 PM", 4.4, 235);

        saveShop("Sri Murugan Pharmacy",     "Murugan",   "9876500038",
                 "Melur Road, Madurai",
                 "Health & Wellness", "Pharmacy",
                 "Trusted pharmacy near Meenakshi temple. Ayurvedic and allopathic medicines.",
                 "Madurai", "Madurai", "625001", 9.9312, 78.1298,
                 "07:00 AM", "10:00 PM", 4.3, 156);

        saveShop("Madurai Fresh Mart",       "Sundar",    "9876500039",
                 "Anna Nagar, Madurai",
                 "Food & Drinks", "Supermarket",
                 "Daily fresh vegetables, fruits, and groceries. Best quality at local market price.",
                 "Madurai", "Madurai", "625020", 9.9542, 78.0987,
                 "07:00 AM", "09:00 PM", 4.2, 190);

        saveShop("Temple View Hotel",        "Manager",   "9876500040",
                 "Town Hall Road, Madurai",
                 "Education & Services", "Hotel",
                 "Budget hotel with rooftop view of Meenakshi temple. AC rooms with breakfast.",
                 "Madurai", "Madurai", "625001", 9.9167, 78.1197,
                 "12:00 AM", "11:59 PM", 4.1, 267);

        System.out.println("✅ Successfully seeded 40 shops across Chennai, Trichy, Coimbatore, and Madurai!");
    }

    private void saveShop(
            String name, String ownerName, String phone, String address,
            String mainCategory, String subCategory, String description,
            String city, String district, String pincode,
            double latitude, double longitude,
            String openTime, String closeTime,
            double rating, int totalReviews) {

        Shop shop = new Shop();
        shop.setName(name);
        shop.setOwnerName(ownerName);
        shop.setPhone(phone);
        shop.setAddress(address);
        shop.setMainCategory(mainCategory);
        shop.setSubCategory(subCategory);
        shop.setDescription(description);
        shop.setCity(city);
        shop.setDistrict(district);
        shop.setState("Tamil Nadu");
        shop.setPincode(pincode);
        shop.setLatitude(latitude);
        shop.setLongitude(longitude);
        shop.setOpenTime(openTime);
        shop.setCloseTime(closeTime);
        shop.setRating(rating);
        shop.setTotalReviews(totalReviews);
        shop.setIsOpen(true);
        shopRepository.save(shop);
    }
}