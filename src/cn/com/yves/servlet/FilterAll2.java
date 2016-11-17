package cn.com.yves.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 第二个do Filter
 * 
 * @author Yves He
 * 
 */
public class FilterAll2 implements Filter {

    public void destroy() {

    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        // 传递请求到过滤器链,继续下一个过滤器
        chain.doFilter(request, response);

    }

    public void init(FilterConfig filterConfig) throws ServletException {

    }

}
