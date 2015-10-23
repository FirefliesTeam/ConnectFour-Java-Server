package mechanics;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class GameSession {
    private final long startTime;
    private final GameUser firstPlayer;
    private final GameUser secondPlayer;

    private final int MARK_FIRST_PLAYER = 1;
    private final int MARK_SECOND_PLAYER = 2;

    private static final int COLUMNS = 7;
    private static final int ROWS = 6;

    private int[] gameField = new int[ROWS * COLUMNS];

    //Зачем они нужны?
    private Map<String, GameUser> users = new HashMap<>();

    public GameSession(String first, String second) {
        startTime = new Date().getTime();

        GameUser firstPlayer = new GameUser(first);
        firstPlayer.setEnemyName(second);

        GameUser secondPlayer = new GameUser(second);
        secondPlayer.setEnemyName(first);

        users.put(first, firstPlayer);
        users.put(second, secondPlayer);

        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;

        for(int i = 0; i < ROWS; ++i) {
            for(int j = 0; j < COLUMNS; ++j) {
                gameField[COLUMNS * i + j] = 0;
            }
        }
    }

    public long getSessionTime() { return new Date().getTime() - startTime; }

    public GameUser getFirstPlayer() { return firstPlayer; }

    public GameUser getSecondPlayer() { return secondPlayer; }

    public void setPointFirstPlayer(int i, int j) {
        gameField[COLUMNS * i + j] = MARK_FIRST_PLAYER;
    }

    public void setPointSecondPlayer(int i, int j) {
        gameField[COLUMNS * i + j] = MARK_SECOND_PLAYER;
    }

    public void setPointFirstPlayerByColumn(int j) {
        setPointByColumn(j, MARK_FIRST_PLAYER);
    }

    public void setPointSecondPlayerByColumn(int j) {
        setPointByColumn(j, MARK_SECOND_PLAYER);
    }

    public boolean isFirstWin() {
        return isWin(MARK_FIRST_PLAYER);
    }

    public boolean isSecondWin() {
        return isWin(MARK_SECOND_PLAYER);
    }

    private void setPointByColumn(int j, int mark) {
        int i = 0;
        while (i < ROWS && gameField[COLUMNS * i + j] == 0) {
            ++i;
        }
        gameField[COLUMNS * (--i) + j] = mark;
    }

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


}
