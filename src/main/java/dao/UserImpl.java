package dao;

import models.User;
import java.util.*;

public class UserImpl implements UserDAO {
    
    private static final Map<Long, User> users = new HashMap<>();
    private static Long nextId = 1L;
    
    
    static {
        users.put(1L, new User(1L, "admin", "admin123", "admin@example.com", "ADMIN"));
        users.put(2L, new User(2L, "user1", "user123", "user1@example.com", "USER"));
        nextId = 3L;
    }
    
    @Override
    public void save(User user) {
        if (user.getId() == null) {
            user.setId(nextId++);
        }
        users.put(user.getId(), user);
    }
    
    @Override
    public User findById(Long id) {
        return users.get(id);
    }
    
    @Override
    public User findByUsername(String username) {
        for (User user : users.values()) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
    
    @Override
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }
    
    @Override
    public void update(User user) {
        users.put(user.getId(), user);
    }
    
    @Override
    public void delete(Long id) {
        users.remove(id);
    }
    
    @Override
    public boolean usernameExists(String username) {
        return findByUsername(username) != null;
    }
    
    @Override
    public boolean emailExists(String email) {
        for (User user : users.values()) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }
}
