package com.epam.jwd.controller.command.user;

import com.epam.jwd.controller.command.Attributes;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.context.RequestContext;
import com.epam.jwd.controller.context.ResponseContext;
import com.epam.jwd.dao.exception.DAOException;
import com.epam.jwd.service.impl.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChangeRoleCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ChangeRoleCommand.class);
    private static final Command INSTANCE = new ChangeRoleCommand();
    private static final String ALL_USERS_JSP = "/controller?command=SHOW_ALL_USERS";
    private static final int RESULT_MESSAGE_CODE = 116;
    private static final int PARSING_ERROR_CODE = 247;
    private static final ResponseContext CHANGE_ROLE_PAGE_CONTEXT = new ResponseContext() {
        @Override
        public String getPage() {
            return ALL_USERS_JSP;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    private ChangeRoleCommand() {
    }

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        logger.debug("execute method");
        UserService userService = new UserService();
        try {
            int roleId = Integer.parseInt(requestContext.getParamFromJSP(Attributes.NEW_ROLE_ATTRIBUTE));
            long userId = Integer.parseInt(requestContext.getParamFromJSP(Attributes.USER_ID_ATTRIBUTE));
            userService.changUserRole(userId, roleId);
            requestContext.addAttributeToJSP(Attributes.COMMAND_RESULT_ATTRIBUTE, RESULT_MESSAGE_CODE);
        } catch (DAOException e) {
            logger.error(e);
            requestContext.addAttributeToJSP(Attributes.EXCEPTION_ATTRIBUTE, e.getMessage());
        }  catch (NumberFormatException e) {
            logger.error(e);
            requestContext.addAttributeToJSP(Attributes.EXCEPTION_ATTRIBUTE, PARSING_ERROR_CODE);
        }
        return CHANGE_ROLE_PAGE_CONTEXT;
    }
}
