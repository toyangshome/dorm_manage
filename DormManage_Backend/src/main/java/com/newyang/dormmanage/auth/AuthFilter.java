package com.newyang.dormmanage.auth;

import org.springframework.boot.jta.atomikos.AtomikosDependsOnBeanFactoryPostProcessor;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author NewYang
 * @email 1013836629@qq.com
 * @date 2022/3/15 17:02
 */
@WebFilter(urlPatterns = "/*" ,filterName = "loginFilter")
public class AuthFilter implements Filter {
    @Override
    public void init (FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter (ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // TODO
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy () {
        Filter.super.destroy();
    }
}
