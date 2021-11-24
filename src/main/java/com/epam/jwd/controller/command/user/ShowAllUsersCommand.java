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

public class ShowAllUsersCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ShowAllUsersCommand.class);
    private static final Command INSTANCE = new ShowAllUsersCommand();
    private static final String ALL_USERS_JSP = "/WEB-INF/jsp/all_users.jsp";
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

    private ShowAllUsersCommand() {
    }

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public ResponseContext execute(RequestContext requestContext) {

        requestContext.addAttributeToJSP("roles", Arrays.asList(Role.values()));
        UserService userService = new UserService();
        try {
            requestContext.addAttributeToJSP("allUsers", userService.findAll());
        } catch (DAOException e) {
            logger.error(e);
            requestContext.addAttributeToJSP(Attributes.EXCEPTION_ATTRIBUTE_NAME, e.getMessage());
        }

        return SHOW_ALL_USERS_PAGE_CONTEXT;
    }
}
