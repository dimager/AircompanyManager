package com.epam.jwd.controller.command.authentication;

import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.context.RequestContext;
import com.epam.jwd.controller.context.ResponseContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShowErrorPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ShowErrorPageCommand.class);
    private static final Command INSTANCE = new ShowErrorPageCommand();
    public static final String ERROR_JSP = "/WEB-INF/jsp/errorpage.jsp";

    public static Command getInstance(){
        return INSTANCE;
    }

    private static final ResponseContext ERROR_PAGE_CONTEXT = new ResponseContext() {
        @Override
        public String getPage() {
            return ERROR_JSP;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };


    private ShowErrorPageCommand() {
    }


    @Override
    public ResponseContext execute(RequestContext requestContext) {

        return ERROR_PAGE_CONTEXT;
    }
}
