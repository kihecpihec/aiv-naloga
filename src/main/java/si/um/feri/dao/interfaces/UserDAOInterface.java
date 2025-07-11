package si.um.feri.dao.interfaces;

import jakarta.ejb.Local;
import si.um.feri.vao.User;

import java.util.List;
import java.util.Optional;

@Local
public interface UserDAOInterface {
    void insertUser(User user);
    List<User> getAllUsers();
    Optional<User> getUserByEmail(String email);
    void updateUser(User user);
    void updateUserEmail(String email, String newEmail);
    void deleteUserByEmail(String email);
}
