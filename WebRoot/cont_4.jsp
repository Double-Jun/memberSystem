<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//Dth XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/Dth/xhtml1-strict.dth">
<html xmlns="http://www.w3.org/1999/xhtml" lang="zh-CN">
<head>
<title>会员体检</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="description" content="" />
<meta name="keywords" content="" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/cont_4.js"></script>
<link rel="stylesheet" href="css/reset.css" />
<link rel="stylesheet" href="css/cont_4.css" />
</head>
<body>
	<div id="cont_4">
		<div class="header">
			<form action="PhysicalServlet?method=readExcel" method="post"
				enctype="multipart/form-data">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span>读取Excel文件到数据库：</span>
				<input type="file" class="input_file" name="excel" /> <input
					type="submit" class="check_sub" value="更新数据库体检字段" />
			</form>
		</div>
		<div class="content">
			<div class="content_head">
				<form action="PhysicalServlet?method=conditionQuery" method="post">

					<span>性别：</span> <select class="form_select1"
						name="conditionGender">
						<option value=""></option>
						<option value="男">男</option>
						<option value="女">女</option>
					</select> &nbsp;&nbsp;&nbsp;&nbsp; <span> 所在部门： </span> <select
						class="form_select2" name="conditionDep">
						<option value=""></option>
						<c:forEach items="${deps }" var="dep">
							<option value="${dep.depName }"
								<c:if test="${natureInfo.depName}== ${dep.depName }">selected='selected'</c:if>>${dep.depName
								}</option>
						</c:forEach>
					</select> &nbsp;&nbsp;&nbsp;&nbsp; <span>体检</span> <select
						class="form_select3" name="physical">
						<option value=""></option>
						<option>已体检</option>
						<option>未体检</option>
					</select> &nbsp;&nbsp;&nbsp;&nbsp; <span>年龄</span> <input type="text"
						class="form_age" name="conditionMinAge" /> 
						<span>--</span> 
						<input type="text" class="form_age" name="conditionMaxAge" /> 
						<input type="text" class="hidden" id="age_input" />
						<input type="submit" class="sub" value="" />
				</form>
			</div>

			<table>
				<tr style="background:#99CCFF;" class="first">
					<td class="td_name">姓名</td>
					<td class="ta_gender">性别</td>
					<td class="td_birth">出生年月</td>
					<td class="td_dept">所在部门</td>
					<td class="td_id">身份证号</td>
					<td class="td_school">毕业院校</td>
					<td class="td_check">体检</td>
				</tr>
				<c:forEach items="${requestScope.pageBean.listBean}" var="list">
					<tr>
						<td class="td_name">${list.name }</td>
						<td class="ta_gender">${list.gender }</td>
						<td class="td_birth">${list.birthday }</td>
						<td class="td_dept">${list.depName }</td>
						<td class="td_id">${list.IDcard }</td>
						<td class="td_school">${list.schoolTag }</td>
						<td class="td_check">${list.status }</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div class="footer">
			<div class="take_excel">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span><a href="servlet/Export?pageBean=${pageBean.url }">报表导出</a></span>
			</div>
			<div class="pages">
				<%@include file="pages.jsp"%>
			</div>
		</div>
	</div>
</body>
</html>