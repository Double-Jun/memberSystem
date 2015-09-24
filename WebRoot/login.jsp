<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE html PUBLIC "-//W3C//Dth XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/Dth/xhtml1-strict.dth">
<html xmlns="http://www.w3.org/1999/xhtml" lang="zh-CN">
<head>
<title>登陆</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="description" content="" />
<meta name="keywords" content="" />
<link rel="stylesheet" type="text/css" href="css/reset.css" />
<link rel="stylesheet" type="text/css" href="css/login.css" />
</head>
<body style="background:url(images/bg.png);">
    <div id="header">
    </div>
    <div id="content">
        <div class="left">
            <img src="images/2.png" />
        </div>
        <div class="right">
            <form action="LoginServlet?method=login" method="post">
                <input type="text" name="username" class="account input_login" placeholder="账号登陆" />
                <span class="icon1 icon"  style="background:url(images/login.png) -110px -273px"></span>
                <input type="password" name="password" class="psw input_login" placeholder="密码" />
                <span class="icon icon2"  style="background:url(images/login.png) -136px -273px"></span>
                <div class="input_sub">
                    <input type="submit" class="sub type_sub" value="登录"/>
                    <input type="reset" class="reset type_sub" value="重置"/>
                </div>
                <div class="forget_pwd">
                    <a href="#">忘记密码？</a>
                </div>
            </form>
        </div>
    </div>

</body>
</html>

