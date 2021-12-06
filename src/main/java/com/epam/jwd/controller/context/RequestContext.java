package com.epam.jwd.controller.context;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public interface RequestContext {
    HttpServletRequest getHttpServletRequest();
    void addAttributeToJSP(String name, Object attribute);
    String getParamFromJSP(String name);
    HttpSession getHttpSession();
    HttpSession getHttpSession(boolean create);


}
