package transaction;

import java.util.Date;
import user.User;
import listing.Listing;

public class Transaction {
	// Aggregation: อ้างอิงถึง User ที่มีตัวตนอยู่แล้วข้างนอก
    private String transactionId;
    private User buyer;
    private User seller;
    private Listing listing;
    private Date transactionDate;

    // รับ User เข้ามาทาง Constructor (ไม่ได้ new User() เองข้างใน)
    public Transaction(String transactionId, User buyer, User seller, Listing listing) {
        this.transactionId = transactionId;
        this.buyer = buyer;
        this.seller = seller;
        this.listing = listing;
        this.transactionDate = new Date();
    }

    // --- Getters (เพิ่มส่วนนี้) ---
    public String getTransactionId() { return transactionId; }
    public User getBuyer() { return buyer; }
    public User getSeller() { return seller; }
    public Listing getListing() { return listing; }
    public Date getTransactionDate() { return transactionDate; }
}