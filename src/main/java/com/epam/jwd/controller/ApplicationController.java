package com.epam.jwd.controller;

import com.epam.jwd.controller.command.Attributes;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.context.ResponseContext;
import com.epam.jwd.controller.context.impl.RequestContextImpl;
import com.epam.jwd.dao.connectionpool.impl.ConnectionPoolImpl;
import com.epam.jwd.dao.entity.Role;
import com.epam.jwd.service.dto.UserDTO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;

public class ApplicationController extends HttpServlet {


    private static final String COMMAND_ATTRIBUTE = "command";

    @Override
    public void init() throws ServletException {
        for (Role value : Role.values()) {
            this.getServletContext().setAttribute("Role_" + value, value);
        }
        super.init();
    }

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
        UserDTO userDTO = (UserDTO) req.getSession().getAttribute(Attributes.SESSION_USER_ATTRIBUTE);
        if (Objects.isNull(userDTO) || Objects.isNull(commandName)) {
            execute(req, resp, commandName);
        } else if (Objects.nonNull(commandName) && Command.executePermission(commandName, userDTO.getRole())) {
            execute(req, resp, commandName);
        }
        else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/controller?command=SHOW_ERROR_PAGE");
            dispatcher.forward(req, resp);
        }
    }

    private void execute(HttpServletRequest req, HttpServletResponse resp, String commandName) throws IOException, ServletException {
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
        ConnectionPoolImpl.getInstance().shutdown();
    }
}
