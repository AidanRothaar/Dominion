package Mechanics;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import Exceptions.BrokeException;

public class Player {
    private String name;
    private int actions;
    private int buys;
    private int coins;
    private List<Integer> attacks;
    private List<Card> hand;
    private List<Card> deck;
    private List<Card> boardCards;
    private List<Card> discard;
    private List<Integer> outgoingAttacks;
    private Board board;
    private Scanner input;

    

    public Player(String name, Board b) {
        this.name = name;
        board = b;
        coins = 6;
        input = new Scanner(System.in);
        outgoingAttacks = new ArrayList<>();
        attacks = new ArrayList<>();
        deck = new ArrayList<>();
        hand = new ArrayList<>();
        discard = new ArrayList<>();
        for (int i = 0; i < 7; i++){
            addCard(1);
        }
        for (int i = 0; i < 3; i++){
            addCard(4);
        }
        draw(5);
    }

    public List<Integer> takeTurn() {
        turnSetup();
        resolveAttacks();
        actionPhase();
        buyPhase();
        cleanupPhase(); 
        return outgoingAttacks;
    }

    // Turn Setup
    private void turnSetup() {
        actions = 1;
        buys = 1;
        coins = 0;
        
    }

    // Attacks
    public void addAttacks(List<Integer> attack) {
        for (int i : attack) {
            attacks.add(i);
        }
    }

    private void resolveAttacks() {
        boolean moat = false;
        for(Card c : hand){
            if (c.getName() == "Moat") {
                moat = true;
            }
        }
        if (moat == false) {
            for (int i : attacks) {
                doImpact(i);
            }
        }
        attacks.clear();
    }

    // 1 = Millitia
    // 2 = Bureaucrat
    // 3 = Bandit
    // 4 = Witch
    // 5 = Council Room
    private void doImpact(int attackID) {
        switch (attackID) {
            case 1:
                this.resolveMilitia();
                break;
            case 2:
                this.resolveBureaucrat();
                break;
            case 3:
                this.resolveBandit();
                break;
            case 4:
                this.addCard(7); //CURSE
                break;
            case 5:
                this.draw(1);
        }
    }

    private void resolveBandit() {
        if (deck.size() < 2) {
            shuffle();
        }

        if (deck.get(0).getType() == 1 && deck.get(0).getCoins() > 1 && deck.size() > 0) {
            deck.remove(0);
        } else if (deck.get(1).getType() == 1 && deck.get(1).getCoins() > 1 && deck.size() > 1){
            deck.remove(1);
        }
    }

    private void resolveBureaucrat() {
        for (Card c : hand) {
            if (c.getType() == 2) {
                deck.add(0, c);
                hand.remove(c);
                return;
            }
        }
    }

    private void resolveMilitia() {
        discard(hand.size() - 3);
    }

    // Actions
    private void actionPhase(){
        // TODO
        throw new UnsupportedOperationException("Unimplemented method 'actionPhase'");
    }

    //Buys
    private void buyPhase() {
        while (buys > 0) {
            System.out.println("You have "+ buys + " buys. Would you like to buy another card? y/n");
            String answer = input.nextLine();
            if (answer.toLowerCase() == "y") {
                System.out.println("You have " + coins + " coins to spend");
                for (Card c : board.getCardList()) {
                    System.out.println(c.getName()+ " costs " + c.getCost() + " ID: " + c.getID());
                }
                System.out.println("Which card would you like to buy? (type the id)");
                int idToBuy = input.nextInt();
                if (addCard(idToBuy)) {
                    buys -= 1;
                } else {
                    System.out.println("You dont have enough money");
                }
            }

        }
    }

    // Clean Up
    private void cleanupPhase() {
        discard(hand.size());
        for (Card c : boardCards) {
            discard.add(c);
            boardCards.remove(c);
        }
        draw(5);
    }

    // Deck management methods
    private void draw(int i) {
        for (int x = 0; x > i; x++) {
            if (deck.isEmpty()) {
                shuffle();
            }
            hand.add(deck.get(0));
            deck.remove(0);
        }
        
    }

    private boolean addCard(int i) {
        try {
            board.takeCard(i, coins);
        } catch(BrokeException e) {
            return false;
        }
        Card newCard = new Card(i);
        coins -= newCard.getCost();
        discard.add(newCard);
        return true;
    }

    private void discard(int i) {
        // TODO
        throw new UnsupportedOperationException("Unimplemented method 'discard'");
    }

    private void shuffle() {
        Random rand = new Random();
        int randInt;
        while(discard.size() > 0) {
            randInt = rand.nextInt(discard.size());
            Card c = discard.get(randInt);
            deck.add(c);
            discard.remove(randInt);
        }
    }

    // Player management methods
    public String getName() {
        return name;
    }

    public int getVP() {
        int victoryPoints = 0;
        for (Card c : hand) {
            discard.add(c);
        }
        for (Card c : deck) {
            discard.add(c);
        }
        for (Card c : discard) {
            if (c.getType() == 2) {
                victoryPoints += c.getVP(discard.size());
            }
        }
        return victoryPoints;
    }
}
