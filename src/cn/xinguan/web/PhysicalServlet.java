package cn.xinguan.web;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import jxl.read.biff.BiffException;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.itcast.jdbc.TxQueryRunner;
import cn.itcast.servlet.BaseServlet;
import cn.xinguan.damain.NatureInfo;
import cn.xinguan.damain.PageBean;
import cn.xinguan.server.PhysicalServer;
import cn.xinguan.utils.PageUtils;
import cn.xinguan.utils.ReadExcelUtils;

/**
 * ��Ա���Web��
 * 
 * @author MingJun Chen
 * 
 */
public class PhysicalServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;
	private PhysicalServer ps = new PhysicalServer(); // ����һ��Server�����

	private ServletContext sc;
	private String savePath;

	public void init(ServletConfig config) {
		// ��web.xml�����õ�һ����ʼ������
		savePath = config.getInitParameter("savePath");
		sc = config.getServletContext();
	}

	/**
	 * ��ȡExcel
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String readExcel(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		DiskFileItemFactory factory = new DiskFileItemFactory();

		ServletFileUpload upload = new ServletFileUpload(factory);
		File file = null;
		try {
			List<?> items = upload.parseRequest(request);
			Iterator<?> itr = items.iterator();
			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				if (item.isFormField()) {
					System.out.println("��������:" + item.getFieldName()
							+ "��������ֵ:" + item.getString("UTF-8"));
				} else {
					if (item.getName() != null && !item.getName().equals("")) {
						System.out.println("�ϴ��ļ��Ĵ�С:" + item.getSize());
						System.out.println("�ϴ��ļ�������:" + item.getContentType());
						// item.getName()�����ϴ��ļ��ڿͻ��˵�����·������
						System.out.println("�ϴ��ļ�������:" + item.getName());

						File tempFile = new File(item.getName());
						System.out.println("tempFile:" + tempFile);
						file = new File(sc.getRealPath("/") + savePath,
								tempFile.getName());
						item.write(file);
						System.out.println("�ϴ��ļ��ɹ���");
					} else {
						request.setAttribute("upload.message", "û��ѡ���ϴ��ļ���");
					}
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("upload.message", "�ϴ��ļ�ʧ�ܣ�");
		}

		String sql = "update tb_natureinfo set status=? where IDcard=?";
		TxQueryRunner qr = new TxQueryRunner();
		try {

			String sql2 = "update tb_natureinfo set status=''";
			qr.update(sql2);

			ReadExcelUtils.readExcel(file.toString(), sql);
			JOptionPane.showMessageDialog(null, "���ݸ�����ɣ�");
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
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

		PageBean<NatureInfo> pbNatureInfo = ps.conditionsQuery(params, pNum);

		// 4.����url������ǳ���Ҫһ��Ҫ��
		if (pNum == 1 && request.getParameter("tag") == null) {
			for (String s : set) { // ѭ����Map�е�keyֵ��valueֵ����params��
				if (!s.equals("method")) {
					url = url + "&" + s + "="
							+ URLEncoder.encode(conditions.get(s)[0], "utf-8");
				}
			}
			url = url + "&tag=old";
		}

		System.out.println(url);

		pbNatureInfo.setUrl(url); // ��ƴ�Ӻõ�url��ŵ�pageBean��
		// 5.��pageBean���õ�request��
		request.setAttribute("pageBean", pbNatureInfo);
		// 6.ת���ػ�Ա���ҳ��
		return "f:/cont_4.jsp";
	}
}
