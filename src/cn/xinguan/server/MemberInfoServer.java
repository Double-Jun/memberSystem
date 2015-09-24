package cn.xinguan.server;

import java.sql.SQLException;

import cn.xinguan.Dao.MemberInfoDao;
import cn.xinguan.damain.NatureInfo;
import cn.xinguan.damain.PageBean;

/**
 * ��Ա��Ϣ --- ҵ���
 * 
 * @author MingJun Chen
 * 
 */
public class MemberInfoServer {

	private MemberInfoDao mid = new MemberInfoDao();

	/**
	 * ����һ������ѯ��Ա��Ϣ
	 * 
	 * @param condition
	 * @param value
	 * @param pNum
	 * @return
	 * @throws SQLException
	 */
	public PageBean<NatureInfo> findMember(String condition, String value,
			int pNum) throws SQLException {
		return mid.findMember(condition, value, pNum);
	}

}
