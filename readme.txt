1.如何增加对异常日志的处理。

2.如何增加连接池的处理。

3.数据库的操作,增加事务的回滚操作.


4.数据库中userName是否允许重名

//contextPath context是指上下文,也是指的工程,所以ContextPath  也就是工程路径   本项目是指的是 /1yves
String path = request.getContextPath();

//http://localhost:8080/1yves/
String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";