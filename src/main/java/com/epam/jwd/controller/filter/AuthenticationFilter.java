package com.epam.jwd.controller.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(urlPatterns = "/*", servletNames = "myServlet", filterName = "AuthFilter")
public class AuthenticationFilter implements Filter {

    private HttpServletRequest httpServletRequest;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
     //   System.out.println(((HttpServletRequest) request).getSession(false).getAttribute("loginState"));
        chain.doFilter(request, response);
//
//        if (session == null) {   //checking whether the session exists
//            res.sendRedirect("/controller?command=SHOW_LOGIN_PAGE");
//        } else {
//            // pass the request along the filter chain
//            chain.doFilter(request, response);
//        }
//
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        HttpSession session = httpRequest.getSession(false);
//        String commandParam = httpRequest.getParameter("command");
//        boolean isLogged = false;
//        if (session != null && session.getAttribute(JSPParameters.SESSION_SUCCESSFULLY_LOGIN_STATE_ATTRIBUTE_NAME)!= null){
//            isLogged = (boolean)session.getAttribute(JSPParameters.SESSION_SUCCESSFULLY_LOGIN_STATE_ATTRIBUTE_NAME);
//            System.out.println("isLogged = " + isLogged);
//        }
//        if (isLogged) {
//            chain.doFilter(request, response);
//        } else if ((commandParam != null && commandParam.contains("SIGNUP")) || httpRequest.getRequestURI().contains("assets")) {
//            chain.doFilter(request, response);
//        } else {
//            httpRequest.getRequestDispatcher("/controller?command=SHOW_LOGIN_PAGE").forward(request, response);
//        }
    }
}
