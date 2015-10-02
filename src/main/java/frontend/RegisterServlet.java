package frontend;

import main.AccountService;
import main.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class RegisterServlet extends HttpServlet {
    private AccountService accountService;

    public RegisterServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("login");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Map<String, Object> objResponse = new HashMap<>();
        objResponse.put("signup", false);
        objResponse.put("name", name);

        if (accountService.addUser(name, new UserProfile(name, password, email))) {
            objResponse.replace("signup", true);
        }
        JSONObject jsonResponse = new JSONObject(objResponse);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse.toString());
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
