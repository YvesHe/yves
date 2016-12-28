/**   
 * Filename:    ApplicationtAttributeListener.java   
 * Copyright:   Copyright (c)2016  
 * Company:     Yves  
 * @version:    1.0    
 * Create at:   2016-11-20 下午4:19:05   
 * Description:  
 *
 * Author       Yves He 
 */
package cn.com.yves.listen.application;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;

/**
 * 调用机制：
 * 
 * application.setAttribute(“userName”,”tom”); 第一次setAttribute
 * 
 * application.setAttribute(“userName”,”tom”); 已经存在时setAttribute
 * 
 * application.removeAttribute(“userName”);从application中移除时(也就是从servletContext中)
 * 
 * @author Yves He
 * 
 */
public class ApplicationtAttributeListener implements
        ServletContextAttributeListener {

    public void attributeAdded(ServletContextAttributeEvent arg0) {

    }

    public void attributeRemoved(ServletContextAttributeEvent arg0) {

    }

    public void attributeReplaced(ServletContextAttributeEvent arg0) {

    }

}
