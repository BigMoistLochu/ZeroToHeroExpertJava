package app.zerotoexpertjavaproject.userLayer.repositories;

import app.zerotoexpertjavaproject.userLayer.entities.tokenentity.Token;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends CrudRepository<Token,Long> {
}
