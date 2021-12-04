package com.epam.jwd.controller.command.authentication;

import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.context.RequestContext;
import com.epam.jwd.controller.context.ResponseContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShowSignupCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ShowSignupCommand.class);
    private static final Command INSTANCE = new ShowSignupCommand();
    private static final String SIGNUP_JSP = "/WEB-INF/jsp/signup.jsp";
    private static final ResponseContext SHOW_SIGNUP_PAGE_CONTEXT = new ResponseContext() {
        @Override
        public String getPage() {
            return SIGNUP_JSP;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    private ShowSignupCommand() {
    }

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        return SHOW_SIGNUP_PAGE_CONTEXT;
    }


}
