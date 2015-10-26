package frontend.game;

import base.GameMechanics;
import base.WebSocketService;
import mechanics.GameUser;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

@WebSocket
public class GameWebSocket {
    private String name;
    private Session session;
    private WebSocketService webSocketService;
    private GameMechanics gameMechanics;

    public GameWebSocket(String name, GameMechanics gameMechanics, WebSocketService webSocketService) {
        this.name = name;
        this.gameMechanics = gameMechanics;
        this.webSocketService = webSocketService;
    }

    public String getName() { return name; }

    public void setSession(Session session) {this.session = session; }

    public Session getSession() { return session; }

    public void connectEnemy(GameUser user) {
        try {
            JSONObject jsonMessage = new JSONObject();
            jsonMessage.put("status", "enemyConnected");
            jsonMessage.put("enemyName", user.getEnemyName());
            session.getRemote().sendString(jsonMessage.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void connectRoom(GameUser user) {
        try {
            JSONObject jsonMessage = new JSONObject();
            jsonMessage.put("status", "connectToRoom");
            jsonMessage.put("enemyName", user.getEnemyName());
            session.getRemote().sendString(jsonMessage.toString());
        } catch(Exception e) {
            System.out.print(e.toString());
        }
    }

    public void waitEnemy(String userName) {
        try {
            JSONObject jsonMessage = new JSONObject();
            jsonMessage.put("status", "wait");
            jsonMessage.put("myName", userName);
            session.getRemote().sendString(jsonMessage.toString());
        } catch(Exception e) {
            System.out.print(e.toString());
        }
    }

    public void startGame(GameUser user) {
        try {
            JSONObject jsonMessage = new JSONObject();
            jsonMessage.put("status", "startGame");
            jsonMessage.put("myName", user.getName());
            jsonMessage.put("enemyName", user.getEnemyName());
            session.getRemote().sendString(jsonMessage.toString());
        } catch(Exception e) {
            System.out.print(e.toString());
        }
    }

    public void startRound(GameUser user, boolean isTurn) {
        try {
            JSONObject jsonMessage = new JSONObject();
            jsonMessage.put("status", "startRound");
            jsonMessage.put("isMyTurn", isTurn);
            session.getRemote().sendString(jsonMessage.toString());
        } catch(Exception e) {
            System.out.print(e.toString());
        }
    }

    public void makeTurn(GameUser user, int column, boolean succesTurn) {
        try {
            JSONObject jsonMessage = new JSONObject();
            jsonMessage.put("status", "makeTurn");
            jsonMessage.put("succesTurn", succesTurn);
            jsonMessage.put("column", column);
            session.getRemote().sendString(jsonMessage.toString());
        } catch(Exception e) {
            System.out.print(e.toString());
        }
    }

    public void gameOver(String winner, int numRound) {
        try {
            JSONObject jsonMessage = new JSONObject();
            jsonMessage.put("status", "gameOver");
            jsonMessage.put("winner", winner);
            jsonMessage.put("runRound", numRound);
            session.getRemote().sendString(jsonMessage.toString());
        } catch(Exception e) {
            System.out.print(e.toString());
        }
    }

    public void nextTurn(boolean isTurn) {
        try {
            JSONObject jsonMessage = new JSONObject();
            jsonMessage.put("status", "nextTurn");
            jsonMessage.put("turn", isTurn);
            session.getRemote().sendString(jsonMessage.toString());
        } catch(Exception e) {
            System.out.print(e.toString());
        }
    }

    @OnWebSocketMessage
    public void onMessage(String data) {
        //gameMechanics.incrementScore(myName);
        try {
            JSONObject jsonMessage = new JSONObject(data);
            String status = jsonMessage.getString("status");
            String name = jsonMessage.getString("name");
            if(status.equals("newGame")) {
                gameMechanics.registerUser(name);
            }
            if(status.equals("joinGame")) {
                String nameEnemy = jsonMessage.getString("roomHolder");
                gameMechanics.selectGame(name, nameEnemy);
            }

        } catch(Exception e) {
            System.out.print(e.toString());
        }
    }

    @OnWebSocketConnect
    public void onOpen(Session session) {
        this.session = session;
        webSocketService.registerSocket(this);
        gameMechanics.registerUser(name);
    }

    @OnWebSocketClose
    public void onClose(int statusCode, String reason) {

    }
}
