package main;

import java.util.List;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;

import bookexchange.BookExchangeSystem;
import user.User;
import listing.Listing;
import book.Book;

public class Main {
    public static void main(String[] args) {
        BookExchangeSystem system = new BookExchangeSystem();
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        System.out.println("=== Book Exchange System Initialized ===");

        while (isRunning) {
            User currentUser = system.getCurrentUser();
            
            if (currentUser == null) {
                // --- หน้าแรก (Guest) เหลือแค่ 3 เมนู ---
                System.out.println("\n=========================================");
                System.out.println("   Welcome to Student Book Exchange");
                System.out.println("=========================================");
                System.out.println("1. Login (เข้าสู่ระบบ)");
                System.out.println("2. Register (สมัครสมาชิก)");
                System.out.println("0. Exit (ออกจากโปรแกรม)");
                System.out.print("Select menu: ");
                
                String choice = scanner.nextLine();
                
                switch (choice) {
                    case "1":
                        System.out.print("User ID: ");
                        String id = scanner.nextLine();
                        System.out.print("Password: ");
                        String pass = scanner.nextLine();
                        if (system.login(id, pass) != null) 
                            System.out.println(">>> Login Success!");
                        else 
                            System.out.println("!!! Login Failed: Wrong ID or Password.");
                        break;
                        
                    case "2":
                        System.out.println("--- Register New Account ---");
                        System.out.print("New ID (Student ID): "); 
                        String uid = scanner.nextLine();
                        System.out.print("Username: "); 
                        String name = scanner.nextLine();
                        System.out.print("Password: "); 
                        String p = scanner.nextLine();
                        System.out.print("Contact Info (Line/Tel): "); 
                        String c = scanner.nextLine();
                        
                        if(system.registerUser(new User(uid, name, p, c))) 
                            System.out.println(">>> Registration Successful! Please Login.");
                        else 
                            System.out.println("!!! Registration Failed: ID already exists.");
                        break;
                        
                    case "0":
                        isRunning = false;
                        System.out.println("Goodbye!");
                        break;
                        
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } else {
                // --- เมนูสมาชิก (Member) ---
                // พอล็อกอินเข้ามาแล้ว ค่อยให้ทำอย่างอื่นได้
                System.out.println("\n[Member Menu: " + currentUser.getUsername() + "]");
                System.out.println("1. Post a Book (ลงขายหนังสือ)");
                System.out.println("2. Show All Listings (ดูรายการสินค้า)");
                System.out.println("3. Search Listing (ค้นหาหนังสือ)");
                System.out.println("9. Logout (ออกจากระบบ)");
                System.out.print("Select: ");
                
                String choice = scanner.nextLine();
                
                switch (choice) {
                    case "1":
                        System.out.print("Book Title: "); String t = scanner.nextLine();
                        System.out.print("Price: "); double price = Double.parseDouble(scanner.nextLine());
                        
                        // สร้าง Mock Data สำหรับ demo
                        Book b = new Book(t, "Description", currentUser, "Author", "Code");
                        Listing l = new Listing((int)(Math.random()*1000), b, price);
                        system.addListing(l);
                        System.out.println(">>> Posted Successfully!");
                        break;
                        
                    case "2":
                        showListings(system.getAllListings());
                        break;
                        
                    case "3":
                        System.out.print("Enter keyword: ");
                        String keyword = scanner.nextLine();
                        searchDemo(system, keyword);
                        break;
                        
                    case "9":
                        system.logout();
                        System.out.println(">>> Logged out.");
                        break;
                        
                    default:
                        System.out.println("Invalid choice.");
                }
            }
        }
        scanner.close();
    }

    // ฟังก์ชันช่วยแสดงผล (Helper Methods)
    private static void showListings(List<Listing> list) {
        System.out.println("\n--- Marketplace Listings ---");
        if (list.isEmpty()) {
            System.out.println("No items found.");
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            for (Listing l : list) {
                String dateStr = (l.getListingDate() != null) ? sdf.format(l.getListingDate()) : "N/A";
                System.out.println("ID: " + l.getListingId() + 
                                   " | Item: " + l.getItem().getTitle() + 
                                   " | Price: " + l.getPrice() + " THB" +
                                   " | Date: " + dateStr);
            }
        }
    }
    
    private static void searchDemo(BookExchangeSystem sys, String keyword) {
        System.out.println("\n--- Search Result for '" + keyword + "' ---");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        boolean found = false;
        for (Listing l : sys.getAllListings()) {
            if (l.getItem().getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                String dateStr = (l.getListingDate() != null) ? sdf.format(l.getListingDate()) : "N/A";
                System.out.println("Found -> Item: " + l.getItem().getTitle() + 
                                   " | Price: " + l.getPrice() + " THB" +
                                   " | Date: " + dateStr);
                found = true;
            }
        }
        if (!found) System.out.println("No item matches your keyword.");
    }
}