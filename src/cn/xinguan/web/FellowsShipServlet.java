package cn.xinguan.web;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.servlet.BaseServlet;
import cn.xinguan.damain.NatureInfo;
import cn.xinguan.damain.PageBean;
import cn.xinguan.server.FellowShipServer;
import cn.xinguan.utils.PageUtils;

/**
 * 单身联谊--> web层
 * 
 * @author MingJun Chen
 * 
 */
public class FellowsShipServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;
	private FellowShipServer fss = new FellowShipServer();

	/**
	 * 查询所有会员自然信息
	 * 
	 * @param request
	 * @param response
	 * @return String
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */
	public String showAllMember(HttpServletRequest request,
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
		return "f:/cont_3.jsp";
	}

	/**
	 * 多条件组合查询
	 * 
	 * @param request
	 * @param response
	 * @return String
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */
	public String conditionQuery(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {

		// 1.得到pNum
		int pNum = PageUtils.getPNum(request);
		// 2.得到链接URL
		String url = PageUtils.getUrl(request);

		// System.out.println("要查询的url" + url);
		// 3.通过调用方法得到PageBean
		Map<String, String[]> conditions = request.getParameterMap(); // 得到所有查询条件
		Set<String> set = conditions.keySet(); // 存放Map的Key值
		Map<String, String> params = new HashMap<String, String>(); // 用于存放所有查询条件
		for (String s : set) { // 循环将Map中的key值和value值放入params中
			params.put(s, conditions.get(s)[0]);
		}

		PageBean<NatureInfo> pbNatureInfo = fss.conditionsQuery(params, pNum);

		// 4.设置url，这个非常重要
		if (pNum == 1 && !url.contains("conditionGender")) {
			for (String s : set) { // 循环将Map中的key值和value值放入params中
				url = url + "&" + s + "="
						+ URLEncoder.encode(conditions.get(s)[0], "utf-8");
			}
		}
		// System.out.println("拼接好的url:" + url);
		pbNatureInfo.setUrl(url);
		// 5.将pageBean设置到request中
		request.setAttribute("pageBean", pbNatureInfo);
		// 6.转发
		return "f:/cont_3.jsp";
	}
}
