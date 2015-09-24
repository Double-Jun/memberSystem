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
 * ��������--> web��
 * 
 * @author MingJun Chen
 * 
 */
public class FellowsShipServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;
	private FellowShipServer fss = new FellowShipServer();

	/**
	 * ��ѯ���л�Ա��Ȼ��Ϣ
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
		return "f:/cont_3.jsp";
	}

	/**
	 * ��������ϲ�ѯ
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

		// 1.�õ�pNum
		int pNum = PageUtils.getPNum(request);
		// 2.�õ�����URL
		String url = PageUtils.getUrl(request);

		// System.out.println("Ҫ��ѯ��url" + url);
		// 3.ͨ�����÷����õ�PageBean
		Map<String, String[]> conditions = request.getParameterMap(); // �õ����в�ѯ����
		Set<String> set = conditions.keySet(); // ���Map��Keyֵ
		Map<String, String> params = new HashMap<String, String>(); // ���ڴ�����в�ѯ����
		for (String s : set) { // ѭ����Map�е�keyֵ��valueֵ����params��
			params.put(s, conditions.get(s)[0]);
		}

		PageBean<NatureInfo> pbNatureInfo = fss.conditionsQuery(params, pNum);

		// 4.����url������ǳ���Ҫ
		if (pNum == 1 && !url.contains("conditionGender")) {
			for (String s : set) { // ѭ����Map�е�keyֵ��valueֵ����params��
				url = url + "&" + s + "="
						+ URLEncoder.encode(conditions.get(s)[0], "utf-8");
			}
		}
		// System.out.println("ƴ�Ӻõ�url:" + url);
		pbNatureInfo.setUrl(url);
		// 5.��pageBean���õ�request��
		request.setAttribute("pageBean", pbNatureInfo);
		// 6.ת��
		return "f:/cont_3.jsp";
	}
}
