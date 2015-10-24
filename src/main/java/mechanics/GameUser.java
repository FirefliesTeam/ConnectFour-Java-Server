package mechanics;

import java.util.Random;

public class GameUser {
    public static final int RED_COLOR = 1;
    public static final int BLUE_COLOR = 2;

    private String name;
    private int playerColor;
    private boolean myTurn;

    private String enemyName;


    public GameUser(String name) {
        this.name = name;
    }

    public String getName() { return this.name; }

    public String getEnemyName() { return this.enemyName; }

    public void setEnemyName(String enemyName) { this.enemyName = enemyName; }

    public void setRandomColorToMe() {
        Random random = new Random();
        playerColor = random.nextInt(1) + 1;
    }

    public int getPlayerColor() { return playerColor; }

    public int getEnemyColor() {
        if(playerColor == RED_COLOR) {
            return BLUE_COLOR;
        } else {
            return RED_COLOR;
        }
    }

    public void setColorToMe(int color) { this.playerColor = color; }

    public void setMyTurn() { this.myTurn = true; }

    public void setEnemyTurn() { this.myTurn = false;  }

    public boolean getTurn() { return myTurn; }

}
