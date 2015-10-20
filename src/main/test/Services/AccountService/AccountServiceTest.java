package Services.AccountService;

import Services.UserProfile.UserProfile;
import org.junit.Test;

import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AccountServiceTest {

    private AccountService accountService = new AccountServiceImpl();

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
        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("userId")).thenReturn((long) 0);
        UserProfile user = new UserProfile("userSingUp", "123456", "user@mail.ru");
        accountService.singUp(user);
        accountService.singIn(session, user.getLogin(), user.getPassword());
        assertTrue(accountService.checkAuth(session));
    }

    @Test
    public void testCheckAuth() throws Exception {
        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("userId")).thenReturn((long) 0);
        UserProfile user = new UserProfile("userCheckAuth", "123456", "user@mail.ru");
        accountService.singUp(user);
        accountService.singIn(session, user.getLogin(), user.getPassword());
        assertTrue(accountService.checkAuth(session));
    }

    @Test
    public void testCheckAuthErrors() throws Exception {
        HttpSession session = mock(HttpSession.class);
        UserProfile user = new UserProfile("userCheckAuth", "123456", "user@mail.ru");
        accountService.singUp(user);
        accountService.singIn(session, user.getLogin(), user.getPassword());
        HttpSession temp_session = mock(HttpSession.class);
        when(temp_session.getAttribute("userId")).thenReturn(null);
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


}