package com.epam.jwd.controller.command.locale;

import com.epam.jwd.controller.command.Attributes;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.context.RequestContext;
import com.epam.jwd.controller.context.ResponseContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SetLocaleCommand implements Command {
    private static final Logger logger = LogManager.getLogger(SetLocaleCommand.class);
    private static final Command INSTANCE = new SetLocaleCommand();
    private static String PAGE = "/WEB-INF/jsp/main.jsp";
    private static final ResponseContext DEFAULT_PAGE_CONTEXT = new ResponseContext() {
        @Override
        public String getPage() {
            System.out.println(PAGE);
            return PAGE;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    private SetLocaleCommand() {
    }

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        PAGE = "controller?command=" + requestContext.getParamFromJSP("returnPage");
        String locale = requestContext.getParamFromJSP(Attributes.SESSION_LOCALE_ATTRIBUTE_NAME);
            requestContext.getHttpSession().setAttribute(Attributes.SESSION_LOCALE_ATTRIBUTE_NAME, locale);
        return DEFAULT_PAGE_CONTEXT;
    }
}
