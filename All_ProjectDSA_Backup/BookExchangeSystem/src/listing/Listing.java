package listing; 

import item.Item; 
import user.User; 

public class Listing {
    // Attributes
    private int listingId;
    private Item item;
    private double price;
    private boolean isAvailable;

    // Constructor
    public Listing(int listingId, Item item, double price) {
        this.listingId = listingId;
        this.item = item;
        this.price = price;
        this.isAvailable = true;
    }

    // Methods
    public double getPrice() {
        return price;
    }

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
}