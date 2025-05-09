package si.um.feri.dao;

import jakarta.ejb.Stateless;
import si.um.feri.dao.interfaces.UserDAOInterface;
import si.um.feri.vao.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Stateless
public class UserDAO implements UserDAOInterface {
//    private static UserDAO instance;
    private List<User> users = new ArrayList<>();

//    private UserDAO() {}
//
//    public static UserDAO getInstance() {
//        if (instance == null) {
//            instance = new UserDAO();
//        }
//        return instance;
//    }

    @Override
    public void insertUser(User user) {
        boolean emailExists = users.stream()
                .anyMatch(existing -> existing.getEmail().equalsIgnoreCase(user.getEmail()));

        if (emailExists) {
            throw new IllegalArgumentException("Email already exists: " + user.getEmail());
        }

        users.add(user);
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return users.stream().filter(user -> user.getEmail().equals(email)).findFirst();
    }

    @Override
    public void updateUser(User updatedUser) {
        for (int i = 0; i < users.size(); i++) {
            User u = users.get(i);
            if (u.getEmail().equals(updatedUser.getEmail())) {
                users.set(i, updatedUser);
                return;
            }
        }
        throw new IllegalArgumentException("Uporabnik ni bil najden za posodobitev");
    }

    @Override
    public void updateUserEmail(String email, String newEmail) {
        getUserByEmail(email).ifPresent(user -> user.setEmail(newEmail));
    }

    @Override
    public void deleteUserByEmail(String email) {
        getUserByEmail(email).ifPresent(user -> users.remove(user));
    }
}
