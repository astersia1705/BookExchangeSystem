package bookexchange;

import java.util.List;
import java.util.ArrayList;
import user.User;
import listing.Listing;
import admin.Admin;

public class BookExchangeSystem {
    // Attributes
    private List<User> users;
    private List<Listing> listings;
    private List<Admin> admins;
    
    private User currentUser; 

    public BookExchangeSystem() {
        this.users = new ArrayList<>();
        this.listings = new ArrayList<>();
        this.admins = new ArrayList<>();
        // Mock Data
        users.add(new User("u1", "TestUser", "1234", "Line:test"));
    }

    // --- User Management ---
    public boolean registerUser(User user) {
        for (User u : users) {
            if (u.getUserId().equalsIgnoreCase(user.getUserId())) return false;
        }
        users.add(user);
        return true;
    }

    public Object login(String id, String pass) {
        for (User u : users) {
            if (u.getUserId().equals(id) && u.getPassword().equals(pass)) {
                this.currentUser = u;
                return u;
            }
        }
        return null;
    }
    
    public void logout() {
        this.currentUser = null;
    }
    
    public User getCurrentUser() {
        return currentUser;
    }

    // --- Listing Management ---
    public void addListing(Listing listing) {
        listings.add(listing);
    }

    public List<Listing> getAllListings() {
        return listings;
    }
}