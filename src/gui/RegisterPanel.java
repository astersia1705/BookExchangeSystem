package gui;

import javax.swing.*;
import java.awt.*;
import user.User;

public class RegisterPanel extends JPanel {
    private MainFrame mainFrame;
    private JTextField idField, nameField, contactField;
    private JPasswordField passField;

    public RegisterPanel(MainFrame frame) {
        this.mainFrame = frame;
        setLayout(null);

        JLabel title = new JLabel("สมัครสมาชิกใหม่");
        title.setFont(new Font("Tahoma", Font.BOLD, 22));
        title.setBounds(300, 50, 300, 30);
        add(title);

        // สร้าง Method ช่วยสร้าง Label+Field จะได้โค้ดไม่รก
        idField = createField("User ID:", 120);
        nameField = createField("Username:", 170);
        passField = createPasswordField("Password:", 220);
        contactField = createField("Contact:", 270);

        JButton submitBtn = new JButton("Confirm Register");
        submitBtn.setBounds(300, 350, 200, 40);
        submitBtn.setBackground(new Color(60, 179, 113));
        submitBtn.setForeground(Color.WHITE);
        submitBtn.addActionListener(e -> doRegister());
        add(submitBtn);

        JButton backBtn = new JButton("Cancel");
        backBtn.setBounds(300, 410, 200, 30);
        backBtn.addActionListener(e -> mainFrame.showLogin());
        add(backBtn);
    }

    private JTextField createField(String labelText, int y) {
        JLabel label = new JLabel(labelText);
        label.setBounds(200, y, 100, 30);
        add(label);
        JTextField field = new JTextField();
        field.setBounds(300, y, 250, 30);
        add(field);
        return field;
    }

    private JPasswordField createPasswordField(String labelText, int y) {
        JLabel label = new JLabel(labelText);
        label.setBounds(200, y, 100, 30);
        add(label);
        JPasswordField field = new JPasswordField();
        field.setBounds(300, y, 250, 30);
        add(field);
        return field;
    }

    private void doRegister() {
        String uid = idField.getText();
        String name = nameField.getText();
        String pass = new String(passField.getPassword());
        String contact = contactField.getText();

        if(uid.isEmpty() || name.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "กรุณากรอกข้อมูลให้ครบ");
            return;
        }

        User newUser = new User(uid, name, pass, contact);
        boolean success = mainFrame.getSystem().registerUser(newUser);

        if(success) {
            JOptionPane.showMessageDialog(this, "สมัครสำเร็จ! กรุณาล็อกอิน");
            mainFrame.showLogin();
            // Clear fields
            idField.setText(""); nameField.setText(""); passField.setText(""); contactField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "ID นี้มีผู้ใช้แล้ว", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}