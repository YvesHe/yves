/**   
 * Filename:    SessionListen.java   
 * Copyright:   Copyright (c)2016  
 * Company:     Yves  
 * @version:    1.0    
 * Create at:   2016-11-20 上午1:27:41   
 * Description:  
 *
 * Author       Yves He 
 */
package cn.com.yves.listen.session;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 调用机制:
 * 
 * sessionCreated() : session创建时调用
 * 
 * sessionDestroyed() :session销毁时调用
 * 
 * @author Yves He
 * 
 */
public class SessionListen implements HttpSessionListener {
    /* HttpSessionListener接口中只有两个方法 */

    public void sessionCreated(HttpSessionEvent e) {// HttpSessionEvent
                                                    // 对象里面封装了触发时间的信息

        HttpSession session = e.getSession();// 获得触触发事件的session对象

        Object source = e.getSource(); // 该方法返回的是Object对象,实质上也是HttpSession对象
                                       // ,可以强转成HttpSession对象

        System.out.println("session创建了!");

    }

    public void sessionDestroyed(HttpSessionEvent e) {// session销毁的时候触发

        System.out.println("session销毁了");
    }

}
