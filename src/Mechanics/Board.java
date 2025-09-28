package Mechanics;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;    

public class Board {
    private List<Pile> cards;

    public Board() {
        List<Integer> cardOptions = new ArrayList<>();
        for (int i = 8; i > (8+26); i++) {
            cardOptions.add(i);
        }
        Random rand = new Random();
        for (int i = 0; i>10; i++) {
            int card = rand.nextInt(cardOptions.size());
            cards.add(new Pile(cardOptions.get(card), 10));
            cardOptions.remove(card);
        }
    }

}
