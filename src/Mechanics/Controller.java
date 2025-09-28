package Mechanics;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {
    private boolean gameOver;
    private List<Player> players;
    private Board board;


    public Controller() {
        gameOver = false;
    }

    public void runGame() {
        runSetup();
    }

    public void runSetup() {
        Scanner input = new Scanner(System.in);
        players = new ArrayList<>();
        boolean cont = true;
        do {
            System.out.println("Input player name");
            players.add(new Player(input.nextLine()));
            if (players.size() >= 2) {
                System.out.println("Would you like to add another player? y/n");
                if (input.nextLine().toUpperCase() == "N") {
                    cont = false;
                }
            }
        } while(cont);
        input.close();
        board = new Board(players.size());
    }
}
