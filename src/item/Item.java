public abstract class Item {
    // Attributes (Protected ตาม UML)
    protected String title;
    protected String description;
    protected User owner;

    // Constructor
    public Item(String title, String description, User owner) {
        this.title = title;
        this.description = description;
        this.owner = owner;
    }

    // Abstract Method
    public abstract void displayInfo();

    // Getters (จำเป็นสำหรับ SearchService)
    public String getTitle() { return title; }
    public User getOwner() { return owner; }
}