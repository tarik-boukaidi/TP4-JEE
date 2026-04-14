package services;

import models.User;

public interface UserMetier {
    User authenticate(String username, String password);
    void register(User user);
    User getUserById(Long id);
    User getUserByUsername(String username);
    boolean usernameExists(String username);
    boolean emailExists(String email);
}
