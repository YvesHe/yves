package cn.com.yves.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 这是一个service 多个service 实现的usre版本
 * 
 * @author Yves He
 * 
 */
public interface Service {

    /**
     * 所有的子service实现都应该实现该方法.
     * 
     * @param request
     * @param response
     */
    void execute(HttpServletRequest request, HttpServletResponse response);

}
