package com.epam.jwd.controller.command.user;

import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.context.RequestContext;
import com.epam.jwd.controller.context.ResponseContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
        logger.debug("execute method");
        return SHOW_USER_PAGE_CONTEXT;
    }
}
