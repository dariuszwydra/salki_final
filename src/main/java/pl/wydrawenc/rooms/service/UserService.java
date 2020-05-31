package pl.wydrawenc.rooms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wydrawenc.rooms.entity.User;
import pl.wydrawenc.rooms.repo.UserRepo;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService {

    UserRepo userRepo;

    // constructor injection of UserRepo class object
    public UserService (UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    // method for saving a new user
    public void saveUser(User user) {
        userRepo.save(user);
    }

    public void saveOrUpdateUser(User user) { }

    // method for logging user by checking username and password
    public User findByUsernameAndPassword(String username, String password) {
        return userRepo.findByUsernameAndPassword(username,password);
    }

    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    public User findByUserID(int userID) { return userRepo.findByUserID(userID); }

    public List<User> findAllUsers() {
        return (List<User>) userRepo.findAll();
    }

    public void deleteUserByID(int userID) {

        userRepo.deleteById(userID);

    }
}
