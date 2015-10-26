package mechanics;

import java.util.Date;

public class GameSession {
    private long startSession;
    private long startRoundTime;
    private boolean inGame;
    private int emptyCells;
    private int numberRound;

    private final GameUser firstPlayer;
    private final GameUser secondPlayer;
    private boolean turnFirstPlayer;
    private boolean firstTurnFirstPlayerInLastRound;
    private boolean firstPlayerReady;
    private boolean secondPlayerReady;

    private final int MARK_FIRST_PLAYER = 1;
    private final int MARK_SECOND_PLAYER = 2;

    private static final int COLUMNS = 7;
    private static final int ROWS = 6;

    private int lastSetPointPosition;

    private int[] gameField = new int[ROWS * COLUMNS];

    //Зачем они нужны?
    //private Map<String, GameUser> users = new HashMap<>();

    public GameSession(String first, String second) {
        startSession = new Date().getTime();
        startRoundTime = 0;
        inGame = false;

        GameUser firstPlayer = new GameUser(first);
        firstPlayer.setRandomColorToMe();
        if(firstPlayer.getPlayerColor() == GameUser.BLUE_COLOR) {
            turnFirstPlayer = true;
        } else {
            turnFirstPlayer = false;
        }
        firstPlayer.setEnemyName(second);

        GameUser secondPlayer = new GameUser(second);
        secondPlayer.setColorToMe(firstPlayer.getEnemyColor());
        secondPlayer.setEnemyName(first);

        //users.put(first, firstPlayer);
        //users.put(second, secondPlayer);

        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;

        firstPlayerReady = false;
        secondPlayerReady = false;

        emptyCells = ROWS * COLUMNS;
        numberRound = 1;

        lastSetPointPosition = -1;

        for(int i = 0; i < ROWS; ++i) {
            for(int j = 0; j < COLUMNS; ++j) {
                gameField[COLUMNS * i + j] = 0;
            }
        }
    }

    public void setCurrectTimeToRound() { startRoundTime = new Date().getTime(); }

    public void nextTurn() {
        //startRound = new Date().getTime();
        setCurrectTimeToRound();
        //changeTurn();
        turnFirstPlayer = !turnFirstPlayer;
    }

    public void startRound() {
        turnFirstPlayer = !firstTurnFirstPlayerInLastRound;
        setCurrectTimeToRound();
        for(int i = 0; i < ROWS; ++i) {
            for(int j = 0; j < COLUMNS; ++j) {
                gameField[COLUMNS * i + j] = 0;
            }
        }
        emptyCells = ROWS * COLUMNS;
    }

    public boolean isTurnFirstPlayer() { return turnFirstPlayer; }

    public boolean isTurnByName(String name) {
        if(firstPlayer.getName().equals(name)) {
            return turnFirstPlayer;
        } else {
            return !turnFirstPlayer;
        }
    }

    public int getRound() { return numberRound; }

    public void incrementRound() { ++numberRound; }

    public GameUser getGameUserByName(String name) {
        if(name.equals(firstPlayer.getName())) {
            return firstPlayer;
        } else {
            return secondPlayer;
        }
    }

    public boolean isFirstPlayerReady() { return firstPlayerReady; }

    public boolean isSecondPlayerReady() { return secondPlayerReady; }

    public void setPlayerReady(String name, boolean isReady) {
        if(name.equals(firstPlayer.getName())) {
            this.firstPlayerReady = isReady;
        } else {
            this.secondPlayerReady = isReady;
        }
    }

    public boolean isSessionReady() {
        return firstPlayerReady && secondPlayerReady;
    }

    public  void setNotReady() {
        firstPlayerReady = false;
        secondPlayerReady = false;
    }

    public void startGame() {
        this.inGame = true;
        firstTurnFirstPlayerInLastRound = turnFirstPlayer = firstPlayer.getPlayerColor() == GameUser.BLUE_COLOR;
    }

    public boolean isInGame() { return this.inGame; }

