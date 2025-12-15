package studymaterial; // <--- สำคัญมาก! ต้องมีบรรทัดนี้

import item.Item;      // <--- ต้อง Import Item
import user.User;      // <--- ต้อง Import User

public class StudyMaterial extends Item {
    // Attributes
    private String subject;

    // Constructor
    public StudyMaterial(String title, String description, User owner, String subject) {
        super(title, description, owner);
        this.subject = subject;
    }

    @Override
    public void displayInfo() {
        System.out.println("Study Material: " + getTitle() + " | Subject: " + subject);
    }
    
    // Getter
    public String getSubject() { return subject; }
}