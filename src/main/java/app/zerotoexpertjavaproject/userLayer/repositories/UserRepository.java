package app.zerotoexpertjavaproject.userLayer.repositories;

import app.zerotoexpertjavaproject.userLayer.entities.userentity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    Optional<User> findByUsername(String username);

    boolean existsUserByUsername(String username);
}
