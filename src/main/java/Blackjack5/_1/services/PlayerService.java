package Blackjack5._1.services;

import Blackjack5._1.exceptions.PlayerNotFoundException;
import Blackjack5._1.model.Player;
import Blackjack5._1.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Mono<Player> createPlayer(Player player) {
        return playerRepository.save(player);
    }

    public Mono<Player> getPlayerById(Integer id) {
        return playerRepository.findById(id)
                .switchIfEmpty(Mono.error(new PlayerNotFoundException("The player with ID " + id + " doesn't exist.")));
    }

    public Flux<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public Mono<Void> deletePlayer(Integer id) {
        return playerRepository.deleteById(id);
    }

    public Mono<Player> updatePlayerName(Integer playerId, String newName) {
        return playerRepository.findById(playerId)
                .flatMap(player -> {
                    player.setName(newName);
                    return playerRepository.save(player);
                });
    }

    public Flux<Player> getRanking() {
        return playerRepository.findAllByOrderByGamesWonDesc();
    }
}

