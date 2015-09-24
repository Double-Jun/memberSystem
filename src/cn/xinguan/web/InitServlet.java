package cn.xinguan.web;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import cn.xinguan.damain.Department;
import cn.xinguan.server.DepartmentServer;

/**
 * 部门信息封装 --- WEB层
 * 
 * @author MingJun Chen
 * 
 */
public class InitServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		DepartmentServer departmentServer = new DepartmentServer();
		try {

			List<Department> departments = departmentServer
					.seachAllDepartment();

			ServletContext application = config.getServletContext();

			application.setAttribute("deps", departments);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		super.init();
	}

}
