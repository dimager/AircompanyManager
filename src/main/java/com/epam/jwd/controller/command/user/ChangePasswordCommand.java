package com.epam.jwd.controller.command.user;

import com.epam.jwd.controller.command.Attributes;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.context.RequestContext;
import com.epam.jwd.controller.context.ResponseContext;
import com.epam.jwd.dao.exception.DAOException;
import com.epam.jwd.service.dto.UserDTO;
import com.epam.jwd.service.impl.LoginService;
import com.epam.jwd.service.impl.UserService;
import com.epam.jwd.service.impl.ValidationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ChangePasswordCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ChangePasswordCommand.class);
    private static final Command INSTANCE = new ChangePasswordCommand();
    private static String USER_JSP = "/controller?command=SHOW_USER_PAGE";

    private static final ResponseContext CHANGE_PASSWORD_PAGE_CONTEXT = new ResponseContext() {
        @Override
        public String getPage() {
            return USER_JSP;
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
        UserService userService = new UserService();
        ValidationService validationService = new ValidationService();
        LoginService loginService = new LoginService();

        List<Integer> errors = new ArrayList<>();
        UserDTO sessionUserDTO = (UserDTO) requestContext.getHttpSession(false).getAttribute(Attributes.SESSION_USER_ATTRIBUTE);
        String oldPassword = requestContext.getParamFromJSP(Attributes.OLD_PASSWORD_ATTRIBUTE).trim();
        sessionUserDTO.setPassword(oldPassword);
        String newPassword = requestContext.getParamFromJSP(Attributes.NEW_PASSWORD_ATTRIBUTE).trim();
        String newPasswordRepeat = requestContext.getParamFromJSP(Attributes.NEW_PASSWORD_REPEAT_ATTRIBUTE).trim();
        try {
            sessionUserDTO.setPassword(newPassword);
            if (validationService.validateNewPassword(sessionUserDTO, newPasswordRepeat, errors)
                    & Objects.nonNull(loginService.authenticate(sessionUserDTO, errors))) {
                if (userService.update(sessionUserDTO)) {
                    USER_JSP = "/controller?command=SHOW_LOGIN_PAGE";
                    requestContext.getHttpSession(false).invalidate();
                }
            } else {
                requestContext.addAttributeToJSP(Attributes.COMMAND_ERRORS_ATTRIBUTE, errors);
            }
        } catch (DAOException e) {
            logger.error(e);
            requestContext.addAttributeToJSP(Attributes.EXCEPTION_ATTRIBUTE, e.getMessage());
        }
        return CHANGE_PASSWORD_PAGE_CONTEXT;
    }
}
