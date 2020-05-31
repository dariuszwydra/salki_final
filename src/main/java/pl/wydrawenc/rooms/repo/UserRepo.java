package pl.wydrawenc.rooms.repo;

import org.springframework.data.repository.CrudRepository;
import pl.wydrawenc.rooms.entity.User;

public interface UserRepo extends CrudRepository<User,Integer> {

    User findByUsernameAndPassword(String username, String password);

    User findByUsername(String username);

    User findByUserID(int userID);


}
