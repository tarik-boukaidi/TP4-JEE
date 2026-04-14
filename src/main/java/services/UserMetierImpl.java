package services;

import models.User;
import dao.UserDAO;
import dao.UserImpl;

public class UserMetierImpl implements UserMetier {
    
    private UserDAO userDAO;

    public UserMetierImpl() {
        this.userDAO = new UserImpl();
    }
    
    @Override
    public User authenticate(String username, String password) {
        User user = userDAO.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
    
    @Override
    public void register(User user) {
        if (!userDAO.usernameExists(user.getUsername()) && 
            !userDAO.emailExists(user.getEmail())) {
            user.setRole("USER"); // Role par défaut
            userDAO.save(user);
        } else {
            throw new IllegalArgumentException("Utilisateur ou email déjà existant");
        }
    }
    
    @Override
    public User getUserById(Long id) {
        return userDAO.findById(id);
    }
    
    @Override
    public User getUserByUsername(String username) {
        return userDAO.findByUsername(username);
    }
    
    @Override
    public boolean usernameExists(String username) {
        return userDAO.usernameExists(username);
    }
    
    @Override
    public boolean emailExists(String email) {
        return userDAO.emailExists(email);
    }
}
