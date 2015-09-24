<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="zh-CN">
<head>
<title>黑龙江大学工会管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="description" content="" />
<meta name="keywords" content="" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/change_pwd.js"></script>
<script type="text/javascript" src="js/index.js"></script>
<script type="text/javascript" src="js/location.js"></script>
<link rel="stylesheet" href="css/reset.css" />
<link rel="stylesheet" href="css/index.css" />
<link rel="stylesheet" href="css/single.css" />
<style>
.content_right .cont {
	width: 998px;
	height: 478px;
	display: block;
	position: absolute;
	background: #fff;
}

.content_right .cont_1 {
	z-index: 5;
}
</style>
</head>
<body>
	<div id="all">
		<div id="header"></div>
		<div id="content">
			<div class="content_left">
				<ul>
					<li class="content_li1" style="background:#4696D6">管理菜单</li>
					<li><a
						href="MemberInfoServlet?method=showAllMemberInfo" target="cont" class="li_a">会员信息</a></li>

					<li><a href="MemberManagerServlet?method=listAllMember"
						target="cont" class="li_a">会员管理</a></li>

					<li><a href="FellowsShip?method=showAllMember" target="cont" class="li_a">单身联谊</a></li>

					<li class="S"><a href="PhysicalServlet?method=conditionQuery"
						target="cont" class="li_a li_last">会员体验</a></li>
				</ul>
				<div class="change_pwd">
					<a href="#" id="hi_change_pwd">修改密码</a>
				</div>
				<div class="change_back">
					<a href="LoginServlet?method=logoutAdmin" id="hi_change_pwd">注	销</a>
				</div>
			</div>
			<div class="content_right">
				<iframe name="cont" scrolling=no frameborder=0 class="cont_1 cont" src="welcome.jsp">
				</iframe>
			</div>
		</div>
		<div id="footer">
			<span>Copyright &copy; 2014-2020 HEILONGJIANG UNIVERSITY. All
				Rights Reserved.</span>
		</div>
	</div>
	<div id="shade"></div>
	<div id="change_pop_up">
		<form action="LoginServlet?method=alterAdmin" method="post">
			<h1>修改密码</h1>
			<img src="images/close.png" class="change_close" />
			<ul>
				<li class="li_first"><span>账号:</span> 	
<!-- 				<input type="text" class="user" name="user" value="${admin.username }" readonly/>  -->
					<span class="user">${admin.username }</span>
				
				</li>
				<li><span>原始密码:</span> <input type="password" class="pwd"
					name="password" /></li>
				<li><span>新密码:</span> <input type="password" class="new_pwd"
					name="newPassword" /></li>
				<li><span>确认新密码:</span> <input type="password" name="reNewPassword"
					class="new_pwd1" /></li>
				<li style="text-align:center;"><input type="button" value="取  消"
					class="cancel li_last change_close" /> <input type="submit"
					value="确  认" class="li_sub li_last" /></li>
			</ul>
		</form>
	</div>
</body>
</html>
