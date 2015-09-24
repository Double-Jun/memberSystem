package cn.xinguan.server;

import java.sql.SQLException;

import cn.xinguan.Dao.LoginDao;
import cn.xinguan.damain.Manager;

/**
 * ����Ա��½��֤ --- ҵ���
 * 
 * @author MingJun Chen
 * 
 */
public class LoginServer {

	private LoginDao loginDao = new LoginDao();

	/**
	 * ��֤�û���½
	 * 
	 * @param admin
	 * @return
	 * @throws SQLException
	 */
	public Manager verifyAdmin(Manager admin) throws SQLException {

		return loginDao.verifyAdmin(admin);
	}

	/**
	 * �޸��û�����
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
