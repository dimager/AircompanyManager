package com.epam.jwd.controller;

import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.context.RequestContextImpl;
import com.epam.jwd.controller.context.ResponseContext;
import com.epam.jwd.dao.connectionpool.impl.ConnectionPoolImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/controller", name = "myServlet")
public class ApplicationController extends HttpServlet {
    private static final String COMMAND_ATTRIBUTE = "command";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        commandHandler(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        commandHandler(req, resp);
    }

    private void commandHandler(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String commandName = req.getParameter(COMMAND_ATTRIBUTE);
        Command command = Command.of(commandName);
        ResponseContext commandResult = command.execute(new RequestContextImpl(req));
        if (commandResult.isRedirect()){
            resp.sendRedirect(commandResult.getPage());
        }
        else {
            RequestDispatcher dispatcher = req.getRequestDispatcher(commandResult.getPage());
            dispatcher.forward(req,resp);
        }
    }
    @Override
    public void destroy() {
        ConnectionPoolImpl.getInstance().shutdown();
    }
}
