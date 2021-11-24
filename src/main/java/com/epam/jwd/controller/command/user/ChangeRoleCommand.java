package com.epam.jwd.controller.command.user;

import com.epam.jwd.controller.command.Attributes;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.context.RequestContext;
import com.epam.jwd.controller.context.ResponseContext;
import com.epam.jwd.dao.entity.Role;
import com.epam.jwd.dao.exception.DAOException;
import com.epam.jwd.service.impl.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class ChangeRoleCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ChangeRoleCommand.class);
    private static final Command INSTANCE = new ChangeRoleCommand();
    private static final String ALL_USERS_JSP = "/controller?command=SHOW_ALL_USERS";
    private static final String RESULT_MESSAGE = "Role was updated.";
    private static final ResponseContext SHOW_ALL_USERS_PAGE_CONTEXT = new ResponseContext() {
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

    public String getPage() {
        return ALL_USERS_JSP;
    }

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        UserService userService = new UserService();
        try {
            int roleId = Integer.parseInt(requestContext.getParamFromJSP("new_role"));
            long userId = Integer.parseInt(requestContext.getParamFromJSP("user_id"));
            userService.changUserRole(userId, roleId);
            requestContext.addAttributeToJSP(Attributes.COMMAND_RESULT_ATTRIBUTE_NAME, RESULT_MESSAGE);
        } catch (DAOException e) {
            logger.error(e);
            requestContext.addAttributeToJSP(Attributes.EXCEPTION_ATTRIBUTE_NAME, e.getMessage());
        }
        return SHOW_ALL_USERS_PAGE_CONTEXT;
    }
}
