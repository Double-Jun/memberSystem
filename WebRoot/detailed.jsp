<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>



<!DOCTYPE html PUBLIC "-//W3C//Dth XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/Dth/xhtml1-strict.dth">
<html xmlns="http://www.w3.org/1999/xhtml" lang="zh-CN">
<head>
<title>会员详情</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="description" content="" />
<meta name="keywords" content="" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/index.js"></script>
<link rel="stylesheet" href="css/reset.css" />
<link rel="stylesheet" href="css/modify.css" />
<style>

input {
	text-align: center;
}

</style>
</head>
<body>
	<div id="all">
		<div id="header"></div>
		<div id="content">
			<form>
				<div class="content_left content" style="background:-yellow">
					<ul>
						<li><span>姓名</span><input type="text" name="name"
							class="input1" readonly value="${natureInfo.name}"/></li>
						<li><span>性别</span><input type="text" name="gender_"
							class="input1" readonly value="${natureInfo.gender }"/></li>
						<li><span>民族</span><input type="text" name="nation"
							class="input1" readonly value="${natureInfo.nation }"/></li>
						<li><span>出生年月</span><input type="text" name="birth"
							class="input1" readonly value="${natureInfo.birthday} "/></li>
						<li><span>身份证 </span><input type="text" name="id"
							class="input1" readonly value="${natureInfo.IDcard}" /></li>
						<li><span>籍贯</span><input type="text" name="native_place"
							class="input1" readonly value="${natureInfo.address}" /></li>
						<li><span>联系方式</span><input type="text" name="call"
							class="input1" readonly value="${natureInfo.telphone}" /></li>
						<li><span>在岗情况</span><input type="text" name="situation_"
							class="input1" readonly value="${socialInfo.workStatus}" /></li>
					</ul>
				</div>
				<div class="content_center content">
					<ul>
						<li style="font-size:14px;color:#666"><span>兴趣爱好</span> <input
							type="text" name="interesting" class="input1" readonly /></li>
						<li><span>婚姻状况</span> <input type="text" name="marry_"
							class="input1" readonly value="${natureInfo.marriage}"/></li>
						<li><span>政治面貌</span><input type="text"
							name="political_status" class="input1" readonly value="${socialInfo.politics}"/></li>
						<li><span>学历</span><input type="text" name="level"
							class="input1" readonly value="${socialInfo.degree}"/></li>
						<li><span>从事专业方向</span><input type="text" name="direction"
							class="input1" readonly value="${socialInfo.profession}" /></li>
						<li><span>工作时间</span><input type="text" name="work_time"
							class="input1" readonly value="${socialInfo.workingTime}" /></li>
						<li><span>来校时间</span><input type="text" name="phone"
							class="input1" readonly value="${socialInfo.startTime}" /></li>
						<li><span>毕业学校</span><input type="text" name="schoolTag"
							class="input1" readonly value="${natureInfo.schoolTag}" /></li>
					</ul>
				</div>
				<div class="content_right content">
					<ul>
						<div id="detailed_photo">
							<img src="${natureInfo.photo }" width="100%" height="100%"/>
						</div>
						<li><span>部门联系方式</span><input type="text" name="to_the_time"
							class="input1" readonly /></li>
						<li><span>职位</span><input type="text" name="position"
							class="input1" readonly value="${socialInfo.position}" /></li>
						<li><span>党政职务</span><input type="text" name="govern_job"
							class="input1" readonly value="${socialInfo.partyPosition}" /></li>
						<li><span>部门名称</span><input type="text" name="office_name"
							class="input1" readonly value="${natureInfo.depName}" /></li>
						<li><span>负责人</span><input type="text" name="principal"
							class="input1" readonly /></li>

					</ul>
				</div>
				<div class="save">
					<div>
						 <span class="abolish">
						 	<a href="index.html#manage">取消</a>
						 </span>
					</div>
				</div>
			</form>
		</div>

		<div id="footer">
			<span>Copyright &copy; 2014-2020 HEILONGJIANG UNIVERSITY. All Rights Reserved.</span>
		</div>
	</div>
</body>
</html>
