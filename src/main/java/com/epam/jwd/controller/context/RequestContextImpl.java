package com.epam.jwd.controller.context;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class RequestContextImpl implements RequestContext {
    private final HttpServletRequest request;

    public RequestContextImpl(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public HttpServletRequest getHttpServletRequest() {
        return request;
    }

    @Override
    public void addAttributeToJSP(String name, Object attribute) {
        request.setAttribute(name, attribute);
    }

    @Override
    public void addAttributeToHttpSession(String name, Object attribute) {
        request.getSession().setAttribute(name, attribute);
    }

    @Override
    public String getParamFromJSP(String name) {
        return request.getParameter(name);
    }

    @Override
    public Object getAttributeFromJsp(String name) {
        return request.getAttribute(name);
    }

    public HttpSession getHttpSession() {
        return request.getSession();
    }

    public HttpSession getHttpSession(boolean create) {
        return request.getSession(create);
    }
}
