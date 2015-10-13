package frontend;

import main.AccountService;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class LoginServlet extends HttpServlet {
    public static final String PAGE_URL = "/login";

    @NotNull
    private AccountService accountService;

    public LoginServlet(@NotNull AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");


        Map<String, Object> objResponse = new HashMap<>();
        objResponse.put("name", login);
        objResponse.put("auth", false);

        HttpSession session = request.getSession();

        if (accountService.singIn(session, login, password) != -1) {
            objResponse.put("auth", true);
        }

        JSONObject jsonResponse = new JSONObject(objResponse);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse.toString());
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
