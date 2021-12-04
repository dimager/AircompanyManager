package com.epam.jwd.controller.command.user;

import com.epam.jwd.controller.command.Attributes;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.context.RequestContext;
import com.epam.jwd.controller.context.ResponseContext;
import com.epam.jwd.dao.entity.Role;
import com.epam.jwd.dao.exception.DAOException;
import com.epam.jwd.service.dto.AircraftDTO;
import com.epam.jwd.service.dto.UserDTO;
import com.epam.jwd.service.impl.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;

public class ShowUserPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ShowUserPageCommand.class);
    private static final Command INSTANCE = new ShowUserPageCommand();
    private static final String USER_JSP = "/WEB-INF/jsp/userpage.jsp";
    private static final ResponseContext SHOW_USER_PAGE_CONTEXT = new ResponseContext() {
        @Override
        public String getPage() {
            return USER_JSP;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    private ShowUserPageCommand() {
    }

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public ResponseContext execute(RequestContext requestContext) {

        UserService userService = new UserService();
        try {
            UserDTO userDTO = userService.findById(1);
            requestContext.addAttributeToJSP("userDTO", userDTO);
        } catch (DAOException e) {
            logger.error(e);
            requestContext.addAttributeToJSP(Attributes.EXCEPTION_ATTRIBUTE_NAME, e.getMessage());
        }

        return SHOW_USER_PAGE_CONTEXT;
    }
}