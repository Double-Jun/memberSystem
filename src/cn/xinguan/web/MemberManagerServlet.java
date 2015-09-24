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
 * 会员管理模块 --- WEB层
 * 
 * @author MingJun Chen
 * 
 */
public class MemberManagerServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private MemberManagerServer memberManagerServer = new MemberManagerServer(); // 会员管理业务类
	private FellowShipServer fss = new FellowShipServer(); // 单身联谊业务类

	/**
	 * 通过姓名查询
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
		String name = request.getParameter("name"); // 得到查询条件 姓名
		int pNum = PageUtils.getPNum(request);

		PageBean<NatureInfo> pageBean = memberManagerServer.findByName(name,
				pNum);

		url = url + "&name=" + URLEncoder.encode(name, "utf-8");
		pageBean.setUrl(url);
		request.setAttribute("pageBean", pageBean);

		return "f:cont_2.jsp";
	}

	/**
	 * 显示所有会员的自然信息
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
		return "f:cont_2.jsp";
	}

	/**
	 * 添加会员
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public void init(ServletConfig config) {
		// 在web.xml中设置的一个初始化参数
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
						request.setAttribute("upload.message", "上传文件成功！");
						System.out.println("文件上传成功");
					} else {
						request.setAttribute("upload.message", "没有选择上传文件！");
						System.out.println("没有选择上传文件");
					}

				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("upload.message", "上传文件失败！");
			System.out.println("文件上传失败：");
		}

		NatureInfo nature = CommonUtils.toBean(map, NatureInfo.class);
		SocialInfo social = CommonUtils.toBean(map, SocialInfo.class);

		int result = memberManagerServer.addMember(nature, social);

		System.out.println(nature);
		if (result == 1) {
			JOptionPane.showMessageDialog(null, "用户添加成功！");
		} else {
			JOptionPane.showMessageDialog(null, "用户添加失败！");
		}
		return "r:/addmember.jsp";

	}

	/**
	 * 批量删除会员
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
	 * 根据id查询会员的详细信息
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
		// 得到要查询会员的id
		String id = request.getParameter("id");

		// 调用业务层的findById方法
		List<Object> list = memberManagerServer.findById(id);

		// 将查询结果设置
		request.setAttribute("natureInfo", (NatureInfo) list.get(0));
		request.setAttribute("socialInfo", (SocialInfo) list.get(1));

		return "f:detailed.jsp";
	}

	/**
	 * 根据id修改会员信息
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
	 * 提交会员修改信息
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

		// 得到要查询会员的id
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
	 * 根据id删除单个会员记录
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
