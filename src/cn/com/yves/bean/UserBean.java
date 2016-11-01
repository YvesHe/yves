package cn.com.yves.bean;

public class UserBean {

	private String userId;// 系统的id

	/* 三种可以登录的方式 */
	private long userCount;// 系统自动生成一个账号 必须
	private String userName;// 用户名(账号) : 唯一邮箱注册 必须 userName代表邮箱
	private String userPhoneNumber;// 手机号,可以为null

	private String userPwd;// 用户密码

	private String userNickname;// 昵称,默认为账号名
	private String userDesc; // 用户的其他信息

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public long getUserCount() {
		return userCount;
	}

	public void setUserCount(long userCount) {
		this.userCount = userCount;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhoneNumber() {
		return userPhoneNumber;
	}

	public void setUserPhoneNumber(String userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserNickname() {
		return userNickname;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}

	public String getUserDesc() {
		return userDesc;
	}

	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}

}
