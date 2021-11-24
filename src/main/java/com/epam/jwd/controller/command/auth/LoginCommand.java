package com.epam.jwd.controller.command.auth;

import com.epam.jwd.controller.api.JSPParameters;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.context.RequestContext;
import com.epam.jwd.controller.context.ResponseContext;
import com.epam.jwd.dao.entity.User;
import com.epam.jwd.dao.exception.DAOException;
import com.epam.jwd.service.converter.UserConverter;
import com.epam.jwd.service.dto.UserDTO;
import com.epam.jwd.service.exception.ValidatorException;
import com.epam.jwd.service.impl.AuthenticationService;
import com.epam.jwd.service.validator.UserValidator;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginCommand implements Command {
    private static final Logger logger = LogManager.getLogger(LoginCommand.class);
    public static final String LOGIN_JSP = "/WEB-INF/jsp/login.jsp";
    public static final String INDEX_JSP = "/WEB-INF/jsp/main.jsp";
    private static final Command INSTANCE = new LoginCommand();

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
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(requestContext.getParamFromJSP(JSPParameters.JSP_USERNAME_INPUT_FILED_NAME).trim());
        userDTO.setPassword(requestContext.getParamFromJSP(JSPParameters.JSP_PASSWORD_INPUT_FILED_NAME).trim());
        UserConverter userConverter = new UserConverter();
        User user = userConverter.convertToDAO(userDTO);
        AuthenticationService authenticationService = new AuthenticationService();


        try {
            if (UserValidator.loginValidate(user)) {
                try {
                    if (authenticationService.authorized(user)) {
                        HttpSession oldSession = requestContext.getHttpSession(false);
                        if (oldSession != null) {
                            oldSession.invalidate();
                        }
                        HttpSession newSession = requestContext.getHttpSession(true);
                        userDTO = userConverter.convertToDTO(user);
                        newSession.setAttribute("userDTO",userDTO);
                        newSession.setAttribute(JSPParameters.SESSION_SUCCESSFULLY_LOGIN_STATE_ATTRIBUTE_NAME, true);
                    } else {
                        requestContext.addAttributeToJSP(JSPParameters.JSP_INCORRECT_LOGIN_DATA_ATTRIBUTE_NAME, true);
                    }
                } catch (DAOException e) {
                    e.printStackTrace();
                }
            }
        } catch (ValidatorException e) {
            e.printStackTrace();
        }
        return LOGIN_COMMAND_CONTEXT;

    }
}

