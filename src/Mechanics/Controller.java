package Mechanics;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {
    private boolean gameOver;
    private List<Player> players;
    private Board board;
    private Scanner input;


    public Controller() {
        gameOver = false;
        input = new Scanner(System.in);
    }

    public void runGame() {
        runSetup();
    }

    public void runSetup() {
        players = new ArrayList<>();
        board = new Board();
        boolean cont = true;
        do {
            players.add(new Player());
            if (players.size() >= 2) {
                System.out.println("Would you like to add another player? y/n");
                if (input.nextLine().toUpperCase() == "n") {
                    cont = false;
                }
            }
        } while(cont);
    }
}
