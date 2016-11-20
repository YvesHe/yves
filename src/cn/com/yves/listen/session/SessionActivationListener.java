/**   
 * Filename:    SessionActivationListener.java   
 * Copyright:   Copyright (c)2016  
 * Company:     Yves  
 * @version:    1.0    
 * Create at:   2016-11-20 下午4:13:40   
 * Description:  
 *
 * Author       Yves He 
 */
package cn.com.yves.listen.session;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;

/**
 * 
 * sessionDidActivate() 被活化时调用
 * 
 * sessionWillPassivate() 被钝化时调用
 * 
 * 活化：将对象反序列化到内存
 * 
 * 钝化：将对象序列化到硬盘
 * 
 * @author Yves He
 * 
 */
public class SessionActivationListener implements HttpSessionActivationListener {

    public void sessionDidActivate(HttpSessionEvent se) {// 被活化,反序列到内存

    }

    public void sessionWillPassivate(HttpSessionEvent se) {// 被钝化,序列化到硬盘

    }

}
