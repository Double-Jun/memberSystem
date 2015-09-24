package cn.xinguan.server;

import java.sql.SQLException;
import java.util.List;

import cn.xinguan.Dao.DepartmentDao;
import cn.xinguan.damain.Department;

public class DepartmentServer {

	private DepartmentDao departmentDao = new DepartmentDao();

	public List<Department> seachAllDepartment() throws SQLException {
		return departmentDao.seachAllDepartment();
	}

}
