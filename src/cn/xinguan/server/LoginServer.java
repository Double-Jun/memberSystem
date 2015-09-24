package cn.xinguan.server;

import java.sql.SQLException;

import cn.xinguan.Dao.LoginDao;
import cn.xinguan.damain.Manager;

/**
 * 管理员登陆验证 --- 业务层
 * 
 * @author MingJun Chen
 * 
 */
public class LoginServer {

	private LoginDao loginDao = new LoginDao();

	/**
	 * 验证用户登陆
	 * 
	 * @param admin
	 * @return
	 * @throws SQLException
	 */
	public Manager verifyAdmin(Manager admin) throws SQLException {

		return loginDao.verifyAdmin(admin);
	}

	/**
	 * 修改用户密码
	 * 
	 * @param username
	 * @param password
	 * @param newPassword
	 * @return
	 */
	public int alterAdmin(String username, String password, String newPassword) {
		return loginDao.alterAdmin(username, password, newPassword);
	}

}
