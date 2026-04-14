package dao;

import models.User;
import java.util.List;

public interface UserDAO {
    void save(User user);
    User findById(Long id);
    User findByUsername(String username);
    List<User> findAll();
    void update(User user);
    void delete(Long id);
    boolean usernameExists(String username);
    boolean emailExists(String email);
}
