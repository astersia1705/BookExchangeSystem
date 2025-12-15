package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import user.User;

public class LoginPanel extends JPanel {
    private MainFrame mainFrame;
    private JTextField userField;
    private JPasswordField passField;

    public LoginPanel(MainFrame frame) {
        this.mainFrame = frame;
        setLayout(null);
        
        // --- ส่วนหัวข้อ ---
        JLabel titleLabel = new JLabel("เข้าสู่ระบบ (Login)");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        titleLabel.setBounds(250, 80, 400, 40);
        add(titleLabel);

        // --- ช่องกรอกข้อมูล ---
        JLabel userLabel = new JLabel("User ID:");
        userLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        userLabel.setBounds(250, 160, 100, 30);
        add(userLabel);

        userField = new JTextField();
        userField.setBounds(350, 160, 200, 30);
        add(userField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        passLabel.setBounds(250, 210, 100, 30);
        add(passLabel);

        passField = new JPasswordField();
        passField.setBounds(350, 210, 200, 30);
        add(passField);

        // --- ปุ่ม Login (ตัวปัญหาที่ต้องแก้) ---
        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(350, 270, 100, 40);
        loginBtn.setBackground(new Color(100, 149, 237)); // สีฟ้า
        loginBtn.setForeground(Color.WHITE);
        
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doLogin(); // เรียกฟังก์ชันล็อกอิน
            }
        });
        add(loginBtn);

        // --- ปุ่ม Register ---
        JButton regBtn = new JButton("Register");
        regBtn.setBounds(460, 270, 90, 40);
        regBtn.addActionListener(e -> mainFrame.showRegister());
        add(regBtn);
    }

    // ฟังก์ชันทำงานตอนกดปุ่ม
    private void doLogin() {
        String id = userField.getText();
        String pass = new String(passField.getPassword());

        // 1. เช็ค Login กับระบบ
        Object result = mainFrame.getSystem().login(id, pass);

        if (result instanceof User) {
            // Login ผ่าน
            User user = (User) result;
            JOptionPane.showMessageDialog(this, "Login สำเร็จ! ยินดีต้อนรับ: " + user.getUsername());
            
            // 2. [จุดสำคัญ] สั่งเปลี่ยนหน้า
            try {
                System.out.println(">>> กำลังจะเปลี่ยนไปหน้า MarketPlace..."); // เช็คใน Console
                mainFrame.showMarketPlace(user); // <--- บรรทัดนี้คือตัวสั่งเปลี่ยนหน้า
            } catch (Exception e) {
                // ถ้าเปลี่ยนหน้าไม่ได้ ให้เด้ง error มาบอก
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "เกิดข้อผิดพลาดในการเปลี่ยนหน้า: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            
            // ล้างช่องรอ
            userField.setText("");
            passField.setText("");
            
        } else {
            // Login ไม่ผ่าน
            JOptionPane.showMessageDialog(this, "ID หรือ Password ผิด! (Login Failed)", "แจ้งเตือน", JOptionPane.ERROR_MESSAGE);
        }
    }
}