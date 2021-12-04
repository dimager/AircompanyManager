package com.epam.jwd.controller.command.authentication;

import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.context.RequestContext;
import com.epam.jwd.controller.context.ResponseContext;
import jakarta.servlet.http.HttpSession;

public class LogoutCommand implements Command {
    public static final String INDEX_JSP = "/";
    private static final Command INSTANCE = new LogoutCommand();

    private static final ResponseContext LOGOUT_COMMAND_CONTEXT = new ResponseContext() {
        @Override
        public String getPage() {
            return INDEX_JSP;
        }

        @Override
        public boolean isRedirect() {
            return true;
        }
    };

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        HttpSession session = requestContext.getHttpSession(false);
        if (session != null){
            session.invalidate();
        }
        return LOGOUT_COMMAND_CONTEXT;
    }
}

