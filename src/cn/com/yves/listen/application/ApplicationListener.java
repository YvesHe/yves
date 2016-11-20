/**   
 * Filename:    ApplicationListener.java   
 * Copyright:   Copyright (c)2016  
 * Company:     Yves  
 * @version:    1.0    
 * Create at:   2016-11-20 下午4:16:39   
 * Description:  
 *
 * Author       Yves He 
 */
package cn.com.yves.listen.application;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 调用机制：
 * 
 * Web容器加载项目时执行contextInitialized方法，应用程序被容器销毁是执行contextDestroyed方法。
 * 暂时可以理解为启动和关闭容器.
 * 
 * @author Yves He
 * 
 */
public class ApplicationListener implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent arg0) {// 销毁时

    }

    public void contextInitialized(ServletContextEvent arg0) {// 加载项目时

    }

}
