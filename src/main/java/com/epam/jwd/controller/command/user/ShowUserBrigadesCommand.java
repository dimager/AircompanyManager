package com.epam.jwd.controller.command.user;

import com.epam.jwd.controller.command.Attributes;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.context.RequestContext;
import com.epam.jwd.controller.context.ResponseContext;
import com.epam.jwd.dao.exception.DAOException;
import com.epam.jwd.service.dto.UserDTO;
import com.epam.jwd.service.impl.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShowUserBrigadesCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ShowUserBrigadesCommand.class);
    private static final Command INSTANCE = new ShowUserBrigadesCommand();
    private static final String USER_BRIGADES_JSP = "/WEB-INF/jsp/userbrigades.jsp";
    private static final ResponseContext SHOW_USER_FLIGHTS_PAGE_CONTEXT = new ResponseContext() {
        @Override
        public String getPage() {
            return USER_BRIGADES_JSP;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    private ShowUserBrigadesCommand() {
    }

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        UserService userService = new UserService();

        try {
            UserDTO  userDTO = (UserDTO) requestContext.getHttpSession(false).getAttribute(Attributes.SESSION_USER_ATTRIBUTE);
            requestContext.addAttributeToJSP(Attributes.USER_BRIGADES_ATTRIBUTE_NAME,userService.findUserBrigades(userDTO.getUserId()));
        } catch (DAOException e) {
            logger.error(e);
            requestContext.addAttributeToJSP(Attributes.EXCEPTION_ATTRIBUTE_NAME, e.getMessage());
        }
        return SHOW_USER_FLIGHTS_PAGE_CONTEXT;
    }
}
