package Blackjack5._1.services;

import Blackjack5._1.model.Deck;
import Blackjack5._1.model.Game;
import Blackjack5._1.repositories.GameRepository;
import Blackjack5._1.utils.BlackjackUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class GameService {
    private final GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Mono<Game> newGame(Game game) {
        Deck deck = new Deck();
        game.setStatus("IN_PROGRESS");

        game.setPlayerHand(List.of(deck.drawCard(), deck.drawCard()));
        game.setDealerHand(List.of(deck.drawCard(), deck.drawCard()));

        return gameRepository.save(game);
    }

    public Mono<Game> getGameById(String gameId) {
        return gameRepository.findById(gameId);
    }

    public Mono<Void> deleteGame(String gameId) {
        return gameRepository.deleteById(gameId);
    }

    public Mono<Game> playGame(String gameId, GameAction action) {
        return gameRepository.findById(gameId)
                .flatMap(game -> {
                    Deck deck = new Deck();
                    switch (action.getActionType()){
                        case "HIT":
                            game.getPlayerHand().add(deck.drawCard());
                            break;
                        case "STAND":
                            while (BlackjackUtils.calculateHandValue(game.getDealerHand()) < 17){
                                game.getDealerHand().add(deck.drawCard());
                            }
                            break;
                    }
                    game.setStatus(BlackjackUtils.checkGameResult(game));
                    return gameRepository.save(game);
                });
    }
}