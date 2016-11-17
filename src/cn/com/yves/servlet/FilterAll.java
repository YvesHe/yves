package cn.com.yves.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

//配置过滤器: 注意: 过滤器一定要配置在servlet的前面
/**
 * 当web容器启动的时候，就会自动调用init(FilterConfig
 * arg0)来对filter进行初始化，当关闭web容器，关机，或者reload整个应用时
 * ，都会调用destroy()来关闭filter。也就是说，当web容器启动时，filter就被加载到内存，并在destroy()调用之前都常驻内存。
 * 总之:servlet filter listener 都是单例的，就是说在运行时只存在一个实例。
 * 
 * @author Yves He
 * 
 */
public class FilterAll implements Filter {
    // 网站浏览次数,所有的请求都算一次浏览 包括ajax局部刷新 和 错误我访问路径.
    private int countView;

    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器初始化!");

        countView = 0;
    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        System.out.println("过滤器在doFilter");

        System.out.println("访问次数:" + ++countView);

        // 继续传递请求?
        chain.doFilter(request, response);
    }

    // 当关闭web容器时才调用,也及时服务器关闭了.
    public void destroy() {
        System.out.println("过滤器 销毁了!");
    }
}
