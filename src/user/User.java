import java.util.List;

public class User {
    // Attributes
    private String userId;
    private String username;
    private String password;
    private String contactInfo;

    // Constructor
    public User(String userId, String username, String password, String contactInfo) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.contactInfo = contactInfo;
    }

    // Methods
    public String getContactInfo() {
        return contactInfo;
    }

    public List<Listing> viewMyListings() {
        // TODO: กรอง Listing ของตัวเอง
        return null;
    }

    public List<Transaction> viewTransactions() {
        // TODO: กรอง Transaction ของตัวเอง
        return null;
    }

    // Getters พื้นฐาน (จำเป็นต้องมีเพื่อให้เพื่อนใช้)
    public String getUserId() { return userId; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
}