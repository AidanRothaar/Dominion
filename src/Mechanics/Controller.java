package Mechanics;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {
    private boolean gameOver;
    private List<Player> players;
    private Board board;
    private List<Integer> attacksToAdd;
    private Scanner input;
    
    
    public Controller() {
        gameOver = false;
    }

    public void runGame() {
        runSetup();
        int turn = 0;
        while (!gameOver) {
            if(turn > players.size() - 1) {
                turn = 0;
            }
            attacksToAdd = players.get(turn).takeTurn();
            for (int i = 0; i < players.size(); i++) {
                if (i != turn) {
                    players.get(i).addAttacks(attacksToAdd);
                }
            }
            if (board.isGameOver()) {
                gameOver = true;
            }
            turn++;
        }
        int maxVP = 0;
        Player winner = null;
        for (Player p : players) {
            if (p.getVP() > maxVP) {
                maxVP = p.getVP();
                winner = p;
            }
            System.out.println("Player " + p.getName() + " had " + p.getVP() + " Victory Points");
        }
        System.out.println("Player " + winner.getName() + " won with " + maxVP + " Victory Points");
        input.close();
    }

    public void runSetup() {
        input = new Scanner(System.in);
        int numPlayers = input.nextInt();
        input.nextLine();
        board = new Board(numPlayers);
        players = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            String name = "Player " + (i+1);
            players.add(new Player(name, board));
        }
       
    }
}
