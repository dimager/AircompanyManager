package com.epam.jwd.controller.filter;

import com.epam.jwd.controller.command.Attributes;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Objects;

@WebFilter(servletNames = "ApplicationServlet")
public class AuthenticationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        boolean state = false;
        boolean isLoginPage = Objects.nonNull(req.getQueryString()) && req.getQueryString().contains("LOGIN");
        boolean isSignupPage = Objects.nonNull(req.getQueryString()) && req.getQueryString().contains("SIGNUP");
        boolean IsSetLocale = Objects.nonNull(req.getQueryString()) && req.getQueryString().contains("SET_LOCALE");
        System.out.println("isSignupPage = " + isSignupPage);
        System.out.println("isLoginPage = " + isLoginPage);
        if (Objects.nonNull(session)) {
            if (Objects.nonNull(session.getAttribute(Attributes.SESSION_LOGIN_STATE_ATTRIBUTE_NAME))) {
                state = (boolean) session.getAttribute(Attributes.SESSION_LOGIN_STATE_ATTRIBUTE_NAME);
            }
            System.out.println("state = " + state);
            if (state || isLoginPage || isSignupPage || IsSetLocale) {
                chain.doFilter(request, response);
            } else {
                ((HttpServletResponse) response).sendRedirect("/");
            }
        }
    }
}
