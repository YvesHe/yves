/**   
 * Filename:    SessionAttributeListen.java   
 * Copyright:   Copyright (c)2016  
 * Company:     Yves  
 * @version:    1.0    
 * Create at:   2016-11-20 上午2:23:51   
 * Description:  
 *
 * Author       Yves He 
 */
package cn.com.yves.listen.session;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * 产生事件的方法：
 * 
 * session.setAttribute(“userName”,”tom”); 第一次setAttribute("userName")触发
 * 
 * session.setAttribute(“userName”,”tom”);
 * session中已经有了userName,再次session.setAttribute(“userName”,”tom”);触发
 * 
 * session.removeAttribute(“userName”); 从session中移除已经有了属性userName
 * 
 * @author Yves He
 * 
 */
public class SessionAttributeListen implements HttpSessionAttributeListener {

    public void attributeAdded(HttpSessionBindingEvent e) {// session中增加的键在session中之前不存在时,调用setAttribute("键")时触发;

        HttpSession session = e.getSession();// 获取触发的session

        e.getSource();// 获取触发的session,返回的引用时Object

        e.getName();// 获取想session中addAttribute的Key

        e.getValue();// 获取想session中addAttribute的Name

    }

    public void attributeRemoved(HttpSessionBindingEvent e) {// 从session中删除某个对象时触发,调用的session.removeAttribute("键")时触发
        // 当session中没有要移除的属性,调用方法 session.removeAttribute("key")时不会触发.
        System.out.println("移除属性!");
    }

    public void attributeReplaced(HttpSessionBindingEvent e) {// 原来session中已经有了改键值对,调用的session.setAttribute("键")时触发

    }

}
