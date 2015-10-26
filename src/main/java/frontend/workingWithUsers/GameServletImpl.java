package frontend.workingWithUsers;

import base.AccountService;
import base.Frontend;
import base.GameMechanics;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GameServletImpl extends HttpServlet implements Frontend {
    public static final String PAGE_URL = "/game";

    @NotNull
    private AccountService accountService;

    @NotNull
    private GameMechanics gameMechanics;

    public GameServletImpl(@NotNull AccountService accountService, @NotNull GameMechanics gameMechanics) {
        this.accountService = accountService;
        this.gameMechanics = gameMechanics;
    }

    @Override
    public void doGet(@NotNull HttpServletRequest request,
                      @NotNull HttpServletResponse response) throws ServletException, IOException {

        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("auth", false);
        jsonResponse.put("name", "");

        HttpSession session = request.getSession();

        if (accountService.checkAuth(session)) {
            jsonResponse.put("auth", true);
            jsonResponse.put("name", accountService.getNameBySession(session));

        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse.toString());
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    public void doPost(@NotNull HttpServletRequest request,
                       @NotNull HttpServletResponse response) throws ServletException, IOException {}

}
