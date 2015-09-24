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
<link rel="stylesheet" href="css/cont_3.css" />
</head>
<body>
    <div id="cont_3">
        <div class="cont_3_top">
            <form action="FellowsShip?method=conditionQuery" method="post" class="cont_3_top_form">
                <ul>
                    <li class="li_marry">
                        <span>婚姻状况 </span>
                        <select name="conditionMarriage" >  
                            <option value=""></option> 
                            <option value="已婚">已婚</option>
                            <option value="未婚">未婚</option>  
                        </select>
                    </li>
                    <li class="li_gender" >
                        <span>性别 </span>
                        <select name="conditionGender">  
                            <option value=""></option> 
                            <option value="男">男</option>
                            <option value="女">女</option>  
                        </select>
                    </li>
                    <li class="li_dept">
                        <span>所在部门</span>
                        <select name="conditionDep" class="department2">
                            <option value=""></option>
								<c:forEach items="${deps }" var="dep">
									<option value="${dep.depName }"
										<c:if test="${natureInfo.depName}== ${dep.depName }">selected='selected'</c:if>>${dep.depName
										}</option>
								</c:forEach>
                        </select>
                    </li>
                    <li class="li_age">
                    	<span>年龄</span>
						<input type="text"  name="conditionMinAge"  class="age_number"/>
						&nbsp;&nbsp;&nbsp;&nbsp;<label style="font-size:14px;">到</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="text"  name="conditionMaxAge"  class="age_number"/>
						<input type="text"  class="hidden" id="age_input"/>
                    </li>
                    <li class="li_sub">
                        <input type="submit" name="sub2" value="查询" class="sub2"/>
                    </li> 
                </ul>
            </form>
            
            
        </div>
        <table class=""table1>
            <tr style="background:#99CCFF;">
                <th>姓名</th>
                <th class="gender">性别</th>
                <th class="dept">所在部门</th>
                <th>出生年月</th>
                <th>籍贯</th>
                <th class="gender_marry">婚否</th>
                <th class="gender_marry">操作</th>
            </tr>
            <c:forEach items="${pageBean.listBean }" var="member">
            <tr>
                <td>${member.name }</td>
                <td class="gender_marry">${member.gender }</td>
                <td  class="dept">${member.depName }</td>
                <td>${member.birthday }</td>
                <td>${member.address }</td>
                <td class="gender_marry">${member.marriage }</td>
                <td class="gender_marry"><a href="MemberManagerServlet?method=detailsInfo&id=${member.id}" target="_blank">详情</a></td>
            </tr>
            </c:forEach>
            
       </table>
       <div class="pages">
           <!--<iframe src="pages.jsp" name="cont_2" scrolling=no frameborder=0 class="cont_2 cont">
           </iframe> 
       -->  <%@include file="pages.jsp"%>
       </div>
    </div>
</body>
</html>
