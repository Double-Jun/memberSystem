package cn.xinguan.Dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.jdbc.TxQueryRunner;
import cn.xinguan.damain.Department;

public class DepartmentDao {

	private TxQueryRunner qr = new TxQueryRunner();

	public List<Department> seachAllDepartment() throws SQLException {

		String sql = "select * from tb_dep";

		return qr.query(sql, new BeanListHandler<Department>(Department.class));
	}

}
