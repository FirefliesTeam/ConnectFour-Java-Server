package main;

import Frontend.Frontend;
import Services.AccountService.AccountService;
import Services.AccountService.AccountServiceImpl;
import admin.AdminServlet;
import Frontend.WorkingWithUsers.CheckAuthServletImpl;
import Frontend.WorkingWithUsers.LogoutServletImpl;
import Frontend.WorkingWithUsers.LoginServletImpl;
import Frontend.WorkingWithUsers.RegisterServletImpl;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.Servlet;

public class Main {
    public static void main(String[] args) throws  Exception, NumberFormatException, InterruptedException {
        if (args.length != 1) {
            System.out.append("Use port as the first argument");
            System.exit(1);
        }

        String portString = args[0];
        int port = Integer.valueOf(portString);
        System.out.append("Starting at port: ").append(portString).append('\n');

        //AccountService accountService = new AccountServiceImpl();
        AccountService accountService = new AccountServiceImpl();

        Frontend front_login = new LoginServletImpl(accountService);
        Frontend front_register = new RegisterServletImpl(accountService);
        Frontend front_logout = new LogoutServletImpl(accountService);
        Frontend front_checkAuth = new CheckAuthServletImpl(accountService);

        Servlet login = (Servlet) front_login;
        Servlet register = (Servlet) front_register;
        Servlet logout = (Servlet) front_logout;
        Servlet checkAuth  = (Servlet) front_checkAuth;
        Servlet admin = new AdminServlet(accountService);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(login), LoginServletImpl.PAGE_URL);
        context.addServlet(new ServletHolder(register), RegisterServletImpl.PAGE_URL);
        context.addServlet(new ServletHolder(logout), LogoutServletImpl.PAGE_URL);
        context.addServlet(new ServletHolder(checkAuth), CheckAuthServletImpl.PAGE_URL);
        context.addServlet(new ServletHolder(admin), AdminServlet.PAGE_URL);

        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setDirectoriesListed(true); //!!!
        resource_handler.setResourceBase("public_html");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context});

        Server server = new Server(port);
        server.setHandler(handlers);

        server.start();
        server.join();
    }
}