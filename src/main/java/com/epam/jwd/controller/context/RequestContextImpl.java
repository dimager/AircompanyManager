package com.epam.jwd.controller.context;


import jakarta.servlet.http.HttpServletRequest;

public class RequestContextImpl implements RequestContext{
    private final HttpServletRequest request;

    public RequestContextImpl(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void addAttributeToJSP(String name, Object attribute) {
        request.setAttribute(name, attribute);
    }
}
