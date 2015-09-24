<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
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
<link rel="stylesheet" type="text/css" href="pager.css" >
</head>
<div class="divBody">
	<div class="divContent">
		<%--上一页 --%>
		<c:choose>
			<c:when test="${pageBean.pNum eq 1 }">
				<span class="spanBtnDisabled">首页</span>
				<span class="spanBtnDisabled">上一页</span>
			</c:when>
			<c:otherwise>
				<a href="${pageBean.url }&pNum=1" class="aBtn bold">首页</a>
				<a href="${pageBean.url }&pNum=${pageBean.pNum-1}" class="aBtn bold">上一页</a>
			</c:otherwise>
		</c:choose>



		<%--我们需要计算页码列表的开始和结束位置，即两个变量begin和end
计算它们需要通过当前页码！
1. 总页数不足6页--> begin=1, end=最大页
2. 通过公式设置begin和end，begin=当前页-1，end=当前页+3
3. 如果begin<1，那么让begin=1，end=6
4. 如果end>tp, 让begin=tp-5, end=tp
 --%>
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

		<c:forEach begin="${begin }" end="${end }" var="i">
			<c:choose>
				<c:when test="${i eq pageBean.pNum }">
					<span class="spanBtnSelect">${i }</span>
				</c:when>
				<c:otherwise>
					<a href="${pageBean.url }&pNum=${i}" class="aBtn">[${i }]</a>
				</c:otherwise>
			</c:choose>


		</c:forEach>
		<%-- 计算begin和end --%>
		<%-- 如果总页数<=6，那么显示所有页码，即begin=1 end=${pb.tp} --%>
		<%-- 设置begin=当前页码-2，end=当前页码+3 --%>
		<%-- 如果begin<1，那么让begin=1 end=6 --%>
		<%-- 如果end>最大页，那么begin=最大页-5 end=最大页 --%>



		<%-- 显示点点点 --%>
		<c:if test="${end < pageBean.totalPage }">
			<span class="spanApostrophe">...</span>
		</c:if>


		<%--下一页 --%>
		<c:choose>
			<c:when test="${pageBean.pNum eq pageBean.totalPage }">
				<span class="spanBtnDisabled">下一页</span>
				<span class="spanBtnDisabled">尾页</span>
			</c:when>
			<c:otherwise>
				<a href="${pageBean.url }&pNum=${pageBean.pNum+1}" class="aBtn bold">下一页</a>
				<a href="${pageBean.url }&pNum=${pageBean.totalPage}" class="aBtn bold">尾页</a>
			</c:otherwise>
		</c:choose>


		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

		<%-- 共N页 到M页 --%>
		<span>第${pageBean.pNum }页/共${pageBean.totalPage }页</span> <span>到</span> <input type="text"
			style="width:30px;" class="inputPageCode" id="pageCode" value="${pageBean.pNum }" /> <span>页</span>
		<a href="javascript:_go();" class="aSubmit">确定</a>
	</div>
</div>