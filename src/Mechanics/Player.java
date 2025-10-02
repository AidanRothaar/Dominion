package Mechanics;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

import Exceptions.BrokeException;
import Exceptions.InvalidIDException;

public class Player {
    private String name;
    private int actions;
    private int buys;
    private int coins;
    private int merchants;
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
        boardCards = new ArrayList<>();
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
        System.out.println(name + " turn");
        actions = 1;
        buys = 1;
        coins = 0;
        merchants = 0;
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
        boolean hasAction;
        while (actions > 0) {
            int count = 1;
            hasAction = false;
            for (Card c : hand) {
                System.out.println(count + ". " + c.getName());
                if (c.getType() == 3) {
                    hasAction = true;
                }
                count++;
            }
            if (hasAction) {
                System.out.println("You have " + actions + " actions");
                System.out.println("Which card would you like to play?");
                int cardtoplay = getInt();
                playcard(cardtoplay - 1);
            } else {
                System.out.println("You have no action cards");
                actions = 0;
            }
        }
    }

    private void playcard(int i) {
        Card c = hand.get(i);
        if (c.getType() != 3) {
            System.out.println("This is not an action card");
            return;
        }
        actions--;
        actions += c.getActions();
        buys += c.getBuys();
        coins += c.getCoins();
        if (c.hasEffect() == true) {
            doSpecialEffect(c.getID());
        }
        if (c.getDraw() > 0) {
            draw(c.getDraw());
        }
        boardCards.add(c);
        hand.remove(c);
    }

    private void doSpecialEffect(int i) {
        switch (i) {
            case 8:
                cellar();
                break;
            case 9:
                chapel();
                break;
            case 11:
                harbinger();
                break;
            case 12:
                merchant();
                break;
            case 13:
                vassal();
                break;
            case 15:
                workshop();
                break;
        }
    }

    private void cellar() {
        // TODO
        // User can discard any number of cards of their choice they then draw that many cards
    }

    private void chapel() {
        // TODO
        // User can choose up to 4 cards from their hand to permanantly remove from their deck
    }

    private void harbinger() {
        // TODO
        // Lets the User choose a card from the discard and puts it on top of the deck. 
        // If the discard is empty does nothing. 
        // User can decline
    }

    private void merchant() {
        merchants++;
    }

    private void vassal() {
        // TODO
        // if the top card of the deck is an action card the user may play it or discard it
        // otherwise discard it
    }

    private void workshop() {
        // TODO
        // add a card costing 4 or less into the discard 
        // remove it from the supply pile
    }




    //Buys
    private void buyPhase() {
        for (Card c : hand) {
            if (c.getType() == 1) {
                int val = c.getCoins();
                if (val == 2) {
                    coins += merchants;
                    merchants = 0;
                }
                coins += val;
            }
        }
        while (buys > 0) {
            System.out.println("You have "+ buys + " buys and " + coins + " coins to spend. Would you like to buy another card? y/n");
            String answer = getLine();
            if (answer.toLowerCase().equals("y")) {
                for (Card c : board.getCardList()) {
                    System.out.println(c.getName()+ " costs " + c.getCost() + " ID: " + c.getID());
                }
                System.out.println("Which card would you like to buy? (type the id)");
                int idToBuy = input.nextInt();
                if (addCard(idToBuy)) {
                    buys -= 1;
                    System.out.println("You purchased " + discard.get(discard.size()-1).getName());
                } 
            } else {
                buys = 0;
            }

        }
    }

    // Clean Up
    private void cleanupPhase() {
        discard(hand.size());
        if (boardCards.size() > 0){
            for (Card c : boardCards) {
                discard.add(c);
            }
        }
        boardCards.clear();
        draw(5);
    }

    // Deck management methods
    private void draw(int i) {
        for (int x = 0; x < i; x++) {
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
            System.out.println("You dont have enough coins");
            return false;
        } catch(InvalidIDException e) {
            System.out.println("That id is invalid");
            return false;
        }
        Card newCard = new Card(i);
        coins -= newCard.getCost();
        discard.add(newCard);
        return true;
    }

    private void discard(int i) {
        if (i >= hand.size()) {
            for (Card c : hand) {
                discard.add(c);
            }
            hand.clear();
            return;
        }

        // TODO add discard with choice
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

    private int getInt() {
        while (true) {
            try {
                int i = input.nextInt();
                input.nextLine();
                return i;
            } catch (NoSuchElementException e) {
                System.out.println("Please enter a valid integer.");
                input.nextLine(); // Clear the invalid input
            }
        }
    }
    private String getLine() {
        while (true) {
            try {
                String s = input.nextLine();
                return s;
            } catch (NoSuchElementException e) {
                System.out.println("Please enter a valid line.");
                input.nextLine();
            }
        }
    }
}
