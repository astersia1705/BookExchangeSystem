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
        // TODO: แสดงผลข้อมูลชีท
    }
}