package listing;

import java.util.Date; 
import item.Item;
import user.User;

public class Listing {
    // Attributes
	// Listing "มี" Item เป็นองค์ประกอบ (ถ้าไม่มีสินค้า ก็ประกาศขายไม่ได้)
    private int listingId;
    private Item item;
    private double price;
    private boolean isAvailable;
    private Date listingDate; 

    // Constructor
    public Listing(int listingId, Item item, double price) {
        this.listingId = listingId;
        this.item = item; // รับค่าเข้ามาโดยไม่สนว่าเป็น Book หรือ Material
        this.price = price;
        this.isAvailable = true;
        this.listingDate = new Date(); 
    }

    // Methods
    public double getPrice() {
        return price;
    }
   // เมื่อเรียกใช้ item.getTitle() ระบบจะไปดึงชื่อจาก object ลูกจริงๆ โดยอัตโนมัติ
    public String getItemDetails() {
        return item.getTitle() + " (Price: " + price + ")";
    }

    public void markAsSold() {
        this.isAvailable = false;
    }

    // Getters
    public int getListingId() { return listingId; }
    public Item getItem() { return item; }
    public boolean isAvailable() { return isAvailable; }
    public User getOwner() { return item.getOwner(); }
    public Date getListingDate() { return listingDate; }
}