package com.example.demo;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class UserManager {
    private Repository repository;
    private Map<Integer, User> users;
    private String filepath;
    private static  User loggedInUser;


    public UserManager(String filepath) throws FileNotFoundException {
        this.repository = Repository.getRepo();
        this.filepath = filepath;
        this.users = new HashMap<>();
        loadUsers();
    }

    public boolean authenticate(String username, String password) {
        users = repository.getUsers();

        for (User u : users.values()) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }


    void loadUsers() {
        try {
            URL resourceUrl = getClass().getClassLoader().getResource(filepath);

            if (resourceUrl != null) {
                Path path = Paths.get(resourceUrl.toURI());
                try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(path.toFile()))) {
                    Map<Integer, User> loadedUsers = (Map<Integer, User>) input.readObject();
                    if (loadedUsers != null) {
                        users = loadedUsers;
                    } else {
                        users = new HashMap<>(); // Inicialize se estiver nulo
                    }
                } catch (IOException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            } else {
                // Handle the case when the resource is not found
                System.err.println("Resource not found: " + filepath);
            }
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public User getUserByUsername(String username){
        for(User u: users.values()){
            if(u.getUsername().equals(username)){
                return u;
            }
        }
        return null;
    }
//------------------------------Getters & Setters------------------------------------------
    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User user) {
        loggedInUser= user;
    }
}