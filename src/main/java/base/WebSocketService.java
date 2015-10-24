package base;

import frontend.game.GameWebSocket;
import mechanics.GameUser;

public interface WebSocketService {
    void registerSocket(GameWebSocket user);

    void notifyEnemyConnect(GameUser user);

    void notifyConnectToRoom(GameUser user);

    void waitEnemy(String name);

    void notifyStartGame(GameUser user);

    /**/
    //void notifyStartGame(GameUser user);

    void notifyMyTurn(GameUser user);

    void notifyEnemyTurn(GameUser user);

    void notifyTimer(GameUser user);

    void notifyGameOver(GameUser user);

    void notifyEndMyTurn(GameUser user);

    void notifyEndEnemyTurn(GameUser user);

    void notifyEnemyCome(GameUser user);

}
