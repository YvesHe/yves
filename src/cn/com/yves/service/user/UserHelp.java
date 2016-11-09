package cn.com.yves.service.user;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.yves.bean.UserBean;
import cn.com.yves.constant.Constant;

/**
 * 抽象类,静态方法,然后提供静态方法给外界调用: 防止反射类new构造实例对象
 * 
 * @author Yves He
 * 
 */
public abstract class UserHelp {

    private UserHelp() {

    }

    /**
     * 设置编码格式
     * 
     * @param request
     * @param response
     * @throws UnsupportedEncodingException
     */
    public static void setEncoding(HttpServletRequest request,
            HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
    }

    /**
     * 判断是否登录
     * 
     * @param request
     * @return
     */
    public static boolean isLogin(HttpServletRequest request) {
        Object userBean = request.getSession(true).getAttribute("userBean");
        return userBean != null ? true : false;
    }

    /**
     * 自动生成uuid作为user的 id
     * 
     * @return
     */
    public static String createUserId() {
        return UUID.randomUUID().toString();
    }

    /**
     * 获取页面数据(包括新增或修改)
     * 
     * @param request
     * @return
     */
    public static UserBean getUserBeanDataFromJSP(HttpServletRequest request) {
        UserBean uBean = new UserBean();
        uBean.setUserCount(Constant.USER_COUNT_EMAIL);
        // id不是null则设置它的值
        if (request.getParameter("userId") != null) {
            uBean.setUserId(request.getParameter("userId"));
        } else {
            uBean.setUserId(UserHelp.createUserId());
        }
        uBean.setUserDesc(request.getParameter("userDesc"));
        uBean.setUserName(request.getParameter("userName"));
        uBean.setUserNickName(request.getParameter("userNickName"));
        uBean.setUserPhoneNumber(request.getParameter("userPhoneNumber"));
        uBean.setUserPowerId(Integer.valueOf(request
                .getParameter("userPowerId").trim()));
        uBean.setUserPwd(request.getParameter("userPwd"));
        return uBean;
    }
}
