package app.zerotoexpertjavaproject.repositories;

import app.zerotoexpertjavaproject.entities.tokenentity.Token;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends CrudRepository<Token,Long> {
}
