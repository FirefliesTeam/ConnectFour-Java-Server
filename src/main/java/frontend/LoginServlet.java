package frontend;

import main.AccountService;
import main.UserProfile;
import templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author v.chibrikov
 */
public class LoginServlet extends HttpServlet {
    private AccountService accountService;

    public LoginServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        UserProfile profile = accountService.getUser(login);

        boolean isAuth = false;
        if (profile != null) {
            if (profile.getPassword().equals(password)) {
                isAuth = true;
            }
        }

        String json;

        if (isAuth) {
            json = "{ \"auth\": true }";
        } else {
            json = "{ \"auth\": false }";
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
        response.setStatus(HttpServletResponse.SC_OK);

    }
}
