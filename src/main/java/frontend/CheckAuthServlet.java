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

public class CheckAuthServlet extends HttpServlet {
    public static final String PAGE_URL = "/checkAuth";

    @NotNull
    private AccountService accountService;

    public CheckAuthServlet(@NotNull AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

        Map<String, Object> objResponse = new HashMap<>();
        objResponse.put("auth", true);

        HttpSession session = request.getSession();

        if (!accountService.checkAuth(session)) {
            objResponse.put("auth", false);
        }

        JSONObject jsonResponse = new JSONObject(objResponse);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse.toString());
        response.setStatus(HttpServletResponse.SC_OK);
    }
}