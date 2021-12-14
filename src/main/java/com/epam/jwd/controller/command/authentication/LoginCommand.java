package com.epam.jwd.controller.command.authentication;

import com.epam.jwd.controller.command.Attributes;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.context.RequestContext;
import com.epam.jwd.controller.context.ResponseContext;
import com.epam.jwd.dao.exception.DAOException;
import com.epam.jwd.service.dto.UserDTO;
import com.epam.jwd.service.exception.ServiceException;
import com.epam.jwd.service.impl.LoginService;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginCommand implements Command {
    private static final Logger logger = LogManager.getLogger(LoginCommand.class);
    private static final int RESULT_MESSAGE_CODE = 100;
    private static final Command INSTANCE = new LoginCommand();
    public static final String LOGIN_JSP = "/controller?command=SHOW_LOGIN_PAGE";
    private static final ResponseContext LOGIN_COMMAND_CONTEXT = new ResponseContext() {
        @Override
        public String getPage() {
            return LOGIN_JSP;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        logger.debug("execute method");
        UserDTO userDTO = new UserDTO();
        userDTO.setPassword(requestContext.getParamFromJSP(Attributes.JSP_PASSWORD_INPUT_FILED_NAME));
        userDTO.setUsername(requestContext.getParamFromJSP(Attributes.JSP_USERNAME_INPUT_FILED_NAME));
        LoginService loginService = new LoginService();
        try {
            UserDTO loggedinUser = loginService.authenticate(userDTO);
            HttpSession session = requestContext.getHttpSession(true);
            session.setAttribute(Attributes.SESSION_USER_ATTRIBUTE, loggedinUser);
            session.setAttribute(Attributes.SESSION_LOGIN_STATE_ATTRIBUTE, true);
            requestContext.addAttributeToJSP(Attributes.COMMAND_RESULT_ATTRIBUTE, RESULT_MESSAGE_CODE);
        } catch (DAOException | ServiceException e) {
            logger.error(e);
            requestContext.addAttributeToJSP(Attributes.EXCEPTION_ATTRIBUTE, e.getMessage());
        }
        return LOGIN_COMMAND_CONTEXT;
    }
}

