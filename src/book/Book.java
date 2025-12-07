public class Book extends Item {
    // Attributes
    private String author;
    private String courseCode;

    // Constructor
    public Book(String title, String description, User owner, String author, String courseCode) {
        super(title, description, owner);
        this.author = author;
        this.courseCode = courseCode;
    }

    @Override
    public void displayInfo() {
        // TODO: แสดงผลข้อมูลหนังสือ
    }

    // Getters (จำเป็นสำหรับ SearchService)
    public String getCourseCode() { return courseCode; }
}