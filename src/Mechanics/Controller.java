package Mechanics;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {
    private boolean gameOver;
    private List<Player> players;
    private Board board;
    private List<Integer> attacksToAdd;
    
    
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
            for (int i = 0; i <= players.size(); i++) {
                if (i != turn) {
                    players.get(i).addAttacks(attacksToAdd);
                }
            }
        }
    }

    public void runSetup() {
        Scanner input = new Scanner(System.in);
        int numPlayers = input.nextInt();
        board = new Board(numPlayers);
        players = new ArrayList<>();
        boolean cont = true;
        for (int i = 0; i < numPlayers; i++) {
            System.out.println("Input player name");
            players.add(new Player(input.nextLine(), board));
        }
        input.close();
       
    }
}
