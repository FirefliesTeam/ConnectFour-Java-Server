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

import javax.jws.WebService;
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
            session.getRemote().sendString(jsonMessage.toString());
        } catch(Exception e) {
            System.out.print(e.toString());
        }
    }

    public void startGame(GameUser user, boolean isTurn) {
        try {
            JSONObject jsonMessage = new JSONObject();
            jsonMessage.put("status", "startGame");
            jsonMessage.put("enemyName", user.getEnemyName());
            jsonMessage.put("chipColor", user.getPlayerColorStr());
            jsonMessage.put("isMyTurn", isTurn);
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

    /*
    //В этом методе отправляется заполненная ячейка?
    public void changeTurn(GameUser user, int collumn, boolean succesTurn) {
        try {
            JSONObject jsonMessage = new JSONObject();
            jsonMessage.put("status", "changeTurn");

            //Что это?
            jsonMessage.put("succesTurn", succesTurn);

            jsonMessage.put("collumn", collumn);

            session.getRemote().sendString(jsonMessage.toString());
        } catch(Exception e) {
            System.out.print(e.toString());
        }
    }
    */
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

    //Когда происходит сена очереди хода
    // Нужно передать фронэнду номер заполненной ячейки
    public void nextTurn(boolean isTurn, int filledCell) {
        try {
            JSONObject jsonMessage = new JSONObject();
            jsonMessage.put("status", "changeTurn");
            jsonMessage.put("isMyTurn", isTurn);
            jsonMessage.put("filledCell", filledCell);
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
            if (status.equals("connectToRoom")) {
                //Имя игрока, создавшего комнату
                String roomHolderName = jsonMessage.getString("roomHolder");
            }
            if (status.equals("ready")) {
                //WebService.continueGame();
            }
            if (status.equals("playAgain")) {
                //WebService.continueGame();
            }
        } catch(Exception e) {
            System.out.print(e.toString());
        }



    }

    @OnWebSocketConnect
    public void onOpen(Session _session) {
        this.session = _session;
        webSocketService.registerSocket(this);
        gameMechanics.registerUser(name);
    }

    @OnWebSocketClose
    public void onClose(int statusCode, String reason) {

    }
}
