package si.um.feri.service;

import si.um.feri.dao.UserDAO;
import si.um.feri.dao.interfaces.UserDAOInterface;
import si.um.feri.vao.User;

import java.util.List;

public class UserService {
    private final UserDAOInterface userDAO = UserDAO.getInstance();

    public void createUser(String ime, String email, double balance, String carType) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email ne sme biti prazen");
        }
        userDAO.insertUser(new User(ime, email, balance, carType));
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public User getUserByEmail(String email) {
        return userDAO.getUserByEmail(email).orElseThrow(() -> new IllegalArgumentException("User ne obstaja"));
    }

    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    public void updateUserEmail(String email, String newEmail) {
        userDAO.updateUserEmail(email, newEmail);
    }

    public void deleteUserByEmail(String email) {
        userDAO.deleteUserByEmail(email);
    }
}
