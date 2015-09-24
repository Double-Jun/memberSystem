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
 * ����Ա��½
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
	 * ��֤�û���¼
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ���û�������û����������װ��Managerʵ������
		Manager admin = CommonUtils.toBean(request.getParameterMap(),
				Manager.class);
		try {
			Manager manager = loginServer.verifyAdmin(admin); // ȷ���û�����

			if (manager != null) {
				manager.setPassword(null); // ���������������
				request.getSession().setAttribute("admin", manager); // ���û���Ϣ�����Session��
				response.sendRedirect("index.jsp");
			} else {
				JOptionPane.showMessageDialog(null, "�û����������������");
				response.sendRedirect("login.jsp");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// �ڿ���̨�����������û���������
		System.out.println(admin);
		return null;
	}

	/**
	 * �޸��û�����
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

		String password = request.getParameter("password"); // ԭʼ����
		String newPassword = request.getParameter("newPassword"); // ������
		String reNewPassword = request.getParameter("reNewPassword"); // ȷ��������

		System.out.println(password);
		Object username = request.getSession().getAttribute("admin");
		Manager admin = (Manager) username; // ȡ�õ�ǰҪ�޸�������û���

		if (newPassword.equals(reNewPassword)) { // �ж��������ȷ�������Ƿ�һ��
			int result = loginServer.alterAdmin(admin.getUsername(), password,
					newPassword);
			if (result == 1) {
				JOptionPane.showMessageDialog(null, "�����޸ĳɹ���");
				return "r:/index.jsp";
			} else {
				JOptionPane.showMessageDialog(null, "�����������");
				// return "alteradmin.jsp";
				return "r:/index.jsp";
			}
		} else {
			JOptionPane.showMessageDialog(null, "�����������");
			// return "alteradmin.jsp";
			return "r:/index.jsp";
		}
	}

	/**
	 * ע���û�
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
