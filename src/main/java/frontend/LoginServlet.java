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
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        response.setStatus(HttpServletResponse.SC_OK);

        Map<String, Object> pageVariables = new HashMap<>();
        UserProfile profile = accountService.getUser(login);

        boolean isAuth = false;

        if (profile != null) {
            if (profile.getPassword().equals(password)) {
                pageVariables.put("loginStatus", "Login passed");
                isAuth = true;
            } else {
                pageVariables.put("loginStatus", "Wrong password");
            }
        } else {
            pageVariables.put("loginStatus", "Login is not exist");
        }

        if (isAuth) {
            String temp = profile.getLogin();
            pageVariables.put("login", profile.getLogin());
            pageVariables.put("password", profile.getPassword());
            pageVariables.put("email", profile.getEmail());
        } else {
            pageVariables.put("login", "");
            pageVariables.put("password", "");
            pageVariables.put("email", "");
        }

        /*
        if (profile != null && profile.getPassword().equals(password)) {
            pageVariables.put("loginStatus", "Login passed");
            pageVariables.put("login", profile.getLogin());
            pageVariables.put("password", profile.getPassword());
            pageVariables.put("email", profile.getEmail());
        } else {
            pageVariables.put("loginStatus", "Wrong login/password");
        }
        */
        response.getWriter().println(PageGenerator.getPage("authstatus.html", pageVariables));
        //response.setContentType("application/json");
        //response.getWriter().write("lalalalallal");
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        /*
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        response.setStatus(HttpServletResponse.SC_OK);

        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("email", email == null ? "" : email);
        pageVariables.put("password", password == null ? "" : password);

        response.getWriter().println(PageGenerator.getPage("authresponse.txt", pageVariables));
        */
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        response.setStatus(HttpServletResponse.SC_OK);

        Map<String, Object> pageVariables = new HashMap<>();
        UserProfile profile = accountService.getUser(login);

        boolean isAuth = false;

        if (profile != null) {
            if (profile.getPassword().equals(password)) {
                pageVariables.put("loginStatus", "Login passed");
                isAuth = true;
            } else {
                pageVariables.put("loginStatus", "Wrong password");
            }
        } else {
            pageVariables.put("loginStatus", "Login is not exist");
        }

        if (isAuth) {
            String temp = profile.getLogin();
            pageVariables.put("login", profile.getLogin());
            pageVariables.put("password", profile.getPassword());
            pageVariables.put("email", profile.getEmail());
        } else {
            pageVariables.put("login", "");
            pageVariables.put("password", "");
            pageVariables.put("email", "");
        }

        response.getWriter().println(PageGenerator.getPage("authstatus.html", pageVariables));

    }
}
