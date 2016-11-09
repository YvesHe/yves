package cn.com.yves.service.user;

import cn.com.yves.service.Service;

/**
 * 抽象类,静态方法,然后提供静态方法给外界调用: 防止反射类new构造实例对象
 * 
 * @author Yves He
 * 
 */
public abstract class UserServiceFactory {
    private UserServiceFactory() {

    }

    public static Service createLoginService() {
        return new UserLoginService();
    }

    public static Service createExitLoginService() {
        return new UserExitService();
    }

    public static Service createAddService() {
        return new UserAddService();
    }

    public static Service createDelService() {
        return new UserDelService();
    }

    public static Service createUpdateService() {
        return new UserUpdateService();
    }

    public static Service createQueryService() {
        return new UserQueryService();
    }

    public static Service createListService() {
        return new UserListService();
    }

}
