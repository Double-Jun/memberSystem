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
 * ��Ա��Ϣģ�� --- WEB��
 * 
 * @author MingJun Chen
 * 
 */
public class MemberInfoServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;

	private FellowShipServer fss = new FellowShipServer();
	private MemberInfoServer mis = new MemberInfoServer();

	/**
	 * ��ʾ���л�Ա��Ϣ
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

		// 1.�õ�pNum
		int pNum = PageUtils.getPNum(request);
		// 2.�õ�����URL
		String url = PageUtils.getUrl(request);
		// 3.ͨ�����÷����õ�PageBean
		PageBean<NatureInfo> pbNatureInfo = fss.seachAllMember(pNum);
		// 4.����url������ǳ���Ҫ
		pbNatureInfo.setUrl(url);
		// 5.��pageBean���õ�request��
		request.setAttribute("pageBean", pbNatureInfo);
		// 6.ת��
		return "f:cont_1.jsp";
	}

	/**
	 * ����һ������ѯ��Ա��Ϣ
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
		// 1.�õ�pNum
		int pNum = PageUtils.getPNum(request);
		// 2.�õ�����URL
		String url = PageUtils.getUrl(request);
		// 3.ͨ�����÷����õ�PageBean
		request.setCharacterEncoding("utf-8");

		String condition = request.getParameter("condition"); // �õ���ѯ����
		String value = request.getParameter("value"); // ��ѯ��ֵ
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

		// 4.����url������ǳ���Ҫ
		if (pNum == 1) {
			url = url + "&" + condition + "="
					+ URLEncoder.encode(value, "utf-8");
		}
		pbNatureInfo.setUrl(url);

		// 5.��pageBean���õ�request��
		request.setAttribute("pageBean", pbNatureInfo);

		// 6.ת��
		return "f:/cont_1.jsp";
	}
}