    /*
    public void setFirstPlayerReady() { this.firstPlayerReady = true; }

    public void setFirstPlayerNotReady() { this.firstPlayerReady = false; }

    public void setSecondPlayerReady() { this.secondPlayerReady = true; }

    public void setSecondPlayerNotReady() { this.secondPlayerReady = false; }
    */

    public long getSessionTime() { return new Date().getTime() - startSession; }

    public long getRoundTime() { return new Date().getTime() - startRoundTime; }

    public GameUser getFirstPlayer() { return firstPlayer; }

    public GameUser getSecondPlayer() { return secondPlayer; }

    public boolean setPointFirstPlayer(int i, int j) {
        --emptyCells;
        return setPoint(i, j, MARK_FIRST_PLAYER);
    }

    public boolean setPointSecondPlayer(int i, int j) {
        --emptyCells;
        return setPoint(i, j, MARK_SECOND_PLAYER);
    }

    public boolean setPointFirstPlayerByColumn(int j) {
        --emptyCells;
        return setPointByColumn(j, MARK_FIRST_PLAYER);
    }

    public boolean setPointSecondPlayerByColumn(int j) {
        --emptyCells;
        return setPointByColumn(j, MARK_SECOND_PLAYER);
    }

    public boolean isFullTable() {
        return emptyCells < 1;
    }

    public boolean isFirstWin() {
        return isWin(MARK_FIRST_PLAYER);
    }

    public boolean isSecondWin() {
        return isWin(MARK_SECOND_PLAYER);
    }

    private boolean setPoint(int i, int j, int mark) {
        if(gameField[COLUMNS * i + j] == 0) {
            gameField[COLUMNS * i + j] = mark;
            return true;
        } else {
            return false;
        }
    }

    private boolean setPointByColumn(int j, int mark) {
        int i = 0;
        while (i < ROWS && gameField[COLUMNS * i + j] == 0) {
            ++i;
        }
        --i;
        if(i >= 0) {
            gameField[COLUMNS * i + j] = mark;
            this.lastSetPointPosition = COLUMNS * i + j;
            return true;
        } else {
            return false;
        }
    }

    public int getLastSetPointPosition() {  return lastSetPointPosition; }

    private boolean isWin(int mark) {
        int countPoints = 0;
        for(int i = 0; i < ROWS; ++i) {
            countPoints = 0;
            for(int j = 0; j < COLUMNS; ++j) {
                if (gameField[COLUMNS * i + j] == mark) {
                    ++countPoints;
                } else {
                    countPoints = 0;
                }
                if(countPoints == 4) {
                    return true;
                }
            }
        }

        for(int j = 0; j < COLUMNS; ++j) {
            countPoints = 0;
            for(int i = 0; i < ROWS; ++i) {
                if (gameField[COLUMNS * i + j] == mark) {
                    ++countPoints;
                } else {
                    countPoints = 0;
                }
                if(countPoints == 4) {
                    return true;
                }
            }
        }

        for(int i = 0; i < ROWS - 3; ++i) {
            for (int j = 0; j < COLUMNS - 2; ++j) {
                countPoints = 0;
                for (int h = 0, g = 0; h < 4 && COLUMNS * i + j + g < ROWS * COLUMNS; ++h, g += COLUMNS + 1) {
                    if (gameField[COLUMNS * i + j + g] == mark) {
                        ++countPoints;
                        if (countPoints == 4) {
                            return true;
                        }
                    }
                }
            }
        }

        for(int i = 0; i < ROWS - 3; ++i) {
            for (int j = 3; j < COLUMNS; ++j) {
                countPoints = 0;
                for (int h = 0, g = 0; h < 4 && COLUMNS * i + j + g < ROWS * COLUMNS; ++h, g += COLUMNS - 1) {
                    if (gameField[COLUMNS * i + j + g] == mark) {
                        ++countPoints;
                        if (countPoints == 4) {
                            return true;
                        }
                    }
                }
            }
        }
            return false;
        }

    //private void changeTurn() { turnFirstPlayer = !turnFirstPlayer; }

}
