package Blackjack5._1.repositories;

import Blackjack5._1.model.Player;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface PlayerRepository extends ReactiveCrudRepository<Player, Integer> {
    Flux<Player> findAllByOrderByGamesWonDesc();
}
