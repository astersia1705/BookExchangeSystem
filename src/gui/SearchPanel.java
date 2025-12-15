package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;
import listing.Listing;
import book.Book;
import user.User;

public class SearchPanel extends JPanel {
    private MainFrame mainFrame;
    private User currentUser;
    
    // UI Components
    private JTextField searchField;
    private JTable table;
    private DefaultTableModel tableModel;
    
    // Checkboxes (ตัวเลือกค้นหา & เรียงลำดับ)
    private JCheckBox cbByCode, cbByTitle;
    private JCheckBox cbSortPrice, cbSortTitle;

    public SearchPanel(MainFrame frame, User user) {
        this.mainFrame = frame;
        this.currentUser = user;
        setLayout(null); // ใช้ Null Layout เพื่อจัดตำแหน่งตามภาพ
        setBackground(new Color(40, 60, 80)); // สี Dark Blue Theme

        // --- 1. Header ---
        JLabel titleLabel = new JLabel("SEARCH RESULTS", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(0, 20, 800, 40);
        add(titleLabel);

        // --- 2. Filter Options (Checkbox) ---
        JLabel filterLabel = new JLabel("ประเภทการค้นหา:");
        filterLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        filterLabel.setForeground(Color.WHITE);
        filterLabel.setBounds(50, 70, 150, 30);
        add(filterLabel);

        cbByCode = createCheckBox("ค้นหาตามรหัสวิชา", 180, 70);
        cbByTitle = createCheckBox("ค้นหาตามชื่อหนังสือ", 320, 70);
        cbByTitle.setSelected(true); // Default ให้ค้นตามชื่อ

        cbSortPrice = createCheckBox("เรียงลำดับตามราคา", 480, 70);
        cbSortTitle = createCheckBox("เรียงลำดับตามชื่อ", 630, 70);
        
        // Grouping Checkbox (ถ้าอยากให้เลือกได้อย่างใดอย่างหนึ่ง)
        // แต่ในที่นี้แยกอิสระเพื่อให้ User ติ๊กผสมได้

        // --- 3. Search Bar ---
        searchField = new JTextField();
        searchField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        searchField.setBounds(50, 110, 550, 40);
        add(searchField);

        JButton searchBtn = new JButton("ค้นหา");
        searchBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
        searchBtn.setBounds(620, 110, 130, 40);
        searchBtn.addActionListener(e -> doSearch());
        add(searchBtn);

        // --- 4. Table ---
        String[] columnNames = {"ID", "Title", "Price", "Seller"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        table.setRowHeight(30);
        table.setFont(new Font("Tahoma", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 170, 700, 350);
        add(scrollPane);

        // --- 5. Back Button ---
        JButton backBtn = new JButton("Back");
        backBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
        backBtn.setBounds(650, 530, 100, 35);
        backBtn.addActionListener(e -> mainFrame.showMarketPlace(currentUser));
        add(backBtn);

        // โหลดข้อมูลทั้งหมดมาแสดงก่อน (Show All)
        doSearch();
    }

    private JCheckBox createCheckBox(String text, int x, int y) {
        JCheckBox cb = new JCheckBox(text);
        cb.setFont(new Font("Tahoma", Font.BOLD, 12));
        cb.setForeground(Color.WHITE);
        cb.setBackground(new Color(40, 60, 80)); // กลมกลืนกับพื้นหลัง
        cb.setBounds(x, y, 150, 30);
        add(cb);
        return cb;
    }

    // --- Logic การค้นหาและเรียงลำดับ ---
    private void doSearch() {
        String keyword = searchField.getText().toLowerCase().trim();
        tableModel.setRowCount(0); // ล้างตารางเก่า

        List<Listing> allListings = mainFrame.getSystem().getAllListings();
        List<Listing> resultList = new ArrayList<>();

        // 1. Filter (กรองข้อมูล)
        for (Listing l : allListings) {
            // ต้องเป็นของที่ยังไม่ขาย (Available)
            if (!l.isAvailable()) continue;

            boolean match = false;
            String title = l.getItem().getTitle().toLowerCase();
            String code = ""; 
            
            // พยายามดึง Course Code (ถ้าเป็น Book)
            if (l.getItem() instanceof Book) {
                code = ((Book) l.getItem()).getCourseCode().toLowerCase();
            }

            // ถ้าไม่มี Keyword ให้แสดงทั้งหมด
            if (keyword.isEmpty()) {
                match = true;
            } else {
                // เช็คเงื่อนไขตาม Checkbox
                if (cbByTitle.isSelected() && title.contains(keyword)) match = true;
                if (cbByCode.isSelected() && code.contains(keyword)) match = true;
            }

            if (match) {
                resultList.add(l);
            }
        }

        // 2. Sort (เรียงลำดับ)
        if (cbSortPrice.isSelected()) {
            resultList.sort(Comparator.comparingDouble(Listing::getPrice));
        } else if (cbSortTitle.isSelected()) {
            resultList.sort((l1, l2) -> l1.getItem().getTitle().compareToIgnoreCase(l2.getItem().getTitle()));
        }

        // 3. Add to Table (แสดงผล)
        for (Listing l : resultList) {
            Object[] row = {
                l.getListingId(),
                l.getItem().getTitle(),
                l.getPrice(),
                l.getOwner().getUsername()
            };
            tableModel.addRow(row);
        }
    }
}