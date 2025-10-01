package Mechanics;

public class Card {
    private String name;
    private int id;
    private int type;
    // 1 = treasure
    // 2 = victory
    // 3 = action
    private int draw;
    private int actions;
    private int coins;
    private int buys;
    private int cost;
    private int victory;
    private boolean specialEffect;

    public Card(int i) {
        id = i;
        switch(i) {
            case 1:
                name = "Copper";
                type = 1;
                draw = 0;
                actions = 0;
                coins = 1;
                buys = 0;
                cost = 0;
                victory = 0;
                specialEffect = false;
                break;
            case 2:
                name = "Silver";
                type = 1;
                draw = 0;
                actions = 0;
                coins = 2;
                buys = 0;
                cost = 3;
                victory = 0;
                specialEffect = false;
                break;
            case 3:
                name = "Gold";
                type = 1;
                draw = 0;
                actions = 0;
                coins = 3;
                buys = 0;
                cost = 6;
                victory = 0;
                specialEffect = false;
                break;
            case 4:
                name = "Estate";
                type = 2;
                draw = 0;
                actions = 0;
                coins = 0;
                buys = 0;
                cost = 2;
                victory = 1;
                specialEffect = false;
                break;
            case 5:
                name = "Duchy";
                type = 2;
                draw = 0;
                actions = 0;
                coins = 0;
                buys = 0;
                cost = 5;
                victory = 3;
                specialEffect = false;
                break;
            case 6:
                name = "Province";
                type = 2;
                draw = 0;
                actions = 0;
                coins = 0;
                buys = 0;
                cost = 8;
                victory = 6;
                specialEffect = false;
                break;
            case 7:
                name = "Curse";
                type = 2;
                draw = 0;
                actions = 0;
                coins = 0;
                buys = 0;
                cost = 0;
                victory = -1;
                specialEffect = false;
                break;
            case 8:
                name = "Cellar";
                type = 3;
                draw = 0;
                actions = 1;
                coins = 0;
                buys = 0;
                cost = 2;
                victory = 0;
                specialEffect = true;
                break;
            case 9:
                name = "Chapel";
                type = 3;
                draw = 0;
                actions = 0;
                coins = 0;
                buys = 0;
                cost = 2;
                victory = 0;
                specialEffect = true;
                break;
            case 10:
                name = "Moat";
                type = 3;
                draw = 0;
                actions = 1;
                coins = 0;
                buys = 0;
                cost = 2;
                victory = 0;
                specialEffect = false;
                break;
            case 11:
                name = "Harbinger";
                type = 3;
                draw = 1;
                actions = 1;
                coins = 0;
                buys = 0;
                cost = 3;
                victory = 0;
                specialEffect = true;
                break;
            case 12:
                name = "Merchant";
                type = 3;
                draw = 1;
                actions = 1;
                coins = 0;
                buys = 0;
                cost = 3;
                victory = 0;
                specialEffect = true;
                break;
            case 13:
                name = "Vassal";
                type = 3;
                draw = 0;
                actions = 0;
                coins = 2;
                buys = 0;
                cost = 3;
                victory = 0;
                specialEffect = true;
                break;
            case 14:
                name = "Village";
                type = 3;
                draw = 1;
                actions = 2;
                coins = 0;
                buys = 0;
                cost = 3;
                victory = 0;
                specialEffect = false;
                break;
            case 15:
                name = "Workshop";
                type = 3;
                draw = 0;
                actions = 0;
                coins = 0;
                buys = 0;
                cost = 3;
                victory = 0;
                specialEffect = true;
                break;
            //TODO add other cards
                
        }
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

    public int getVP(int Decksize) {
        if (this.name == "Garden") {
            return Decksize/10;
        }
        return victory;
    }

    public int getCoins() {
        return coins;
    }

    public int getCost() {
        return cost;
    }

    public int getActions() {
        return actions;
    }
    
    public int getBuys() {
        return buys;
    }

    public int getDraw() {
        return draw;
    }

    public boolean hasEffect() {
        return specialEffect;
    }

    public int getID() {
        return id;
    }

}
