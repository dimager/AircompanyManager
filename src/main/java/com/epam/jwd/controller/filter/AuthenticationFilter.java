package com.epam.jwd.controller.filter;

import com.epam.jwd.controller.command.Attributes;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.command.Commands;
import com.epam.jwd.service.dto.UserDTO;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@WebFilter(servletNames = "ApplicationServlet")
public class AuthenticationFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(AuthenticationFilter.class);
    private static final String COMMAND_ATTRIBUTE = "command";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.debug("doFilter method");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(false);
        String command = req.getParameter(COMMAND_ATTRIBUTE);
        List<Commands> commandsWithoutUser = Arrays.asList(Commands.SHOW_LOGIN_PAGE, Commands.DEFAULT, Commands.SET_LOCALE, Commands.LOGIN, Commands.SIGNUP, Commands.SHOW_SIGNUP_PAGE);
        boolean isCommandWithoutUser = commandsWithoutUser.contains(Command.name(command));
        UserDTO userDTO = (UserDTO) req.getSession().getAttribute(Attributes.SESSION_USER_ATTRIBUTE);
        if (Objects.nonNull(session)) {
            if (isCommandWithoutUser || (Objects.nonNull(userDTO)) && Command.executePermission(command, userDTO.getRole())) {
                chain.doFilter(request, response);
            } else {
                ((HttpServletResponse) response).sendRedirect("/");
            }
        }
    }
}
