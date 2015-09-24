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
 * 会员体检Web层
 * 
 * @author MingJun Chen
 * 
 */
public class PhysicalServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;
	private PhysicalServer ps = new PhysicalServer(); // 创建一个Server层对象

	private ServletContext sc;
	private String savePath;

	public void init(ServletConfig config) {
		// 在web.xml中设置的一个初始化参数
		savePath = config.getInitParameter("savePath");
		sc = config.getServletContext();
	}

	/**
	 * 读取Excel
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
					System.out.println("表单参数名:" + item.getFieldName()
							+ "，表单参数值:" + item.getString("UTF-8"));
				} else {
					if (item.getName() != null && !item.getName().equals("")) {
						System.out.println("上传文件的大小:" + item.getSize());
						System.out.println("上传文件的类型:" + item.getContentType());
						// item.getName()返回上传文件在客户端的完整路径名称
						System.out.println("上传文件的名称:" + item.getName());

						File tempFile = new File(item.getName());
						System.out.println("tempFile:" + tempFile);
						file = new File(sc.getRealPath("/") + savePath,
								tempFile.getName());
						item.write(file);
						System.out.println("上传文件成功！");
					} else {
						request.setAttribute("upload.message", "没有选择上传文件！");
					}
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("upload.message", "上传文件失败！");
		}

		String sql = "update tb_natureinfo set status=? where IDcard=?";
		TxQueryRunner qr = new TxQueryRunner();
		try {

			String sql2 = "update tb_natureinfo set status=''";
			qr.update(sql2);

			ReadExcelUtils.readExcel(file.toString(), sql);
			JOptionPane.showMessageDialog(null, "数据更新完成！");
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
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

		PageBean<NatureInfo> pbNatureInfo = ps.conditionsQuery(params, pNum);

		// 4.设置url，这个非常重要一定要有
		if (pNum == 1 && request.getParameter("tag") == null) {
			for (String s : set) { // 循环将Map中的key值和value值放入params中
				if (!s.equals("method")) {
					url = url + "&" + s + "="
							+ URLEncoder.encode(conditions.get(s)[0], "utf-8");
				}
			}
			url = url + "&tag=old";
		}

		System.out.println(url);

		pbNatureInfo.setUrl(url); // 将拼接好的url存放到pageBean中
		// 5.将pageBean设置到request中
		request.setAttribute("pageBean", pbNatureInfo);
		// 6.转发回会员体检页面
		return "f:/cont_4.jsp";
	}
}
