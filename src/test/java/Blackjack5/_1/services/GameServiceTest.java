package Blackjack5._1.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GameServiceTest {
    private final GameService gameService;

    @Autowired
    GameServiceTest(GameService gameService) {
        this.gameService = gameService;
    }

    @Test
    void newGame() {
    }

    @Test
    void getGameById() {
    }

    @Test
    void deleteGame() {
    }

    @Test
    void playGame() {
    }
}