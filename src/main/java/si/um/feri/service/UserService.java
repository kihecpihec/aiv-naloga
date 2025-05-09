package si.um.feri.service;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import si.um.feri.dao.interfaces.UserDAOInterface;
import si.um.feri.vao.User;

import java.util.List;

@Stateless
public class UserService implements UserServiceInterface {
    @EJB
    UserDAOInterface userDAO;

    @Override
    public void createUser(String ime, String email, double balance, String carType) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email ne sme biti prazen");
        }
        userDAO.insertUser(new User(ime, email, balance, carType));
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public User getUserByEmail(String email) {
        return userDAO.getUserByEmail(email).orElseThrow(() -> new IllegalArgumentException("User ne obstaja"));
    }

    @Override
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    @Override
    public void updateUserEmail(String email, String newEmail) {
        userDAO.updateUserEmail(email, newEmail);
    }

    @Override
    public void deleteUserByEmail(String email) {
        userDAO.deleteUserByEmail(email);
    }
}
