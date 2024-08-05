package app.zerotoexpertjavaproject.repositories;

import app.zerotoexpertjavaproject.entities.userentity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    Optional<User> findByUsername(String username);

    boolean existsUserByUsername(String username);
}
