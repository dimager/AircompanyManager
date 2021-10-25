package com.epam.jwd.controller;

import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.context.RequestContextImpl;
import com.epam.jwd.controller.context.ResponseContext;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/controller")
public class ApplicationContoller extends HttpServlet {
    private static final String COMMAND_PARAM = "command";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String commandName = req.getParameter(COMMAND_PARAM);
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();

    }
}
