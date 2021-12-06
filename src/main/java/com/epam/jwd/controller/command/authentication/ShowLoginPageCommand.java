package com.epam.jwd.controller.command.authentication;

import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.context.RequestContext;
import com.epam.jwd.controller.context.ResponseContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShowLoginPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ShowLoginPageCommand.class);
    private static final Command INSTANCE = new ShowLoginPageCommand();
    private static final String LOGIN_JSP = "/WEB-INF/jsp/login.jsp";
    private static final ResponseContext SHOW_LOGIN_PAGE_CONTEXT = new ResponseContext() {
        @Override
        public String getPage() {
            return LOGIN_JSP;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    private ShowLoginPageCommand() {
    }

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        logger.debug("execute method");
        return SHOW_LOGIN_PAGE_CONTEXT;
    }
}
