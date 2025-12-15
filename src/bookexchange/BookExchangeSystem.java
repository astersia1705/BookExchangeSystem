package bookexchange;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors; // สำหรับกรองข้อมูล
import user.User;
import listing.Listing;
import transaction.Transaction;
import book.Book;

public class BookExchangeSystem {
    private List<User> users;
    private List<Listing> listings;
    private List<Transaction> transactions; // <--- เพิ่มตัวแปรนี้
    private User currentUser; 

    public BookExchangeSystem() {
        this.users = new ArrayList<>();
        this.listings = new ArrayList<>();
        this.transactions = new ArrayList<>(); // <--- init list

        // Mock User
        User u1 = new User("u1", "TestUser", "1234", "Line:test");
        users.add(u1);
        
        // Mock Data: สร้างประวัติการซื้อขายจำลอง เพื่อให้ตารางไม่โล่ง
        // สมมติว่า TestUser เคยขายหนังสือไป 1 เล่ม
        Book b1 = new Book("Java Programming", "Basic Java", u1, "John Doe", "CS101");
        Listing l1 = new Listing(999, b1, 250.0);
        User buyer = new User("u2", "Buyer01", "1234", "081-111-1111"); // คู่ค้า
        
        // เพิ่ม Transaction เข้าไปในระบบ
        transactions.add(new Transaction("TRX-001", buyer, u1, l1));
    }

    // --- เพิ่ม Method ดึงประวัติของ User คนนั้น ---
    public List<Transaction> getUserTransactions(User user) {
        return transactions.stream()
            .filter(t -> t.getBuyer().getUserId().equals(user.getUserId()) || 
                         t.getSeller().getUserId().equals(user.getUserId()))
            .collect(Collectors.toList());
    }

    // ... (ส่วนอื่นๆ ของไฟล์เหมือนเดิม: registerUser, login, addListing etc.) ...
    public boolean registerUser(User user) {
        for (User u : users) {
            if (u.getUserId().equalsIgnoreCase(user.getUserId())) return false;
        }
        users.add(user);
        return true;
    }

    public Object login(String id, String pass) {
        for (User u : users) {
            if (u.getUserId().equals(id) && u.getPassword().equals(pass)) {
                this.currentUser = u;
                return u;
            }
        }
        return null;
    }
    
    public void logout() { this.currentUser = null; }
    public User getCurrentUser() { return currentUser; }
    public void addListing(Listing listing) { listings.add(listing); }
    public List<Listing> getAllListings() { return listings; }
    public boolean removeListing(int listingId) { return listings.removeIf(l -> l.getListingId() == listingId); }
    public BookExchangeSystem getSystem() { return this; }
}