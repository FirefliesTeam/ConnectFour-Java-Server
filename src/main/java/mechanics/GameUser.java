package mechanics;

public class GameUser {
    private String name;
    private String enemyName;  //А это надо?


    public GameUser(String name) {
        this.name = name;
    }

    public String getName() { return this.name; }

    public String getEnemyName() { return this.enemyName; }

    public void setEnemyName(String enemyName) { this.enemyName = enemyName; }

}
