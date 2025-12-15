package gui;

import javax.swing.*;
import java.awt.*;
import user.User;
import book.Book;
import studymaterial.StudyMaterial; // <--- Import คลาส StudyMaterial เพิ่ม
import listing.Listing;

public class PostItemPanel extends JPanel {
    private MainFrame mainFrame;
    private User currentUser;
    
    // UI Components
    private JRadioButton rbBook, rbMaterial;
    private JTextField titleField, dynamicField, codeField, descField, priceField;
    private JLabel dynamicLabel; // Label ที่จะเปลี่ยนข้อความได้ (Author <-> Subject)

    public PostItemPanel(MainFrame frame, User user) {
        this.mainFrame = frame;
        this.currentUser = user;
        setLayout(null);
        setBackground(new Color(40, 60, 80)); // พื้นหลังสี Dark Blue

        // --- 1. Header ---
        JLabel headerLabel = new JLabel("POST NEW ITEM", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setBounds(0, 20, 800, 40);
        add(headerLabel);

        // --- 2. Select Type (ส่วนเลือกประเภท) ---
        JLabel typeLabel = new JLabel("Select Type (ประเภท):");
        typeLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        typeLabel.setForeground(Color.WHITE);
        typeLabel.setBounds(150, 70, 200, 30);
        add(typeLabel);

        rbBook = new JRadioButton("Book (หนังสือ)", true); // Default เลือก Book
        rbBook.setBounds(350, 70, 150, 30);
        rbBook.setBackground(new Color(40, 60, 80));
        rbBook.setForeground(Color.WHITE);
        rbBook.setFont(new Font("Tahoma", Font.PLAIN, 14));

        rbMaterial = new JRadioButton("Study Material (สื่อการเรียนรู้)");
        rbMaterial.setBounds(500, 70, 250, 30);
        rbMaterial.setBackground(new Color(40, 60, 80));
        rbMaterial.setForeground(Color.WHITE);
        rbMaterial.setFont(new Font("Tahoma", Font.PLAIN, 14));

        // จัดกลุ่มเพื่อให้เลือกได้แค่อย่างเดียว
        ButtonGroup typeGroup = new ButtonGroup();
        typeGroup.add(rbBook);
        typeGroup.add(rbMaterial);
        
        add(rbBook);
        add(rbMaterial);

        // ใส่ Action Listener เมื่อเปลี่ยนตัวเลือก
        rbBook.addActionListener(e -> updateFormDisplay());
        rbMaterial.addActionListener(e -> updateFormDisplay());

        // --- 3. Input Fields ---
        int startY = 120; // จุดเริ่มแกน Y
        int gap = 50;     // ระยะห่างแต่ละบรรทัด

        // Title
        createInputRow("Title (ชื่อ):", startY, titleField = new JTextField());

        // Dynamic Row (จะเป็น Author หรือ Subject ขึ้นอยู่กับเลือก Type)
        // เราจะเก็บ JLabel ไว้ในตัวแปร dynamicLabel เพื่อสั่งเปลี่ยนข้อความทีหลัง
        dynamicLabel = new JLabel("Author (ผู้แต่ง):");
        dynamicLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        dynamicLabel.setForeground(Color.WHITE);
        dynamicLabel.setBounds(150, startY + gap, 200, 30);
        add(dynamicLabel);

        dynamicField = new JTextField(); // ช่องนี้ใช้เก็บ Author หรือ Subject
        dynamicField.setBounds(350, startY + gap, 300, 30);
        add(dynamicField);

        // Code
        createInputRow("Course Code (รหัสวิชา):", startY + gap * 2, codeField = new JTextField());
        
        // Description
        createInputRow("Description (รายละเอียด):", startY + gap * 3, descField = new JTextField());
        
        // Price
        createInputRow("Price (ใส่ 0 ถ้าแจกฟรี):", startY + gap * 4, priceField = new JTextField());

        // --- 4. Buttons ---
        JButton postBtn = new JButton("Post Item");
        postBtn.setBounds(350, startY + gap * 5 + 10, 120, 40);
        postBtn.setBackground(new Color(100, 149, 237));
        postBtn.setForeground(Color.WHITE);
        postBtn.addActionListener(e -> doPost());
        add(postBtn);

        JButton backBtn = new JButton("Back");
        backBtn.setBounds(350, startY + gap * 5 + 70, 120, 30);
        backBtn.addActionListener(e -> mainFrame.showMarketPlace(currentUser));
        add(backBtn);
        
        // เรียกครั้งแรกเพื่อให้หน้าจอถูกต้องตาม Default
        updateFormDisplay();
    }

    // ฟังก์ชันช่วยสร้าง Label+TextField
    private void createInputRow(String labelText, int y, JTextField field) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Tahoma", Font.BOLD, 14));
        label.setForeground(Color.WHITE);
        label.setBounds(150, y, 200, 30);
        add(label);
        field.setBounds(350, y, 300, 30);
        add(field);
    }

    // ฟังก์ชันปรับหน้าจอเมื่อเลือก Type
    private void updateFormDisplay() {
        if (rbBook.isSelected()) {
            dynamicLabel.setText("Author (ผู้แต่ง):");
            codeField.setEnabled(true); // หนังสือมีรหัสวิชา
            codeField.setBackground(Color.WHITE);
        } else {
            dynamicLabel.setText("Subject (วิชา):");
            // ตาม Class StudyMaterial ไม่มีช่องเก็บ Code โดยตรง
            // แต่ถ้าใน Design มีช่องให้กรอก เราก็เปิดไว้แล้วเอาไปต่อท้าย Description ได้
            codeField.setEnabled(true); 
        }
    }

    // ฟังก์ชันบันทึกข้อมูล
    private void doPost() {
        String title = titleField.getText();
        String dynamicValue = dynamicField.getText(); // เป็น Author หรือ Subject
        String code = codeField.getText();
        String desc = descField.getText();
        String priceText = priceField.getText();

        if (title.isEmpty() || priceText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "กรุณากรอกข้อมูล Title และ Price", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int choice = JOptionPane.showConfirmDialog(this, "Confirm Posting?", "Message", JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {
            try {
                double price = Double.parseDouble(priceText);
                int randomId = (int)(Math.random() * 1000);
                
                // สร้าง Item ตามประเภทที่เลือก
                listing.Listing newListing;
                
                if (rbBook.isSelected()) {
                    // กรณี Book: dynamicValue คือ Author
                    Book book = new Book(title, desc, currentUser, dynamicValue, code);
                    newListing = new Listing(randomId, book, price);
                } else {
                    // กรณี StudyMaterial: dynamicValue คือ Subject
                    // หมายเหตุ: StudyMaterial ไม่มีช่อง Code ใน Constructor 
                    // เราจึงเอา Code ไปแปะเพิ่มใน Description แทน เพื่อไม่ให้ข้อมูลหาย
                    String finalDesc = desc + (code.isEmpty() ? "" : " [Code: " + code + "]");
                    StudyMaterial mat = new StudyMaterial(title, finalDesc, currentUser, dynamicValue);
                    newListing = new Listing(randomId, mat, price);
                }

                mainFrame.getSystem().addListing(newListing);

                JOptionPane.showMessageDialog(this, 
                    "[Success] Item posted successfully! Listing ID: #" + randomId, 
                    "Message", JOptionPane.INFORMATION_MESSAGE);

                mainFrame.showMarketPlace(currentUser);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "กรุณากรอก Price เป็นตัวเลข", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        }
    }
}