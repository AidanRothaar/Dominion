package Mechanics;

public class Card {
    String name;
    int type;
    // 1 = treasure
    // 2 = victory
    // 3 = action
    int draw;
    int actions;
    int coins;
    int victory;
    boolean specialEffect;

    public Card(int i) {
        switch(i) {
            case 1:
                name = "Copper";
                type = 1;
                draw = 0;
                actions = 0;
                coins = 1;
                victory = 0;
                specialEffect = false;
                break;
            case 2:
                name = "Silver";
                type = 1;
                draw = 0;
                actions = 0;
                coins = 2;
                victory = 0;
                specialEffect = false;
                break;
            case 3:
                name = "Gold";
                type = 1;
                draw = 0;
                actions = 0;
                coins = 3;
                victory = 0;
                specialEffect = false;
                break;
            case 4:
                name = "Estate";
                type = 2;
                draw = 0;
                actions = 0;
                coins = 0;
                victory = 1;
                specialEffect = false;
                break;
            case 5:
                name = "Duchy";
                type = 2;
                draw = 0;
                actions = 0;
                coins = 0;
                victory = 3;
                specialEffect = false;
                break;
            case 6:
                name = "Province";
                type = 2;
                draw = 0;
                actions = 0;
                coins = 0;
                victory = 6;
                specialEffect = false;
                break;
            case 7:
                name = "Curse";
                type = 2;
                draw = 0;
                actions = 0;
                coins = 0;
                victory = -1;
                specialEffect = false;
                break;
                
        }
    }
}
