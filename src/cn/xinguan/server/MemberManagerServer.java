package cn.xinguan.server;

import java.sql.SQLException;
import java.util.List;

import cn.xinguan.Dao.MemberManagerDao;
import cn.xinguan.damain.NatureInfo;
import cn.xinguan.damain.PageBean;
import cn.xinguan.damain.SocialInfo;

/**
 * ��Ա���� --- ҵ���
 * 
 * @author MingJun Chen
 * 
 */
public class MemberManagerServer {
	private MemberManagerDao memberManagerDao = new MemberManagerDao();

	/**
	 * ��ӻ�Ա��¼
	 * 
	 * @param nature
	 * @param social
	 * @return
	 */
	public int addMember(NatureInfo nature, SocialInfo social) {
		return memberManagerDao.insertMember(nature, social);
	}

	/**
	 * ����ɾ����Ա��¼
	 * 
	 * @param ids
	 */
	public void delBatchMember(String[] ids) {
		memberManagerDao.delBatchMember(ids);
	}

	/**
	 * ͨ��id��ѯ�û�����
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public List<Object> findById(String id) throws SQLException {
		return memberManagerDao.findById(id);
	}

	/**
	 * ����idɾ��������Ա��¼
	 * 
	 * @param id
	 * @throws SQLException
	 */
	public void deleteMember(String id) throws SQLException {
		memberManagerDao.deleteMember(id);
	}

	/**
	 * �ύ�޸���Ϣ
	 * 
	 * @param natureInfo
	 * @param socialInfo
	 */
	public void alterMemberInfo(NatureInfo natureInfo, SocialInfo socialInfo) {
		memberManagerDao.alterMemberInfo(natureInfo, socialInfo);
	}

	/**
	 * 
	 * @param name
	 * @param pNum
	 * @return
	 * @throws SQLException
	 */
	public PageBean<NatureInfo> findByName(String name, int pNum)
			throws SQLException {
		return memberManagerDao.findByName(name, pNum);
	}

}
