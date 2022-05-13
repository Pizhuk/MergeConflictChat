package com.mergemconflictchat.services;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter
public class CORSFilter implements Filter {

    public final String RESPONSE_CORS_HEADER = "Access-Control-Allow-Origin";
    public final String CORS_RESPONSE_HEADER = "Access-Control-Allow-Methods";
    public final String OPTIONS = "OPTIONS";
    public final String HTTP_REQUEST = "GET, OPTIONS, HEAD, PUT, POST";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("cors started");

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        ((HttpServletResponse) response).addHeader(RESPONSE_CORS_HEADER, "*");
        ((HttpServletResponse) response).addHeader(CORS_RESPONSE_HEADER, HTTP_REQUEST);
        HttpServletResponse resp = (HttpServletResponse) response;

        if (((HttpServletRequest) request).getMethod().equals(OPTIONS)) {
            resp.setStatus(HttpServletResponse.SC_ACCEPTED);
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}