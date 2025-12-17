package studymaterial; 

import item.Item;      
import user.User;     

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