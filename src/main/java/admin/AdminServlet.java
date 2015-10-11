package admin;

import org.jetbrains.annotations.NotNull;
import main.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class AdminServlet extends HttpServlet {
    public static final String PAGE_URL = "/admin";

    @NotNull
    private AccountService accountService;

    public AdminServlet(@NotNull AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        String shutdown = request.getParameter("shutdown");

        PrintWriter writer = response.getWriter();

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

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        //response.getWriter().write(jsonResponse.toString());
        response.setStatus(HttpServletResponse.SC_OK);
    }
}

