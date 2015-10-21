package main;

import admin.AdminServlet;
import frontend.CheckAuthServlet;
import frontend.LogOutServlet;
import frontend.LoginServlet;
import frontend.RegisterServlet;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.Servlet;


public class Main {
    public static void main(String[] args) throws Exception  {
        //int port = 8080;
        if (args.length != 1) {
            /*
            String portString = args[0];
            port = Integer.valueOf(portString);
            */
            System.out.append("Use port as the first argument");
            System.exit(1);
        }

        String portString = args[0];
        int port = Integer.valueOf(portString);
        System.out.append("Starting at port: ").append(portString).append('\n');
        //System.out.append("Starting at port: ").append(String.valueOf(port)).append('\n');

        AccountService accountService = new AccountService();

        Servlet login = new LoginServlet(accountService);
        Servlet register = new RegisterServlet(accountService);
        Servlet logout = new LogOutServlet(accountService);
        Servlet checkAuth = new CheckAuthServlet(accountService);
        Servlet admin = new AdminServlet(accountService);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(login), LoginServlet.PAGE_URL);
        context.addServlet(new ServletHolder(register), RegisterServlet.PAGE_URL);
        context.addServlet(new ServletHolder(logout), LogOutServlet.PAGE_URL);
        context.addServlet(new ServletHolder(checkAuth), CheckAuthServlet.PAGE_URL);
        context.addServlet(new ServletHolder(admin), AdminServlet.PAGE_URL);

        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setDirectoriesListed(true);
        resource_handler.setResourceBase("public_html");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context});

        Server server = new Server(port);
        server.setHandler(handlers);

        server.start();
        server.join();
    }
}