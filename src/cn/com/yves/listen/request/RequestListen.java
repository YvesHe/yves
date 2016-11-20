/**   
 * Filename:    RequestListen.java   
 * Copyright:   Copyright (c)2016  
 * Company:     Yves  
 * @version:    1.0    
 * Create at:   2016-11-20 下午4:09:02   
 * Description:  
 *
 * Author       Yves He 
 */
package cn.com.yves.listen.request;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

/**
 * 调用机制：
 * 
 * requestInitialized：request对象被创建时
 * 
 * requestDestroyed：request对象被销毁时
 * 
 * @author Yves He
 * 
 */
public class RequestListen implements ServletRequestListener {

    public void requestDestroyed(ServletRequestEvent arg0) {// 请求对象被销毁

    }

    public void requestInitialized(ServletRequestEvent arg0) {// 请求对象被创建

    }

}
