package si.um.feri.service;

import jakarta.ejb.Local;
import si.um.feri.vao.User;

import java.util.List;

@Local
public interface UserServiceInterface {
    void createUser(String ime, String email, double balance, String carType);

    List<User> getAllUsers();

    User getUserByEmail(String email);

    void updateUser(User user);

    void updateUserEmail(String email, String newEmail);

    void deleteUserByEmail(String email);
}
