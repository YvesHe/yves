package cn.com.yves.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

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

    public void init(FilterConfig filterConfig) throws ServletException {/*
                                                                          * 过滤器初始化时,
                                                                          * 容器自动调用该方法
                                                                          */
        System.out.println("过滤器初始化!");

        String pValue = filterConfig.getInitParameter("filterParameter"); // 获取初始化参数,获取在web.xml中该filter配置的初始化参数

        countView = 0;
    }

    /**
     * 对满足该filter条件的请求,没访问一次都会先进入该方法后在进Servlet
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException { /* 在这里做过滤器要做的业务 */

        // 注意: 如果能确保项目是基于http协议的可以将ServletRequest 强转成
        // HttpServletRequest类访问实例的方法;ServletResponse类推
        ((HttpServletRequest) request).getRequestURI();

        System.out.println("过滤器在doFilter");

        System.out.println("访问次数:" + ++countView);

        String ipAddress = request.getRemoteAddr(); // 获取客户端ip地址
        System.out.println("IP " + ipAddress + ", Time "
                + new Date().toString()); // 输出ip地址及当前时间

        // 传递请求到过滤器链,继续下一个过滤器,如果后面没有过滤器了就会 去请求web资源
        chain.doFilter(request, response);
    }

    public void destroy() { /* 在Filter实例在服务器上被移除前调用。 */
        System.out.println("过滤器 销毁了!");
    }
}
