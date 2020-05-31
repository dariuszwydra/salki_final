package pl.wydrawenc.rooms.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import pl.wydrawenc.rooms.entity.Object;
import pl.wydrawenc.rooms.entity.User;

import java.util.List;

public interface ObjectRepo extends CrudRepository<Object,Integer> {

    public List<Object> findAll();

    public List<Object> findByUserID(User userID);

    public Object findByObjectID(int objectID);

    public int deleteByObjectID(int objectID);
}
