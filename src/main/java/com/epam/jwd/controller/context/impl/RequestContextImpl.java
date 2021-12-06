package com.epam.jwd.controller.context.impl;

import com.epam.jwd.controller.context.RequestContext;
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
    public String getParamFromJSP(String name) {
        return request.getParameter(name);
    }

    @Override
    public HttpSession getHttpSession() {
        return request.getSession();
    }

    @Override
    public HttpSession getHttpSession(boolean create) {
        return request.getSession(create);
    }

}
