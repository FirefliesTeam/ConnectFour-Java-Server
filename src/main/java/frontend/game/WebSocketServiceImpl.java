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


    /*
    *
    *
    *
    *
    * */
    @Override
    public void notifyMyTurn(GameUser user) {

    }

    @Override
    public void notifyEnemyTurn(GameUser user) {

    }

    @Override
    public void notifyTimer(GameUser user) {

    }

    @Override
    public void notifyGameOver(GameUser user) {

    }

    @Override
    public void notifyEndMyTurn(GameUser user) {

    }

    @Override
    public void notifyEndEnemyTurn(GameUser user) {

    }

    @Override
    public void notifyEnemyCome(GameUser user) {

    }

    /*
    public void registrationSocket(GameWebSocket user) {
        userSockets.put(user.getName(), user);
    }

    public void notifySetMyPoint(GameUser user) {
        userSockets.get(user.getName()).setMyScore(user);
    }

    public void notifySetEnemyPoint(GameUser user) {
        userSockets.get(user.getName()).setEnemyScore(user);
    }

    public void notifyStartGame(GameUser user) {
        GameWebSocket gameWebSocket = userSockets.get(user.getMyName());
        gameWebSocket.startGame(user);
    }

    @Override
    public void notifyGameOver(GameUser user, boolean win) {
        userSockets.get(user.getMyName()).gameOver(user, win);
    }
    */
}
