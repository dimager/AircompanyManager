package com.epam.jwd.controller.command.locale;

import com.epam.jwd.controller.command.Attributes;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.context.RequestContext;
import com.epam.jwd.controller.context.ResponseContext;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class SetLocaleCommand implements Command {
    private static final Logger logger = LogManager.getLogger(SetLocaleCommand.class);
    private static final Command INSTANCE = new SetLocaleCommand();
    private static String PAGE = "/controller";
    private static final ResponseContext DEFAULT_PAGE_CONTEXT = new ResponseContext() {
        @Override
        public String getPage() {
            return PAGE;
        }

        @Override
        public boolean isRedirect() {
            return true;
        }
    };

    private SetLocaleCommand() {
    }

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        logger.debug("execute method");
        PAGE = "/controller";
        HttpServletRequest servletRequest = requestContext.getHttpServletRequest();
        String returnUrl = requestContext.getParamFromJSP(Attributes.RETURN_PAGE_ATTRIBUTE);
        if (Objects.nonNull(servletRequest.getParameter(Attributes.SESSION_LOCALE_ATTRIBUTE))) {
            servletRequest.getSession().setAttribute(Attributes.LOCALE_ATTRIBUTE, servletRequest.getParameter(Attributes.SESSION_LOCALE_ATTRIBUTE));
        }
        if (returnUrl.length() > 0) {
            PAGE = "/controller?" + returnUrl;
        }
        String locale = requestContext.getParamFromJSP(Attributes.SESSION_LOCALE_ATTRIBUTE);
        requestContext.getHttpSession().setAttribute(Attributes.SESSION_LOCALE_ATTRIBUTE, locale);
        return DEFAULT_PAGE_CONTEXT;
    }
}
