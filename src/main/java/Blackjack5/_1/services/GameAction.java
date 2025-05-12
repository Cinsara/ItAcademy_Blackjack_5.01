package Blackjack5._1.services;

import Blackjack5._1.model.Card;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameAction {
    private String actionType;
    private Card card;
    private Integer betAmount;
}
