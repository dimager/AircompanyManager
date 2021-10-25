package com.epam.jwd.controller.command;

import com.epam.jwd.controller.context.RequestContext;
import com.epam.jwd.controller.context.ResponseContext;

public class DefaultCommand implements Command {

    private static final Command INSTANCE = new DefaultCommand();
    public static Command getInstance(){
        return INSTANCE;
    }

    private static final ResponseContext DEFAULT_PAGE_CONTEXT = new ResponseContext() {
        @Override
        public String getPage() {
            return "/WEB-INF/main.jsp";
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };


    private DefaultCommand() {
    }


    @Override
    public ResponseContext execute(RequestContext requestContext) {
        return DEFAULT_PAGE_CONTEXT;
    }
}
