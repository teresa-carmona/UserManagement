package com.gft.training.service;

import com.gft.training.exception.NoUserException;
import com.gft.training.exception.UserValidationException;
import com.gft.training.model.User;
import com.gft.training.repository.LocalRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
public class UserService {

    private final static LocalRepository repository = LocalRepository.getInstance();
    private int nextId = 1;

    public void logMessage() {
        log.info("UserService initialized");

        List<User> users = getUsers();
        log.info("Users retrieved: {}", users);

        log.debug("Debugging user retrieval...");
        for (User user : users) {
            log.info("Processing user: {}", user);
        }
    }

    // Add a new user
    public void addUser(String name, int age) throws UserValidationException {
        // Validate name
        if (name == null || name.trim().isEmpty()) {
            log.error("No name specified");
            throw new UserValidationException("Name cannot be empty");
        }

        // Validate age
        if (age < 0) {
            log.error("Age received was negative");
            throw new UserValidationException("Age must be a positive number");
        }

        // Store user
        User user = new User(name, age);
        repository.save(nextId++, user);

        log.info("\n User {} (age: {}) successfully added", name, age);
    }

    // Get a user
    public User getUser(int id) {
        return repository.find(id);
    }

    // List all users
    public void listUsers() {
        log.info("\nList of existing users:");
        Map<Integer, User> users = repository.findAll();
        for (Map.Entry<Integer, User> entry : users.entrySet()) {
            log.info("ID: " + entry.getKey() + ", " + entry.getValue());
        }
    }

    // Delete a user and reorganise ids
    public void deleteUser(int id) throws NoUserException {
        User user = repository.find(id);
        if (user == null) {
            log.error("No user found for id {}", id);
            throw new NoUserException("User not found");
        }
        repository.delete(id);

        // Get rest of users and reorganise keys
        Map<Integer, User> users = repository.findAll();
        repository.clear();
        int newId = 1;

        for (Map.Entry<Integer, User> entry : users.entrySet()) {
            repository.save(newId++, entry.getValue());
        }

        // Update next value
        nextId = newId;
        log.info("\n User " + user.getName() + " successfully deleted");
    }



    private List<User> getUsers() {
        return Arrays.asList(
                new User("Alice", 15),
                new User("Bob", 60)
        );
    }
}
