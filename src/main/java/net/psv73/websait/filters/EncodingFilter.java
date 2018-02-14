package net.psv73.websait.filters;

import net.psv73.websait.util.Utils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "encodingFilter", urlPatterns = {"/mcon/*"})
public class EncodingFilter implements Filter {

    public EncodingFilter() {
        super();
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");

        HttpServletRequest req = (HttpServletRequest) request;

        Utils.setLanguageFromCookie(req.getCookies());

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}