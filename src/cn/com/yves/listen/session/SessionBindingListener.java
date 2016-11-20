/**   
 * Filename:    SessionBindingListener.java   
 * Copyright:   Copyright (c)2016  
 * Company:     Yves  
 * @version:    1.0    
 * Create at:   2016-11-20 下午4:15:41   
 * Description:  
 *
 * Author       Yves He 
 */
package cn.com.yves.listen.session;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

/**
 * 
 * 注意:不需要配置web.xml（只有实现接口SessionBindingListener得监听类不需要在web.xml中配置）
 * 
 * 调用机制：
 * 
 * valueBound() : 对象通过session.setAttribute() 被绑定到session上时
 * 
 * 
 * valueUnbound():对象从session上删除时
 * (调用session.removeAttribute()和session.invalidate()或session超时)
 * 
 * 
 * @author Yves He
 * 
 */
public class SessionBindingListener implements HttpSessionBindingListener {

    public void valueBound(HttpSessionBindingEvent arg0) {// 通过session.setAttribute()被绑定到session上时，则这个对象的valueBound()被调用。

    }

    public void valueUnbound(HttpSessionBindingEvent arg0) {// 调用session.removeAttribute()和session.invalidate()或session超时(失效)

    }

}
