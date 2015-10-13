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

    public long singIn(HttpSession session, String login, String password) {
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

    /////// наверно надо сделать deleteSession()
    public boolean logOut(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (!sessions.isEmpty()) {
            sessions.remove(userId.toString());
        }
        return true;
    }

    public boolean checkAuth(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return false;
        }
        UserProfile userProfile = getSessions(userId.toString());
        return null != userProfile;
    }


    public boolean isAvailableName(String login) {
        return !users.containsKey(login);
    }


    public boolean validationName(String name) {
        String tokens = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_.";
        int min_length = MIN_LENGTH_NAME;
        int max_length = MAX_LENGTH_NAME;
        return validation(name, tokens, min_length, max_length);

        /*
        char[] symbols = name.toCharArray();
        if(symbols.length < 2 || symbols.length > 10 ) return false;
        String validationString = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_.";
        for(char c : symbols){
            if(validationString.indexOf(c)==-1) return false;
        }
        return true;*/
    }

    public boolean validationEmail(String email) {
        if(email.indexOf('@') == -1) {
            return false;
        }
        String tokens = "@ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_.";
        int min_length = MIN_LENGTH_EMAIL;
        int max_length = MAX_LENGTH_EMAIL;
        return validation(email, tokens, min_length, max_length);
        /*
        char[] symbols = email.toCharArray();
        if(symbols.length < 5 || symbols.length > 20 ) return false;
        String validationString = "@ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_.";
        for(char c : symbols){
            if(validationString.indexOf(c)==-1) return false;
        }
        return true;
        */
    }

    public boolean validationPassword(String password) {
        String tokens = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_.";
        int min_length = MIN_LENGTH_PASSWORD;
        int max_length = MAX_LENGTH_PASSWORD;
        return validation(password, tokens, min_length, max_length);
        /*
        char[] symbols = password.toCharArray();
        if(symbols.length < 6 || symbols.length > 20 ) return false;
        String validationString = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_";
        for(char c : symbols){
            if(validationString.indexOf(c)==-1) return false;
        }
        return true;
        */
    }

    public int getRegisteredUsersCount(){
        return users.size();
    }

    public int getLoggedUsersCount(){
        return sessions.size();
    }

    private void addUser(UserProfile userProfile) {
        users.put(userProfile.getLogin(), userProfile);
    }

    private void addSessions(String sessionId, UserProfile userProfile) {
        sessions.put(sessionId, userProfile);
    }

    private UserProfile getUser(String userName) {
        return users.get(userName);
    }

    private UserProfile getSessions(String sessionId) {
        return sessions.get(sessionId);
    }

    private long getSessionId() {
        return _lastSessionId++;
    }

    private boolean validation(String str, String tokens, int min_length, int max_length) {
        char[] symbols = str.toCharArray();
        if(symbols.length < min_length || symbols.length > max_length ) return false;
        for(char c : symbols){
            if(tokens.indexOf(c)==-1) return false;
        }
        return true;
    }

}
