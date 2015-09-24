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
<link rel="stylesheet" href="css/cont_2.css" />
</head>
<body>
    <div id="cont_2">
        <form action="MemberManagerServlet?method=findByName" method="post" >
            <div id="sub">
                <span>姓名</span>
                <div class="text">
                   <input type="text" name="name" />
                </div>
                <div class="sub">
                    <input type="submit" value="" />   
                </div>
            </div>
        </form >
        <hr>
        <form action="MemberManagerServlet?method=delBatchMember" method="post" >
        <table>
            <tr style="background:#99CCFF;" class="tr_first">
                <th><span class="checkAll">全选&nbsp;&nbsp;&nbsp;</span>
                <input type="checkbox" class="check_all" value="checkAll" /></th>
                <th>姓名</th>
                <th class="gender_marry">性别</th>
                <th>出生年月</th>
                <th class="area">毕业院校</th>
                <th class="gender_marry">婚否</th>
                <th class="dept">部门名称</th>
                <th colspan=3 >操作</th>
            </tr>
            
             <c:forEach items="${requestScope.pageBean.listBean}" var="list">
            <tr>
                <td><input type="checkbox" class="check_son" value="${list.id}" name="ids"/ ></td>
                <td>${list.name }</td>
                <td class="gender_marry">${list.gender }</td>
                <td>${list.birthday }</td>
                <td class="area">${list.schoolTag }</td>
                <td class="gender_marry">${list.marriage}</td>
                <td  class="dept">${list.depName}</td>
                <td class="operation"><a href="MemberManagerServlet?method=detailsInfo&id=${list.id}" target="_blank" alt="" class="a_details">详情</a></td>
                <td class="operation"><a href="MemberManagerServlet?method=deleteMember&id=${list.id}" alt="" class="delete_">删除</a></td>
                <td class="operation"><a href="MemberManagerServlet?method=showaltermember&id=${list.id}" target="_blank">修改</a></td>
            </tr>
            </c:forEach>
            
            
            
        </table>
        
        <div class="add">
             <input type="submit"  value="删除选中" class="input1" id="delete_all"/>
            <a href="addmember.jsp" target="_blank"><input type="button" name="add" value="添加" class="input2"/></a>
        </div>
        
        </form>
        <div class="pages">
       
        <%@include file="pages.jsp"%>
        </div>
    </div>
</body>
</html>
