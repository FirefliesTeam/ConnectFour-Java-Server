package mechanics;

import base.GameMechanics;
import base.WebSocketService;
import utils.TimeHelper;

import java.util.*;

public class GameMechanicsImpl implements GameMechanics {
    private static final int STEP_TIME = 100;

    private static final int GAME_TIME = 15 * 1000;

    private static final int ROUND_TIME = 60 * 1000;

    private WebSocketService webSocketService;

    private Map<String, GameSession> nameToGame = new HashMap<>();

    private Set<GameSession> allSessions = new HashSet<>();

    private List<String> waiters = new LinkedList<>();

    public GameMechanicsImpl(WebSocketService webSocketService) {
        this.webSocketService = webSocketService;
    }

    // Добавляем регистрируем игрока в механике и ждем выбора
    public void registerUser(String user) {
        waiters.add(user);
    }

    // Игрок сделал выбор присоедиться к игре или создать новую
    public void selectGame(String user, String toUser) {
        if(toUser != "") {
            waiters.remove(toUser);
            GameSession newGameSession = new GameSession(toUser, user);
            allSessions.add(newGameSession);
            nameToGame.put(user, newGameSession);
            nameToGame.put(toUser, newGameSession);
            webSocketService.notifyEnemyConnect(newGameSession.getGameUserByName(toUser));
            webSocketService.notifyConnectToRoom(newGameSession.getGameUserByName(user));
        } else {
            waiters.add(user);
            webSocketService.waitEnemy(user);
        }
    }

    // Установка готовности игрока/игроков
    public void readyPlayer(String user, String isReadyStr) {
        boolean isReady = isReadyStr.equals("true");
        nameToGame.get(user).setPlayerReady(user, isReady);
    }

    // Если игроки готовы начинается игра
    public void startGame(GameSession session) {
        session.startGame();
        webSocketService.notifyStartGame(session.getFirstPlayer());
        webSocketService.notifyStartGame(session.getSecondPlayer());
    }

    @Override
    public void run() {
        while (true) {
            gmStep();
            TimeHelper.sleep(STEP_TIME);
        }
    }

    private void gmStep() {
        for (GameSession session : allSessions) {
            if (session.getRoundTime() > ROUND_TIME) {

            }
        }
    }


    private void starGame(String first, String second) {
        GameSession gameSession = new GameSession(first, second);
        allSessions.add(gameSession);
        nameToGame.put(first, gameSession);
        nameToGame.put(second, gameSession);

        //webSocketService.notifyStartGame(gameSession.getFirstPlayer());
        //webSocketService.notifyStartGame(gameSession.getSecondPlayer());
        /*
        String second = waiter;
        GameSession gameSession = new GameSession(first, second);
        allSessions.add(gameSession);
        nameToGame.put(first, gameSession);
        nameToGame.put(second, gameSession);

        webSocketService.notifyStartGame(gameSession.getSelf(first));
        webSocketService.notifyStartGame(gameSession.getSelf(second));
         */
    }

}
