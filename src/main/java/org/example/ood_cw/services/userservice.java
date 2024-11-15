package org.example.ood_cw.services;


import org.example.ood_cw.models.User;
import java.util.ArrayList;
import java.util.List;

public class userservice {
    private List<User> users;

    public userservice() {
        this.users = new ArrayList<>();
    }

    // Register a new user
    public String registerUser(String userId, String userName, String password) {
        // Check if the userId already exists
        for (User user : users) {
            if (user.getUserId().equals(userId)) {
                return "UserId already exists! Please try again.";
            }
        }
        users.add(new User(userId, userName, password));
        return "Registration successful!";
    }

    // Login
    public boolean loginUser(String userId, String userName, String password) {
        for (User user : users) {
            if (user.getUserId().equals(userId) &&
                    user.getUserName().equals(userName) &&
                    user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}
