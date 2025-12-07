import java.util.List;
import java.util.ArrayList;

public class BookExchangeSystem {
    // Attributes
    private List<User> users;
    private List<Listing> listings;
    private List<Admin> admins;

    // Constructor
    public BookExchangeSystem() {
        this.users = new ArrayList<>();
        this.listings = new ArrayList<>();
        this.admins = new ArrayList<>();
    }

    // Methods
    public void registerUser(User user) {
        // TODO: Logic ลงทะเบียน
    }

    public Object login(String id, String pass) {
        // TODO: Logic ล็อกอิน (คืนค่า User หรือ Admin)
        return null; 
    }

    public void addListing(Listing listing) {
        // TODO: เพิ่มประกาศลง List
    }

    public void removeListing(int listingId) {
        // TODO: ลบประกาศ (Delete)
    }

    public void markAsSold(int listingId, String buyerId) {
        // TODO: เปลี่ยนสถานะสินค้า และสร้าง Transaction
    }

    public void saveData() {
        // TODO: บันทึกข้อมูลลงไฟล์
    }

    public void loadData() {
        // TODO: โหลดข้อมูลจากไฟล์
    }

    // --- Admin Support Methods ---
    public String getSystemStats() {
        // TODO: คืนค่า String สรุปยอด
        return "";
    }

    public List<User> getAllUsers() {
        // TODO: ส่งรายชื่อสมาชิกทั้งหมด
        return null;
    }

    public void banUser(String userId) {
        // TODO: แบนสมาชิก
    }
    
    public void unbanUser(String userId) {
        // TODO: ปลดแบนสมาชิก
    }
}