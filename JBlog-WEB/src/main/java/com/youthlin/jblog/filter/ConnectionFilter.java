package com.youthlin.jblog.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.transaction.UserTransaction;
import java.io.IOException;

/**
 * Created by lin on 2016-09-08-008.
 * 懒加载
 */
public class ConnectionFilter implements Filter {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Resource
    private UserTransaction utx;

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        try {
//            utx.begin();
//            log.debug("开始UserTransaction");
            chain.doFilter(request, response);
//            log.debug("结束UserTransaction");
//            utx.commit();
//        } catch (Exception e) {
//            log.debug("UserTransaction异常");
//            e.printStackTrace();
//        }

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }
}
