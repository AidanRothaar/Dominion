package Mechanics;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    int actions;
    int buys;
    int coins;
    private List<Integer> attacks;
    private List<Card> hand;
    private List<Card> deck;
    private List<Card> discard;
    private List<Integer> outgoingAttacks;
    private Board board;

    

    public Player(String name, Board b) {
        this.name = name;
        board = b;
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
    public void turnSetup() {

    }

    // Attacks
    public void addAttacks(List<Integer> attack) {
        for (int i : attack) {
            attacks.add(i);
        }
    }

    public void resolveAttacks() {
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
    public void doImpact(int attackID) {
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

    public void resolveBandit() {
        // TODO
        throw new UnsupportedOperationException("Unimplemented method 'resolveBandit'");
    }

    public void resolveBureaucrat() {
        // TODO
        throw new UnsupportedOperationException("Unimplemented method 'resolveBureaucrat'");
    }

    public void resolveMilitia() {
        // TODO
        throw new UnsupportedOperationException("Unimplemented method 'resolveMillitia'");
    }

    // Actions
    public void actionPhase(){
        // TODO
        throw new UnsupportedOperationException("Unimplemented method 'actionPhase'");
    }

    //Buys
    public void buyPhase() {
        // TODO
        throw new UnsupportedOperationException("Unimplemented method 'buyPhase'");
    }

    // Clean Up
    public void cleanupPhase() {
        // TODO
        throw new UnsupportedOperationException("Unimplemented method 'cleanupPhase'");
    }

    // Deck management methods
    public void draw(int i) {
        for (int x = 0; x > i; x++) {
            if (deck.isEmpty()) {
                shuffle();
            }
            hand.add(deck.get(0));
            deck.remove(0);
        }
        
    }

    public void addCard(int i) {
        board.takeCard(i);
        discard.add(new Card(i));
    }

    public void discard(int i) {
        // TODO
        throw new UnsupportedOperationException("Unimplemented method 'discard'");
    }

    public void discardChoice(int i, boolean optional) {
        // TODO
        throw new UnsupportedOperationException("Unimplemented method 'discardChoice'");
    }

    public void shuffle() {
        // TODO
        throw new UnsupportedOperationException("Unimplemented method 'shuffle'");
    }
}
