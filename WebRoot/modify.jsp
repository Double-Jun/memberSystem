<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//Dth XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/Dth/xhtml1-strict.dth">
<html xmlns="http://www.w3.org/1999/xhtml" lang="zh-CN">
<head>
<title>会员修改</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="description" content="" />
<meta name="keywords" content="" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/index.js"></script>
<script type="text/javascript" src="js/addMember.js"></script>
<script type="text/javascript" src="js/jquery.ui.js"></script>
<link rel="stylesheet" href="css/reset.css" />
<link rel="stylesheet" href="css/jquery-ui.css" />
<link rel="stylesheet" href="css/modify.css" />
</head>
<body>
    <div id="all">
        <div id="header">
        </div>
        <div id="content">
            <form action="MemberManagerServlet?method=alterMemberInfo" method="post" >
                <div class="content_left content" style="background:-yellow">
                    <ul>
                       <input type="hidden" name="id" value="${natureInfo.id}" />
                        <li>
							<span>姓名</span><input type="text" name="name" value="${natureInfo.name}" class="input1" id="input_name"/>
							<span class="star">*</span>
							<input type="text" class="name_warning" />
						</li>
                        <li>
                            <span>性别</span><input type="radio" name="gender" value="男" id="gender1" checked='checked'
                            <c:if test="${natureInfo.gender=='男'}">
                            </c:if> />
                            
                            <label for="gender1">&nbsp;&nbsp男</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <input type="radio" name="gender" value="女" id="gender2"
                            <c:if test="${natureInfo.gender=='女'}">
                            checked='checked'
                            </c:if>/>
                            <label for="gender2">&nbsp;&nbsp女</label>

                        </li>
                        
                        <li>
							<span>民族</span>
							<input type="text" name="nation" class="input1" value="${natureInfo.nation}" id="input_nation"/>
							<span class="star">*</span>
							<input type="text" class="name_warning" />
						</li>
                        <li>
							<span>出生年月</span>
							<input type="text" name="birthday" class="input1" value="${natureInfo.birthday}" id="input_birth"  autocomplete="off"/>
							<span class="star">*</span>
							<input type="text" class="name_warning" />
						</li>
                        <li>
							<span>身份证</span>
							<input type="text" name="IDcard" class="input1" value="${natureInfo.IDcard}" id="input_id"/>
							<span class="star">*</span>
							<input type="text" class="name_warning" />
						</li>
                        <li>
							<span>籍贯</span>
							<input type="text" name="address" class="input1" value="${natureInfo.address}"/>
						</li>
                        <li>
							<span>联系方式</span>
							<input type="text" name="telphone" class="input1" value="${natureInfo.telphone}"/>
						</li>
         
                        
                      <c:if test="${socialInfo.workStatus==''}"> selected='selected' </c:if>
										
				
                    </ul>
                </div>
                <div class="content_center content">
                    <ul>
                        <li>
                            <span>婚姻状况</span>
                            <select name="marriage">
                                    <option value="已婚"<c:if test="${natureInfo.marriage=='已婚' }">selected='selected'</c:if>>已婚</option>

						            <option value="未婚"<c:if test="${natureInfo.marriage=='未婚' }">selected='selected'</c:if>>未婚</option>
                            </select>
                        </li>
                        <li><span>照片</span><input type="file" name="photo" value="up_photo"  /></li>
                        <li><span>政治面貌</span>
                                        <select name="politics">
                                        <option value="群众" <c:if test="${socialInfo.politics=='群众'}"> selected='selected' </c:if>>群众</option>
                                        <option value="共青团员" <c:if test="${socialInfo.politics=='共青团员'}"> selected='selected' </c:if>>共青团员</option>
                                         <option value="中共预备党员" <c:if test="${socialInfo.politics=='中共预备党员'}"> selected='selected' </c:if>>中共预备党员</option>
                                        <option value="中共党员" <c:if test="${socialInfo.politics=='中共党员'}"> selected='selected' </c:if>>中共党员</option>
                                        <option value="民进会员" <c:if test="${socialInfo.politics=='民进会员'}"> selected='selected' </c:if>>民进会员</option>
                                        <option value="民盟盟员" <c:if test="${socialInfo.politics=='民盟盟员'}"> selected='selected' </c:if>>民盟盟员</option>
                                        <option value="民革会员" <c:if test="${socialInfo.politics=='民革会员'}"> selected='selected' </c:if>>民革会员</option>
                                       
                                        <option value="九三学社社员" <c:if test="${socialInfo.politics=='九三学社社员'}"> selected='selected' </c:if>>九三学社社员</option>
                                        <option value="农工党党员" <c:if test="${socialInfo.politics=='农工党党员'}"> selected='selected' </c:if>>农工党党员</option>
                                        <option value="民建会员" <c:if test="${socialInfo.politics=='民建会员'}"> selected='selected' </c:if>>民建会员</option>
                                        
                                   </select>
                        
                        </li>
                        <li><span>学历</span><select name="degree">
                                        <option value="" <c:if test="${socialInfo.degree==''}"> selected='selected' </c:if>></option>
                                        <option value="高中以下" <c:if test="${socialInfo.degree=='高中以下'}"> selected='selected' </c:if>>高中以下</option>
                                        <option value="高中" <c:if test="${socialInfo.degree=='高中'}"> selected='selected' </c:if>>高中</option>
                                        <option value="中专" <c:if test="${socialInfo.degree=='中专'}"> selected='selected' </c:if>>中专</option>
                                        <option value="专科" <c:if test="${socialInfo.degree=='专科'}"> selected='selected' </c:if>>专科</option>
                                        <option value="大本" <c:if test="${socialInfo.degree=='大本'}"> selected='selected' </c:if>>大本</option>
                                        <option value="大学" <c:if test="${socialInfo.degree=='大学'}"> selected='selected' </c:if>>大学</option>
                                        <option value="双学位" <c:if test="${socialInfo.degree=='双学位'}"> selected='selected' </c:if>>双学位</option>
                                        <option value="研究生" <c:if test="${socialInfo.degree=='研究生'}"> selected='selected' </c:if>>研究生</option>
                                        <option value="博士生" <c:if test="${socialInfo.degree=='博士生'}"> selected='selected' </c:if>>博士生</option>
                                  </select>
                        </li>
                        <li>
							<span>职位</span>
							<input type="text"  class="input1"name="position"  value="${socialInfo.position}"/>
						</li>
                        <li>
							<span>工作时间</span>
							<input type="text" name="workingTime" class="input1" value="${socialInfo.workingTime}" id="input_work" autocomplete="off" />
							<span class="star">*</span>
							<input type="text" class="name_warning" />
						</li>

                    </ul>
                </div>
                <div class="content_right content">
                    <ul>
                        <li>
							<span>来校时间</span>
							<input type="text" name="startTime"  class="input1" value="${socialInfo.startTime}" id="input_school" autocomplete="off"/>
							<span class="star">*</span>
							<input type="text" class="name_warning" />
						</li>
                        <li>
							<span>从事专业方向</span>
							<select  id="position" name="profession" id="input_dir">
                                 <option value="无" <c:if test="${socialInfo.profession=='无'}"> selected='selected' </c:if>>无</option>
                                 <option value="辅导员" <c:if test="${socialInfo.profession=='辅导员'}"> selected='selected' </c:if>>辅导员</option>
                                 <option value="科研人员" <c:if test="${socialInfo.profession=='科研人员'}"> selected='selected' </c:if>>科研人员</option>
                                 <option value="教师" <c:if test="${socialInfo.profession=='教师'}"> selected='selected' </c:if>>教师</option>
                                 <option value="双肩挑" <c:if test="${socialInfo.profession=='双肩挑'}"> selected='selected' </c:if>>双肩挑</option>
                                 <option value="其他专业技术" <c:if test="${socialInfo.profession=='其他专业技术'}"> selected='selected' </c:if>>其他专业技术</option>
                             </select>
							 <span class="star">*</span>
							 <input type="text" class="name_warning" />
                        </li>
                        <li>
							<span>党政职务</span>
							<input type="text" name="partyPosition"  class="input1" value="${socialInfo.partyPosition}"/>
						</li>
                        <li>
							<span>所在部门</span>
                        	<select name="depName">
								<c:forEach items="${deps }" var="dep">
									<option value="${dep.depName }"
										<c:if test="${natureInfo.depName}== ${dep.depName}">selected='selected'</c:if>>${dep.depName }
									</option>
								</c:forEach>
							</select> 
						</li>
                        <li>
							<span>毕业学校</span><input type="text" name="schoolTag" value="${natureInfo.schoolTag}" class="input1"/>
						</li>
                        <li>
							<span>在岗情况</span>
						    <select id="job_situation" name="workStatus">
								<option value="在岗" <c:if test="${socialInfo.workStatus=='在岗'}"> selected='selected' </c:if>>在岗</option>
								<option value="休假" <c:if test="${socialInfo.workStatus=='休假'}"> selected='selected' </c:if>>休假</option>
								<option value="退休" <c:if test="${socialInfo.workStatus=='退休'}"> selected='selected'</c:if>>退休</option>
								<option value="离职" <c:if test="${socialInfo.workStatus=='离职'}"> selected='selected' </c:if>>离职</option>
							 </select>
                        </li>   
                    </ul>
                </div>
                <div class="save">
                    <div>
                        <span class="keep" style="border-radius:5px 0 0 5px;"><input type="submit" value="修改" /></span>
                        
                        <span class="abolish" style="border-radius:0 5px 5px 0; margin-left:-1px;"><input type="button" value="取消" onclick="history.go(-1);" /></span>
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
