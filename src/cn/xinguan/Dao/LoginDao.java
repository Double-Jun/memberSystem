package cn.xinguan.Dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.itcast.jdbc.TxQueryRunner;
import cn.xinguan.damain.Manager;

/**
 * 管理员登陆验证 --- 持久层
 * 
 * @author MingJun Chen
 * 
 */
public class LoginDao {

	private TxQueryRunner qr = new TxQueryRunner();

	/**
	 * Dao层--->确认用户名和密码是否正确
	 * 
	 * @param admin
	 * @return
	 * @throws SQLException
	 */
	public Manager verifyAdmin(Manager admin) throws SQLException {
		String sql = "select * from tb_manager where username=? and password=?";

		Manager admin1 = qr.query(sql, new BeanHandler<Manager>(Manager.class),
				admin.getUsername(), admin.getPassword());
		return admin1;
	}

	/**
	 * Dao-->修改用户密码
	 * 
	 * @param username
	 * @param password
	 * @param newPassword
	 * @return
	 */
	public int alterAdmin(String username, String password, String newPassword) {
		String sql = "update tb_manager set password=? where username=? and password=? ";
		Object[] params = { newPassword, username, password };

		int result = 0;
		try {
			result = qr.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
