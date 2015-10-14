package main;

import org.jetbrains.annotations.NotNull;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class AccountService {
    public static final int MIN_LENGTH_NAME = 2;
    public static final int MAX_LENGTH_NAME = 15;
    public static final int MIN_LENGTH_EMAIL = 5;
    public static final int MAX_LENGTH_EMAIL = 15;
    public static final int MIN_LENGTH_PASSWORD = 6;
    public static final int MAX_LENGTH_PASSWORD = 20;

    @NotNull
    private Map<String, UserProfile> users = new HashMap<>();
    @NotNull
    private Map<String, UserProfile> sessions = new HashMap<>();

    private long _lastSessionId = 0;

    public boolean singUp(UserProfile userProfile) {
        if(isAvailableName(userProfile.getLogin())) {
            addUser(userProfile);
            return true;
        } else {
            return false;
        }
    }

    public long singIn(@NotNull HttpSession session, @NotNull String login, @NotNull String password) {
        UserProfile profile = getUser(login);
        if (profile != null) {
            if (profile.getPassword().equals(password)) {
                long sessionId = getSessionId();
                session.setAttribute("userId", sessionId);
                addSessions(Long.toString(sessionId), profile);
                return sessionId;
            }
        }
        return -1;
    }

    public boolean logOut(@NotNull HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        session.removeAttribute("userId");
        if (!sessions.isEmpty()) {
            sessions.remove(userId.toString());
        }
        return true;
    }

    public boolean checkAuth(@NotNull HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return false;
        }
        UserProfile userProfile = getSessions(userId.toString());
        return null != userProfile;
    }


    public boolean isAvailableName(@NotNull String login) {
        return !users.containsKey(login);
    }


    public boolean validationName(@NotNull String name) {
        String tokens = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_.";
        int min_length = MIN_LENGTH_NAME;
        int max_length = MAX_LENGTH_NAME;
        return validation(name, tokens, min_length, max_length);
    }

    public boolean validationEmail(@NotNull String email) {
        if(email.indexOf('@') == -1) {
            return false;
        }
        String tokens = "@ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_.";
        int min_length = MIN_LENGTH_EMAIL;
        int max_length = MAX_LENGTH_EMAIL;
        return validation(email, tokens, min_length, max_length);
    }

    public boolean validationPassword(@NotNull String password) {
        String tokens = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_.";
        int min_length = MIN_LENGTH_PASSWORD;
        int max_length = MAX_LENGTH_PASSWORD;
        return validation(password, tokens, min_length, max_length);
    }

    public int getRegisteredUsersCount(){
        return users.size();
    }

    public int getLoggedUsersCount(){
        return sessions.size();
    }

    private void addUser(@NotNull UserProfile userProfile) {
        users.put(userProfile.getLogin(), userProfile);
    }

    private void addSessions(@NotNull String sessionId, @NotNull UserProfile userProfile) {
        sessions.put(sessionId, userProfile);
    }

    private UserProfile getUser(@NotNull String userName) {
        return users.get(userName);
    }

    private UserProfile getSessions(@NotNull String sessionId) {
        return sessions.get(sessionId);
    }

    private long getSessionId() {
        return _lastSessionId++;
    }

    private boolean validation(@NotNull String str, @NotNull String tokens, @NotNull int min_length, @NotNull int max_length) {
        char[] symbols = str.toCharArray();
        if(symbols.length < min_length || symbols.length > max_length ) return false;
        for(char c : symbols){
            if(tokens.indexOf(c)==-1) return false;
        }
        return true;
    }

}
