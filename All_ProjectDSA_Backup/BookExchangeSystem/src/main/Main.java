package main; 

import java.util.List;
import java.util.Scanner;
import bookexchange.BookExchangeSystem;
import user.User;
import listing.Listing;
import item.Item;
import book.Book; 
import admin.Admin;

public class Main {
    public static void main(String[] args) {
        // 1. เริ่มระบบ
        BookExchangeSystem system = new BookExchangeSystem();
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        System.out.println("=========================================");
        System.out.println("   Welcome to Book Exchange System (v0.3 Demo)");
        System.out.println("=========================================");

        while (isRunning) {
            // เช็คสถานะล็อกอิน
            User currentUser = system.getCurrentUser(); 
            
            if (currentUser == null) {
                // --- เมนูคนทั่วไป ---
                System.out.println("\n[Main Menu]");
                System.out.println("1. Login");
                System.out.println("2. Register");
                System.out.println("3. Show All Listings");
                System.out.println("0. Exit");
                System.out.print("Select menu: ");
                
                String choice = scanner.nextLine();
                switch (choice) {
                    case "1":
                        System.out.print("User ID: ");
                        String id = scanner.nextLine();
                        System.out.print("Password: ");
                        String pass = scanner.nextLine();
                        // เรียก Login
                        Object result = system.login(id, pass);
                        if (result instanceof User) {
                            System.out.println("Login Success! Welcome User.");
                        } else if (result instanceof Admin) {
                            System.out.println("Login Success! Welcome Admin.");
                        } else {
                            System.out.println("Login Failed.");
                        }
                        break;
                    case "2":
                        System.out.print("New ID: ");
                        String uid = scanner.nextLine();
                        System.out.print("Username: ");
                        String name = scanner.nextLine();
                        System.out.print("Password: ");
                        String p = scanner.nextLine();
                        System.out.print("Contact: ");
                        String c = scanner.nextLine();
                        system.registerUser(new User(uid, name, p, c));
                        System.out.println("Registered!");
                        break;
                    case "3":
                        showListings(system);
                        break;
                    case "0":
                        isRunning = false;
                        break;
                }
            } else {
                // --- เมนูสมาชิก ---
                System.out.println("\n[Member Menu: " + currentUser.getUsername() + "]");
                System.out.println("1. Post a Book");
                System.out.println("2. Logout");
                System.out.print("Select: ");
                String choice = scanner.nextLine();
                
                if (choice.equals("1")) {
                    System.out.print("Title: ");
                    String t = scanner.nextLine();
                    System.out.print("Price: ");
                    double price = Double.parseDouble(scanner.nextLine());
                    
                    // สร้าง Book 
                    Book b = new Book(t, "Description", currentUser, "Author", "CODE101");
                    Listing l = new Listing(1, b, price);
                    system.addListing(l);
                    System.out.println("Posted!");
                } else if (choice.equals("2")) {
                    system.logout(); // 
                    System.out.println(">>> Logged out successfully.");
                }
            }
        }
    }

    private static void showListings(BookExchangeSystem sys) {
        // ใช้ List ของ Listing
    }
}