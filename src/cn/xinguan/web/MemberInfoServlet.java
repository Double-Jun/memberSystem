package cn.xinguan.web;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.servlet.BaseServlet;
import cn.xinguan.damain.NatureInfo;
import cn.xinguan.damain.PageBean;
import cn.xinguan.server.FellowShipServer;
import cn.xinguan.server.MemberInfoServer;
import cn.xinguan.utils.PageUtils;

/**
 * 会员信息模块 --- WEB层
 * 
 * @author MingJun Chen
 * 
 */
public class MemberInfoServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;

	private FellowShipServer fss = new FellowShipServer();
	private MemberInfoServer mis = new MemberInfoServer();

	/**
	 * 显示所有会员信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */
	public String showAllMemberInfo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {

		// 1.得到pNum
		int pNum = PageUtils.getPNum(request);
		// 2.得到链接URL
		String url = PageUtils.getUrl(request);
		// 3.通过调用方法得到PageBean
		PageBean<NatureInfo> pbNatureInfo = fss.seachAllMember(pNum);
		// 4.设置url，这个非常重要
		pbNatureInfo.setUrl(url);
		// 5.将pageBean设置到request中
		request.setAttribute("pageBean", pbNatureInfo);
		// 6.转发
		return "f:cont_1.jsp";
	}

	/**
	 * 按单一条件查询会员信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */
	public String findMember(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		// 1.得到pNum
		int pNum = PageUtils.getPNum(request);
		// 2.得到链接URL
		String url = PageUtils.getUrl(request);
		// 3.通过调用方法得到PageBean
		request.setCharacterEncoding("utf-8");

		String condition = request.getParameter("condition"); // 得到查询类型
		String value = request.getParameter("value"); // 查询的值
		if (condition == null) {
			value = request.getParameter("name");
			condition = "name";
		}
		if (value == null) {
			value = request.getParameter("depName");
			condition = "depName";
		}
		if (value == null) {
			value = request.getParameter("IDcard");
			condition = "IDcard";
		}

		PageBean<NatureInfo> pbNatureInfo = mis.findMember(condition, value,
				pNum);

		// 4.设置url，这个非常重要
		if (pNum == 1) {
			url = url + "&" + condition + "="
					+ URLEncoder.encode(value, "utf-8");
		}
		pbNatureInfo.setUrl(url);

		// 5.将pageBean设置到request中
		request.setAttribute("pageBean", pbNatureInfo);

		// 6.转发
		return "f:/cont_1.jsp";
	}
}
