package com.youthlin.jblog.listener;

import com.youthlin.jblog.controller.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lin on 2016-09-04-004.
 * 未登录不能访问仪表盘
 */
public class MyFilter implements Filter {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.debug("初始化Filter");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        if (Context.getCurrentUser() == null) {
            log.debug("未登录！");
            ((HttpServletResponse) response).sendRedirect(req.getServletContext().getContextPath() + "/login.xhtml");
        } else {
            log.debug("filter url:{}?{}", req.getRequestURI(), req.getQueryString());
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        log.debug("销毁Filter");
    }
}
