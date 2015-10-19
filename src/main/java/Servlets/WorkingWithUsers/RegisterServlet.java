package Servlets.WorkingWithUsers;

import Services.AccountService.AccountService;
import Services.UserProfile.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    public void doPost(@NotNull HttpServletRequest request,
                       @NotNull HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("login");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("signup", false);
        jsonResponse.put("login", "notexists");
        jsonResponse.put("email", true);
        jsonResponse.put("password", true);

        boolean isOk = true;

        if (!accountService.validationName(name)) {
            //objResponse.put("login", "exists");
            isOk = false;
        } if (!accountService.validationEmail(email)) {
            jsonResponse.put("email", false);
            isOk = false;
        } if (!accountService.validationPassword(password)) {
            jsonResponse.put("password", false);
            isOk = false;
        }

        if (isOk) {
            if (accountService.singUp(new UserProfile(name, password, email))) {
                jsonResponse.put("signup", true);
            } else {
                jsonResponse.put("login", "exists");
            }
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse.toString());
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
