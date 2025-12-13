package main; 

import java.util.Scanner;
import bookexchange.BookExchangeSystem;
import user.User;
import listing.Listing;
import item.Item;
import item.Book;   
import book.Book;   
import admin.Admin;

public class Main {
    public static void main(String[] args) {
        BookExchangeSystem system = new BookExchangeSystem();
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        System.out.println("=== Book Exchange System Initialized ===");

        while (isRunning) {
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
                        Object result = system.login(id, pass);
                        if (result != null) System.out.println(">>> Login Success!");
                        else System.out.println("!!! Login Failed.");
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
                        if(system.registerUser(new User(uid, name, p, c)))
                            System.out.println(">>> Registered!");
                        else
                            System.out.println("!!! ID Already exists.");
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
                System.out.println("2. Show All Listings");
                System.out.println("9. Logout");
                System.out.print("Select: ");
                
                String choice = scanner.nextLine();
                
                switch (choice) {
                    case "1":
                        System.out.print("Book Title: ");
                        String t = scanner.nextLine();
                        System.out.print("Price: ");
                        double price = Double.parseDouble(scanner.nextLine());
                        
                        // สร้าง Book 
                        Book b = new Book(t, "Description", currentUser, "AuthorName", "CODE101");
                        Listing l = new Listing((int)(Math.random()*1000), b, price);
                        system.addListing(l);
                        System.out.println(">>> Posted!");
                        break;
                        
                    case "2":
                        showListings(system);
                        break;
                        
                    case "9":
                        system.logout(); // สั่ง Logout
                        System.out.println(">>> Logged out.");
                        break;
                }
            }
        }
    }

    private static void showListings(BookExchangeSystem sys) {
        // ... (ส่วนแสดงผลเหมือนเดิม) ...
        if (sys.getAllListings().isEmpty()) {
            System.out.println("No items available.");
        } else {
            for (Listing l : sys.getAllListings()) {
                System.out.println("Item: " + l.getItem().getTitle() + " | Price: " + l.getPrice());
            }
        }
    }
}