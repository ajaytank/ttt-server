package hello;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface GameRepository extends MongoRepository<Game, String> {

    public List<Game> findByPlayerOneId(Integer playerOneId);

    List<Game> findByStatus(String status);
}