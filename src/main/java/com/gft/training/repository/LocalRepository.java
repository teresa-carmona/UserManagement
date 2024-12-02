package com.gft.training.repository;

import java.util.HashMap;
import java.util.Map;
import com.gft.training.model.User;

public class LocalRepository {
    private static LocalRepository instance; // Singleton instance
    private final Map<Integer, User> storage;

    private LocalRepository() {
        this.storage = new HashMap<Integer, User>();
    }

    // Only one instance of LocalRepository
    public static synchronized LocalRepository getInstance() {
        if (instance == null) {
            instance = new LocalRepository();
        }
        return instance;
    }

    // Insert or update value
    public void save(int key, User user) {
        storage.put(key, user);
    }

    // Read a value
    public User find(int key) {
        return storage.get(key);
    }

    // Delete a value
    public void delete(int key) {
        storage.remove(key);
    }

    // List all values
    public Map<Integer, User> findAll() {
        return new HashMap<Integer, User>(storage);
    }

    public void clear() {
        storage.clear();
    }
}