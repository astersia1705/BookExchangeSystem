package item; 

import user.User; 

public abstract class Item {
	// Aggregation: สินค้านี้เป็นของ User คนไหน
    protected String title;
    protected String description;
    protected User owner;

    // Constructor
    public Item(String title, String description, User owner) {
        this.title = title;
        this.description = description;
        this.owner = owner; // รับ Object User เข้ามาเก็บไว้
    }

    // Abstract Method
    public abstract void displayInfo(); // <<---

    // Getters
    public String getTitle() { return title; }
    public User getOwner() { return owner; }
}