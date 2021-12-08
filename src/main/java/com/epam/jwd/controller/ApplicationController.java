package com.epam.jwd.controller;

import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.context.ResponseContext;
import com.epam.jwd.controller.context.impl.RequestContextImpl;
import com.epam.jwd.dao.connectionpool.impl.ConnectionPoolImpl;
import com.epam.jwd.dao.entity.Role;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class ApplicationController extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(ApplicationController.class);

    private static final String COMMAND_ATTRIBUTE = "command";

    @Override
    public void init() throws ServletException {
        logger.debug("init method");
        for (Role value : Role.values()) {
            this.getServletContext().setAttribute("Role_" + value, value);
        }
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        execute(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        execute(req, resp);
    }

    private void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        logger.debug("execute method");
        String commandName = req.getParameter(COMMAND_ATTRIBUTE);
        Command command = Command.of(commandName);
        ResponseContext commandResult = command.execute(new RequestContextImpl(req));
        if (commandResult.isRedirect()) {
            resp.sendRedirect(commandResult.getPage());
        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher(commandResult.getPage());
            dispatcher.forward(req, resp);
        }
    }

    @Override
    public void destroy() {
        logger.debug("destroy method");
        ConnectionPoolImpl.getInstance().shutdown();
    }
}
