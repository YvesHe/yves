package cn.com.yves.filter;

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
public class FilterAll2 implements Filter {/*
                                            * 获取配置在改filter中的初始化参数　：分析:由于该接口总共就三个方法
                                            * ，this.方法名是不能获取初始化参数的;
                                            * 初始化参数在FilterConfig对象中
                                            */

    private FilterConfig filterConfig;// 用来获取配置信息

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        String requestEncoding = this.filterConfig
                .getInitParameter("requestEncoding");// 获取配置在改filter中的init-name为requestEncoding的参数

        String responseEncoding = this.filterConfig
                .getInitParameter("responseEncoding");// 获取配置在改filter中的init-param的参数
        request.setCharacterEncoding(requestEncoding);
        response.setContentType(responseEncoding);

        chain.doFilter(request, response); // 传递请求到过滤器链,继续下一个过滤器
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }
}
