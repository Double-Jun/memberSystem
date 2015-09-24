package cn.xinguan.web;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import cn.xinguan.damain.NatureInfo;
import cn.xinguan.damain.PageBean;
import cn.xinguan.damain.SocialInfo;
import cn.xinguan.server.FellowShipServer;
import cn.xinguan.server.MemberManagerServer;
import cn.xinguan.utils.PageUtils;

/**
 * ��Ա����ģ�� --- WEB��
 * 
 * @author MingJun Chen
 * 
 */
public class MemberManagerServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private MemberManagerServer memberManagerServer = new MemberManagerServer(); // ��Ա����ҵ����
	private FellowShipServer fss = new FellowShipServer(); // ��������ҵ����

	/**
	 * ͨ��������ѯ
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */
	public String findByName(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {

		String url = PageUtils.getUrl(request);
		String name = request.getParameter("name"); // �õ���ѯ���� ����
		int pNum = PageUtils.getPNum(request);

		PageBean<NatureInfo> pageBean = memberManagerServer.findByName(name,
				pNum);

		url = url + "&name=" + URLEncoder.encode(name, "utf-8");
		pageBean.setUrl(url);
		request.setAttribute("pageBean", pageBean);

		return "f:cont_2.jsp";
	}

	/**
	 * ��ʾ���л�Ա����Ȼ��Ϣ
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */
	public String listAllMember(HttpServletRequest request,
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
		return "f:cont_2.jsp";
	}

	/**
	 * ��ӻ�Ա
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public void init(ServletConfig config) {
		// ��web.xml�����õ�һ����ʼ������
		savePath = config.getInitParameter("savePath");
		sc = config.getServletContext();
	}

	private ServletContext sc;
	private String savePath;

	public String addMember(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> map = new HashMap<String, String>();
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List<?> items = upload.parseRequest(request);
			Iterator<?> itr = items.iterator();
			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				if (item.isFormField()) {
					map.put(item.getFieldName(), item.getString("UTF-8"));
				} else {
					if (item.getName() != null && !item.getName().equals("")) {
						File tempFile = new File(item.getName());
						File file = new File(sc.getRealPath("/") + savePath,
								tempFile.getName());
						String imgUrl = savePath + "/" + tempFile.getName();
						item.write(file);
						map.put("photo", imgUrl);
						request.setAttribute("upload.message", "�ϴ��ļ��ɹ���");
						System.out.println("�ļ��ϴ��ɹ�");
					} else {
						request.setAttribute("upload.message", "û��ѡ���ϴ��ļ���");
						System.out.println("û��ѡ���ϴ��ļ�");
					}

				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("upload.message", "�ϴ��ļ�ʧ�ܣ�");
			System.out.println("�ļ��ϴ�ʧ�ܣ�");
		}

		NatureInfo nature = CommonUtils.toBean(map, NatureInfo.class);
		SocialInfo social = CommonUtils.toBean(map, SocialInfo.class);

		int result = memberManagerServer.addMember(nature, social);

		System.out.println(nature);
		if (result == 1) {
			JOptionPane.showMessageDialog(null, "�û���ӳɹ���");
		} else {
			JOptionPane.showMessageDialog(null, "�û����ʧ�ܣ�");
		}
		return "r:/addmember.jsp";

	}

	/**
	 * ����ɾ����Ա
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String delBatchMember(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String[] ids = request.getParameterValues("ids");

		if (ids != null) {
			memberManagerServer.delBatchMember(ids);
		}

		response.sendRedirect("MemberManagerServlet?method=listAllMember");
		return null;
	}

	/**
	 * ����id��ѯ��Ա����ϸ��Ϣ
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */
	public String detailsInfo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		// �õ�Ҫ��ѯ��Ա��id
		String id = request.getParameter("id");

		// ����ҵ����findById����
		List<Object> list = memberManagerServer.findById(id);

		// ����ѯ�������
		request.setAttribute("natureInfo", (NatureInfo) list.get(0));
		request.setAttribute("socialInfo", (SocialInfo) list.get(1));

		return "f:detailed.jsp";
	}

	/**
	 * ����id�޸Ļ�Ա��Ϣ
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */
	public String showaltermember(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {

		String id = request.getParameter("id");

		List<Object> list = memberManagerServer.findById(id);

		NatureInfo natureInfo = (NatureInfo) list.get(0);
		SocialInfo socialInfo = (SocialInfo) list.get(1);

		request.setAttribute("natureInfo", natureInfo);
		request.setAttribute("socialInfo", socialInfo);

		return "f:modify.jsp";
	}

	/**
	 * �ύ��Ա�޸���Ϣ
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */
	public String alterMemberInfo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {

		// �õ�Ҫ��ѯ��Ա��id
		String id = request.getParameter("id");

		Map<String, String[]> map = request.getParameterMap();

		NatureInfo natureInfo = CommonUtils.toBean(map, NatureInfo.class);
		SocialInfo socialInfo = CommonUtils.toBean(map, SocialInfo.class);

		memberManagerServer.alterMemberInfo(natureInfo, socialInfo);

		response.sendRedirect("MemberManagerServlet?method=detailsInfo&id="
				+ id);

		return null;
	}

	/**
	 * ����idɾ��������Ա��¼
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */
	public String deleteMember(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {

		String id = request.getParameter("id");

		memberManagerServer.deleteMember(id);

		response.sendRedirect("MemberManagerServlet?method=listAllMember");
		return null;
	}

}
