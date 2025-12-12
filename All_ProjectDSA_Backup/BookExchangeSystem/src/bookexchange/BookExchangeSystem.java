package bookexchange; 

import java.util.ArrayList;
import java.util.List;
import user.User; 
import listing.Listing;
import admin.Admin;

public class BookExchangeSystem {
    // Attributes
    private List<User> users;
    private List<Listing> listings;
    private List<Admin> admins;
    
    // ตัวแปรจำลอง Session ว่าใครล็อกอินอยู่ 
    private User currentUser; 

    public BookExchangeSystem() {
        this.users = new ArrayList<>();
        this.listings = new ArrayList<>();
        this.admins = new ArrayList<>();
        // สร้างข้อมูลปลอมๆ ไว้โชว์ตอนพรี
        users.add(new User("u001", "Somchai", "1234", "Line: somchai99"));
        System.out.println("DEBUG: System initialized with 1 Mock User.");
    }

    // --- 1. ระบบสมาชิก ---
    public boolean registerUser(User user) {
        // เช็คว่า ID ซ้ำไหม
        for (User u : users) {
            if (u.getUserId().equals(user.getUserId())) {
                return false; // ซ้ำ สมัครไม่ได้
            }
        }
        users.add(user);
        return true; // สมัครสำเร็จ
    }

    public User login(String id, String pass) {
        for (User u : users) {
            if (u.getUserId().equals(id) && u.getPassword().equals(pass)) {
                this.currentUser = u; // จำว่าคนนี้ล็อกอินแล้ว
                return u;
            }
        }
        return null; // ไม่เจอ หรือรหัสผิด
    }
    
    
    public User getCurrentUser() {
        return currentUser;
    }
 // เพิ่มเมท็อดนี้เข้าไปในคลาส BookExchangeSystem (เช่น ต่อท้าย login)
    public void logout() {
        this.currentUser = null; // ล้างค่า user ที่จำไว้ออก
    }

    // --- 2. ระบบจัดการสินค้า ---
    public void addListing(Listing listing) {
        listings.add(listing);
    }

    // เมท็อดช่วยสำหรับดึงข้อมูลไปโชว์ (ไว้เป็นDemo present30%)
    public List<Listing> getAllListings() {
        return listings;
    }
}