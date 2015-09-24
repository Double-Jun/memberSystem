<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//Dth XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/Dth/xhtml1-strict.dth">
<html xmlns="http://www.w3.org/1999/xhtml" lang="zh-CN">
<head>
<title>管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="description" content="" />
<meta name="keywords" content="" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.ui.js"></script>
<script type="text/javascript" src="js/index.js"></script>
<link rel="stylesheet" href="css/reset.css" />
<link rel="stylesheet" href="css/cont_1.css" />
</head>
<body>
    <div id="cont_1">
        <form action="MemberInfoServlet?method=findMember" method="post">
            <div id="select">&nbsp; 
                <span>类型查询：</span>
                <select name="condition" class="select_son">
                    <option value="name">姓名</option>
                    <option value="depName">部门</option>
                    <option value="IDcard">身份证号</option>
                </select>
            </div>
            <div id="sub">
                <div  class="text" >
                    <input type="text" name="value"/>
                </div>
                <div class="sub">
                    <input type="submit" value="" />   
                </div>
                
                       
            </div>
        </form>
        <hr />
        <div id="info">
            <table class="first">
                <tr style="background:#99CCFF;">
                    <th class="name">姓名</th>
                    <th class="gender_marry_operation">性别</th>
                    <th class="dept">所在部门</th>
                    <th class="birth">出生年月</th>
                    <th class="id_email">身份证号</th>
                    <th>籍贯</th>
                    <th>联系方式</th>
                    <th class="gender_marry_operation">婚否</th>
                    <th class="gender_marry_operation">操作</th>
                </tr>
                 <c:forEach items="${requestScope.pageBean.listBean}" var="member">
				<tr>
					<td class="name">${member.name }</td>
					<td class="gender_marry_operation">${member.gender }</td>
					<td  class="dept">${member.depName }</td>
					<td class="birth">${member.birthday }</td>
					<td class="id_email">${member.IDcard }</td>
					<td>${member.address }</td>
					<td>${member.telphone }</td>
					<td class="gender_marry_operation">${member.marriage }</td>
					<td class="gender_marry_operation"><a
						href="<c:url value='MemberManagerServlet?method=detailsInfo&id=${member.id }' />" target="_blank">详情</a></td>
				</tr>
			</c:forEach>
            </table>

            <div class="pages">
               
           <%@include file="pages.jsp"%>
            </div>
        </div>
    </div>
</body>
</html>
