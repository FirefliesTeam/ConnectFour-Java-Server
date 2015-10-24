package frontend.game;

import base.WebSocketService;
import mechanics.GameUser;

import java.util.HashMap;
import java.util.Map;

public class WebSocketServiceImpl implements WebSocketService {
    private Map<String, GameWebSocket> usersSockets = new HashMap<>();

    @Override
    public void registerSocket(GameWebSocket userSocket) { usersSockets.put(userSocket.getName(), userSocket); }

    @Override
    public void notifyEnemyConnect(GameUser user) {
        usersSockets.get(user.getName()).connectEnemy(user);
    }

    @Override
    public void notifyConnectToRoom(GameUser user) {
        usersSockets.get(user.getName()).connectRoom(user);
    }

    @Override
    public void waitEnemy(String name) {
        usersSockets.get(name).waitEnemy(name);
    }

    @Override
    public void notifyStartGame(GameUser user) {
        usersSockets.get(user.getName()).startGame(user);
    }

    @Override
    public void notifyStartRound(GameUser user, boolean isTurn) {
        usersSockets.get(user.getName()).startRound(user, isTurn);
    }

    @Override
    public void notifyTurn(GameUser user, int column, boolean succesTurn) {
        usersSockets.get(user.getName()).makeTurn(user, column, succesTurn);
    }

    @Override
    public void notifyGameOver(GameUser user, String winner, int numRound) {
        usersSockets.get(user.getName()).gameOver(winner, numRound);
        usersSockets.get(user.getEnemyName()).gameOver(winner, numRound);
    }

    @Override
    public void notifyNextTurn(String name, boolean isTurn) {
        usersSockets.get(name).nextTurn(isTurn);
    }

}
