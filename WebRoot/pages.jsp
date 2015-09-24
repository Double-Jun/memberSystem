<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html PUBLIC "-//W3C//Dth XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/Dth/xhtml1-strict.dth">
<html xmlns="http://www.w3.org/1999/xhtml" lang="zh-CN">
<head>
<title>管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/page.js"></script>
<meta name="description" content="" />
<meta name="keywords" content="" />

<link rel="stylesheet" href="css/reset.css" />
<link rel="stylesheet" href="css/pages.css" />

<script type="text/javascript">
	function _go() {
		var pNum = document.getElementById("pageCode").value;
		
		if(!/^[1-9]\d*$/.test(pNum)) {//对当前页码进行整数校验
			alert('请输入正确的页码！');
			return;
		}
		if(pNum > ${pageBean.totalPage}) {//判断当前页码是否大于最大页
			alert('请输入正确的页码！');
			return;
		}
		location = "${pageBean.url}&pNum=" + pNum;
	}
</script>
</head>
<body>
	<div id="pages">
		<table>
			<tbody>
				<tr>
					<!-- 上一页和首页 -->
					<c:choose>
						<c:when test="${pageBean.pNum eq 1 }">
							<td class="characters">首页</td>
							<td class="characters">上一页</td>
						</c:when>
						<c:otherwise>
							<td class="characters"> <a href="${pageBean.url }&pNum=1" >首页</a></td>
							<td class="characters"> <a href="${pageBean.url }&pNum=${pageBean.pNum-1}"
								>上一页</a></td>
						</c:otherwise>
					</c:choose>

					<!-- 设置中间起始页和结束页 -->
					<c:choose>
						<c:when test="${pageBean.totalPage <= 7 }">
							<c:set var="begin" value="1" />
							<c:set var="end" value="${pageBean.totalPage }" />
						</c:when>
						<c:otherwise>
							<c:set var="begin" value="${pageBean.pNum-3 }" />
							<c:set var="end" value="${pageBean.pNum + 3}" />
							<c:if test="${begin < 1 }">
								<c:set var="begin" value="1" />
								<c:set var="end" value="7" />
							</c:if>
							<c:if test="${end > pageBean.totalPage }">
								<c:set var="begin" value="${pageBean.totalPage-6 }" />
								<c:set var="end" value="${pageBean.totalPage }" />
							</c:if>
						</c:otherwise>
					</c:choose>

					<!-- 中间显示的页码数 -->
					<c:forEach begin="${begin }" end="${end }" var="i">
						<c:choose>
							<c:when test="${i eq pageBean.pNum }">
								<td class="number"  id="td_index"><span >${i }</span></td>
							</c:when>
							<c:otherwise>
								<td class="number"><a href="${pageBean.url }&pNum=${i}" >${i }</a></td>
							</c:otherwise>
						</c:choose>
					</c:forEach>

					<!-- 下一页和尾页 -->
					<c:choose>
						<c:when test="${pageBean.pNum eq pageBean.totalPage }">
							<td class="characters"><span>下一页</span></td>
							<td class="characters"><span>尾页</span></td>
						</c:when>
						<c:otherwise>
							<td class="characters"><a href="${pageBean.url }&pNum=${pageBean.pNum+1}"
								>下一页</a></td>
							<td class="characters"><a href="${pageBean.url }&pNum=${pageBean.totalPage}"
								>尾页</a></td>
						</c:otherwise>
					</c:choose>

					<!-- 显示总记录数，当前页和总页数 -->
					<td class="other">共<span>${pageBean.totalRecord }</span>条记录
					</td>
					<td class="characters"><span>${pageBean.pNum
							}/${pageBean.totalPage }</span>页</td>
					<td class="characters"><span>到</span> <input type="text"
						style="width:30px;" class="inputPageCode" id="pageCode"
						value="${pageBean.pNum }" /> <span>页</span></td>
					<td class="characters"><a href="javascript:_go();"
						class="aSubmit">确定</a></td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>