package cn.xinguan.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import cn.xinguan.damain.Manager;
import cn.xinguan.server.LoginServer;

/**
 * 管理员登陆
 * 
 * @author MingJun Chen
 * 
 */
public class LoginServlet extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LoginServer loginServer = new LoginServer();

	/**
	 * 验证用户登录
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 将用户输入的用户名和密码封装到Manager实体类中
		Manager admin = CommonUtils.toBean(request.getParameterMap(),
				Manager.class);
		try {
			Manager manager = loginServer.verifyAdmin(admin); // 确认用户输入

			if (manager != null) {
				manager.setPassword(null); // 将保存的密码消掉
				request.getSession().setAttribute("admin", manager); // 将用户信息存放在Session中
				response.sendRedirect("index.jsp");
			} else {
				JOptionPane.showMessageDialog(null, "用户名或密码输入错误！");
				response.sendRedirect("login.jsp");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 在控制台上输出输入的用户名和密码
		System.out.println(admin);
		return null;
	}

	/**
	 * 修改用户密码
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String alterAdmin(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("alterAdmin");

		String password = request.getParameter("password"); // 原始密码
		String newPassword = request.getParameter("newPassword"); // 新密码
		String reNewPassword = request.getParameter("reNewPassword"); // 确认新密码

		System.out.println(password);
		Object username = request.getSession().getAttribute("admin");
		Manager admin = (Manager) username; // 取得当前要修改密码的用户名

		if (newPassword.equals(reNewPassword)) { // 判断新密码和确认密码是否一致
			int result = loginServer.alterAdmin(admin.getUsername(), password,
					newPassword);
			if (result == 1) {
				JOptionPane.showMessageDialog(null, "密码修改成功！");
				return "r:/index.jsp";
			} else {
				JOptionPane.showMessageDialog(null, "密码输入错误！");
				// return "alteradmin.jsp";
				return "r:/index.jsp";
			}
		} else {
			JOptionPane.showMessageDialog(null, "密码输入错误！");
			// return "alteradmin.jsp";
			return "r:/index.jsp";
		}
	}

	/**
	 * 注销用户
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String logoutAdmin(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		return "r:/login.jsp";
	}
}
