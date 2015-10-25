package base;

import frontend.game.GameWebSocket;
import mechanics.GameUser;

public interface WebSocketService {
    void registerSocket(GameWebSocket user);

    void notifyEnemyConnect(GameUser user);

    void notifyConnectToRoom(GameUser user);

    void waitEnemy(String name);

    void notifyStartGame(GameUser user);

    void notifyStartRound(GameUser user, boolean isTurn);

    void notifyTurn(GameUser user, int column, boolean succesTurn);

    void notifyGameOver(GameUser user, String winner, int numRound);

    void notifyNextTurn(String name, boolean isTurn);

}
