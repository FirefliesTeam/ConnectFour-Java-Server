package admin;

import org.jetbrains.annotations.NotNull;
import main.AccountService;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class AdminServlet extends HttpServlet {
    public static final String PAGE_URL = "/admin";

    @NotNull
    private AccountService accountService;

    public AdminServlet(@NotNull AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void doGet(@NotNull HttpServletRequest request,
                      @NotNull HttpServletResponse response) throws ServletException, IOException {
        String shutdown = request.getParameter("shutdown");
        String registCount = request.getParameter("count_regist");
        String logCount = request.getParameter("count_logged");

        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("count_regist", -1);
        jsonResponse.put("count_logged", -1);

        if (shutdown != null && !shutdown.isEmpty()) {
            try {
                int shut = Integer.parseInt(shutdown);
                System.out.print("выключение сервера через " + shut + " ms");
                Thread.sleep(shut);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print("\nShutdown");
            System.exit(0);
        }
        if (registCount != null && registCount.equals("true")) {
            jsonResponse.put("count_regist", accountService.getRegisteredUsersCount());
        }
        if (logCount != null && logCount.equals("true")) {
            jsonResponse.put("count_logged", accountService.getLoggedUsersCount());
        }


        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse.toString());
        response.setStatus(HttpServletResponse.SC_OK);
    }
}

