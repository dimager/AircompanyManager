package com.epam.jwd.controller.filter;

import com.epam.jwd.controller.command.Attributes;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.Objects;

@WebFilter(servletNames = "ApplicationServlet")
public class LocaleFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        if (Objects.nonNull(httpServletRequest.getParameter(Attributes.SESSION_LOCALE_ATTRIBUTE_NAME))) {
            httpServletRequest.getSession().setAttribute("lang", request.getParameter(Attributes.SESSION_LOCALE_ATTRIBUTE_NAME));
        }
        chain.doFilter(request, response);
    }
}
