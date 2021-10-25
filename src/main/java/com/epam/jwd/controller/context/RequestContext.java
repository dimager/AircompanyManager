package com.epam.jwd.controller.context;

import jakarta.servlet.http.HttpServletRequest;

public interface RequestContext {
    void addAttributeToJSP(String name, Object attribute);
}
