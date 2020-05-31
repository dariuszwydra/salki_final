package pl.wydrawenc.rooms.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import pl.wydrawenc.rooms.entity.Object;
import pl.wydrawenc.rooms.entity.User;
import pl.wydrawenc.rooms.repo.ObjectRepo;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ObjectService {

    ObjectRepo objectRepo;

    // constructor injection of UserRepo class object
    public ObjectService (ObjectRepo objectRepo) {
        this.objectRepo = objectRepo;
    }

    // method for saving a new user
    public void saveObject(Object object) {
        objectRepo.save(object);
    }

    public List<Object> findAllObjects() {
        return objectRepo.findAll();
    }

    public List<Object> findObjectsByUserID(User userID) {
        return objectRepo.findByUserID(userID);
    }

    public Object findObjectByObjectID(int objectID) { return objectRepo.findByObjectID(objectID); }

    public int deleteObjectByObjectID(int objectID) { return objectRepo.deleteByObjectID(objectID); }

}
