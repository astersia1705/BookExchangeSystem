import java.util.Date;

public class Transaction {
    // Attributes
    private String transactionId;
    private User buyer;
    private User seller;
    private Listing listing;
    private Date transactionDate;

    // Constructor
    public Transaction(String transactionId, User buyer, User seller, Listing listing) {
        this.transactionId = transactionId;
        this.buyer = buyer;
        this.seller = seller;
        this.listing = listing;
        this.transactionDate = new Date();
    }

    // Methods
    public void completeTransaction() {
        // TODO: บันทึก Log
    }
    
    // ToString หรือ Getters สำหรับแสดงผล
    @Override
    public String toString() {
        return "Transaction [ID=" + transactionId + ", Buyer=" + buyer.getUsername() + "]";
    }
}