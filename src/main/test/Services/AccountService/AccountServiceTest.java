package Services.AccountService;

import Services.UserProfile.UserProfile;
import org.jetbrains.annotations.Nullable;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import java.util.Enumeration;
import java.util.HashMap;

import static org.junit.Assert.*;

public class AccountServiceTest {

    private AccountService accountService;

    @Before
    public void singUp() throws Exception {
        accountService = new AccountService();
        //testUser = new UserProfile("User1", "user1Pass", "user1@mail.ru");
    }

    @Test
    public void testSingUp() throws Exception {
        UserProfile user = new UserProfile("userSingUp", "123456", "user@mail.ru");
        accountService.singUp(user);
        UserProfile userResult = accountService.getUser(user.getLogin());
        assertNotNull(userResult);
        assertEquals(user.getLogin(), userResult.getLogin());
        assertEquals(user.getPassword(), userResult.getPassword());
        assertEquals(user.getEmail(), userResult.getEmail());
    }

    @Test
    public void testSingUpErrors() throws Exception {
        UserProfile user = new UserProfile("userSingUpErrors", "123456", "user@mail.ru");
        accountService.singUp(user);
        UserProfile userResult = accountService.getUser(user.getLogin());
        assertNotNull(userResult);
        assertNotEquals(userResult.getLogin(), "qwerty");
    }

    @Test
    public void testSingIn() throws Exception {
        HttpSession session = new MyHttpSession();
        UserProfile user = new UserProfile("userSingUp", "123456", "user@mail.ru");
        accountService.singUp(user);
        accountService.singIn(session, user.getLogin(), user.getPassword());
        assertTrue(accountService.checkAuth(session));
    }

    @Test
    public void testCheckAuth() throws Exception {
        HttpSession session = new MyHttpSession();
        UserProfile user = new UserProfile("userCheckAuth", "123456", "user@mail.ru");
        accountService.singUp(user);
        accountService.singIn(session, user.getLogin(), user.getPassword());
        assertTrue(accountService.checkAuth(session));
    }

    @Test
    public void testCheckAuthErrors() throws Exception {
        HttpSession session = new MyHttpSession();
        UserProfile user = new UserProfile("userCheckAuth", "123456", "user@mail.ru");
        accountService.singUp(user);
        accountService.singIn(session, user.getLogin(), user.getPassword());
        HttpSession temp_session = new MyHttpSession();
        assertFalse(accountService.checkAuth(temp_session));
    }

    @Test
    public void testIsAvailableName() throws Exception {
        UserProfile user1 = new UserProfile("userIsAvailableName1", "123456", "user@mail.ru");
        UserProfile user2 = new UserProfile("userIsAvailableName2", "123456", "user@mail.ru");
        UserProfile user3 = new UserProfile("userIsAvailableName3", "123456", "user@mail.ru");
        accountService.singUp(user1);
        accountService.singUp(user2);
        accountService.singUp(user3);
        assertTrue(accountService.isAvailableName("my_name"));
    }

    @Test
    public void testIsAvailableNameErrors() throws Exception {
        UserProfile user1 = new UserProfile("userIsAvailableName1", "123456", "user@mail.ru");
        UserProfile user2 = new UserProfile("userIsAvailableName2", "123456", "user@mail.ru");
        UserProfile user3 = new UserProfile("userIsAvailableName3", "123456", "user@mail.ru");
        accountService.singUp(user1);
        accountService.singUp(user2);
        accountService.singUp(user3);
        assertFalse(accountService.isAvailableName("userIsAvailableName1"));
    }

    @Test
    public void testValidationName() throws Exception {
        String name1 = "wergw34tser";
        assertTrue(accountService.validationName(name1));
        String name2 = "231231412";
        assertTrue(accountService.validationName(name2));
        String name3 = "KYEGFWEF";
        assertTrue(accountService.validationName(name3));
    }

    @Test
    public void testValidationNameErrors() throws Exception {
        String name1 = "w";
        assertFalse(accountService.validationName(name1));
        String name2 = "231231412lif3g74ofqgl3h4lfRFKU6fdku6rid";
        assertFalse(accountService.validationName(name2));
        String name3 = "#wfs^nrt";
        assertFalse(accountService.validationName(name3));
    }

    @Test
    public void testValidationPassword() throws Exception {
        String password1 = "waeg$awef";
        assertTrue(accountService.validationPassword(password1));
        String password2 = "34634534tgergdsr";
        assertTrue(accountService.validationPassword(password2));
        String password3 = "KYUguyT@UYGjkyg";
        assertTrue(accountService.validationPassword(password3));
    }

    @Test
    public void testValidationPasswordErrors() throws Exception {
        String password1 = "wae";
        assertFalse(accountService.validationPassword(password1));
        String password2 = "34634534tgergdsrhergsergserhsfgfggaegaerg";
        assertFalse(accountService.validationPassword(password2));
    }

    private static class MyHttpSession implements HttpSession {

        HashMap map = new HashMap();

        @Override
        public long getCreationTime() {
            return 0;
        }

        @Nullable
        @Override
        public String getId() {
            return null;
        }

        @Override
        public long getLastAccessedTime() {
            return 0;
        }

        @Nullable
        @Override
        public ServletContext getServletContext() {
            return null;
        }

        @Override
        public void setMaxInactiveInterval(int interval) {

        }

        @Override
        public int getMaxInactiveInterval() {
            return 0;
        }

        @SuppressWarnings("deprecation")
        @Nullable
        @Override
        public HttpSessionContext getSessionContext() {
            return null;
        }

        @Override
        public Object getAttribute(String name) {
            return map.get(name);
        }

        @Nullable
        @Override
        public Object getValue(String name) {
            return null;
        }

        @Nullable
        @Override
        public Enumeration<String> getAttributeNames() {
            return null;
        }

        @Override
        public String[] getValueNames() {
            return new String[0];
        }

        @Override
        public void setAttribute(String name, Object value) {
            map.put(name, value);
        }


        @Override
        public void putValue(String name, Object value) {

        }

        @Override
        public void removeAttribute(String name) {

        }

        @Override
        public void removeValue(String name) {

        }

        @Override
        public void invalidate() {

        }

        @Override
        public boolean isNew() {
            return false;
        }
    }
}