package base;

import frontend.game.GameWebSocket;
import mechanics.GameUser;

public interface WebSocketService {
    void registerSocket(GameWebSocket user);

    void notifyEnemyConnect(GameUser user, boolean isTurn);

    void notifyConnectToRoom(GameUser user, boolean isTurn);

    void waitEnemy(String name);

    void notifyStartGame(GameUser user);

    void notifyStartRound(GameUser user, boolean isTurn);

    void notifyTurn(GameUser user, int column, boolean succesTurn);

    void notifyGameOver(GameUser user, String winner, int numRound);

    void notifyNextTurn(String name, boolean isTurn);

}
