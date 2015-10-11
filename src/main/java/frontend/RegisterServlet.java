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

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

public class RegisterServlet extends HttpServlet {
    public static final String PAGE_URL = "/register";

    @NotNull
    private AccountService accountService;

    public RegisterServlet(@NotNull AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("login");
        String email = request.getParameter("email");
        String password = request.getParameter("password");


        Map<String, Object> objResponse = new HashMap<>();
        objResponse.put("signup", false); ///// что возвращать???? true -&????? false - ?????
        objResponse.put("login", "notexists"); /////!!!!!!!!!!!!!!
        objResponse.put("email", true);
        objResponse.put("password", true);

        boolean isOk = true;

        if (!accountService.validationName(name)) {
            //objResponse.put("login", "exists");
            isOk = false;
        } if (!accountService.validationEmail(email)) {
            objResponse.put("email", false);
            isOk = false;
        } if (!accountService.validationPassword(password)) {
            objResponse.put("password", false);
            isOk = false;
        }

        if (isOk) {
            if (accountService.singUp(new UserProfile(name, password, email))) {
                objResponse.put("signup", true);
            } else {
                objResponse.put("login", "exists");
            }
        }

        JSONObject jsonResponse = new JSONObject(objResponse);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse.toString());
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
