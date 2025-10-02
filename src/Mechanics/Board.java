package Mechanics;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Exceptions.BrokeException;
import Exceptions.InvalidIDException;

public class Board {
    private int[][] cards;
    private int numPlayers;
    private List<Card> cardList;

    public Board(int i) {
        numPlayers = i;
        cards = new int[17][2];
        cardList = new ArrayList<>();
        addGuarenteedCards();
        addKingdomCards();
    }

    private void addGuarenteedCards() {
        for (int i = 1; i <= 7; i++)  {
            cards[i-1][0] = i;
            cardList.add(new Card(i));
            if (i < 4) {
                cards[i-1][1] = 60;
            } else if (i < 7 && numPlayers == 2) {
                cards[i-1][1] = 8;
            } else if (i < 7) {
                cards[i-1][1] = 12;
            } else {
                cards[i-1][1] = 10*(numPlayers-1);
            }
        }
    }
    
    private void addKingdomCards() {
        List<Integer> cardOptions = new ArrayList<Integer>();
        for (int i = 8; i < (8+26); i++) {
            cardOptions.add(i);
        }
        for (int i = 0; i < 10; i++) {
            Random rand = new Random();
            int num = rand.nextInt(cardOptions.size());
            cards[7+i][0] = cardOptions.get(num);
            cardList.add(new Card(cardOptions.get(num)));
            cardOptions.remove(num);
            cards[7+i][1] = 10;
        }
    }

    public int takeCard(int i, int coins) throws BrokeException, InvalidIDException {
        boolean idExists = false;
        for(int num = 0; num < 17; num++) {
            if (cards[num][0] == i) {
                idExists = true;
            }
        }
        if(!idExists) {
            throw new InvalidIDException();
        }
        for (int num = 0; num < 17; num++){
            if (cards[num][0] == i && cardList.get(num).getCost() <= coins) {
                cards[num][1] -= 1;
                if (cards[num][1] == 0) {
                    for (Card c : cardList) {
                        if (c.getID() == i) {
                            cardList.remove(c);
                            break;
                        }
                    }
                }
                return coins - cardList.get(num).getCost();
            }
        }
        throw new BrokeException();
    }

    public boolean isGameOver() {
        int emptyPiles = 0;
        for (int i = 0; i < 17; i++) {
            if (cards[i][1] == 0) {
                emptyPiles += 1;
            }
        }
        if (emptyPiles >= 3 || cards[5][1] == 0) {
            return true;
        }
        return false;
    }

    public List<Card> getCardList() {
        return cardList;
    }

}
