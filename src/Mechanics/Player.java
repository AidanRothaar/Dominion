package Mechanics;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    int actions;
    int buys;
    int coins;
    private List<Integer> attacks;
    private List <Card> hand;
    private List <Card> deck;
    private List <Card> discard;

    

    public Player() {
        attacks = new ArrayList<>();
    }

    public void takeTurn() {
        resolveAttacks();
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

    public void draw(int i) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'draw'");
    }

    public void addCard(int i) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addCard'");
    }

    // Special Attacks
    public void resolveBandit() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'resolveBandit'");
    }

    public void resolveBureaucrat() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'resolveBureaucrat'");
    }

    public void resolveMilitia() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'resolveMillitia'");
    }
}
