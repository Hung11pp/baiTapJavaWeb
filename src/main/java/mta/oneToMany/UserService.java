package mta.oneToMany;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository repo;

    public List<User> listAll() {
        return repo.findAll();
    }

    public User save(User user) {
        repo.save(user);
        return user;
    }

    public User get(long id) {
        return repo.findById(id).orElse(null);
    }

    public void delete(long id) {
        repo.deleteById(id);
    }

    public User update(User user) {
        repo.save(user);
        return user;
    }
}
