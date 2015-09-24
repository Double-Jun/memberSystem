package cn.xinguan.server;

import java.sql.SQLException;
import java.util.List;

import cn.xinguan.Dao.MemberManagerDao;
import cn.xinguan.damain.NatureInfo;
import cn.xinguan.damain.PageBean;
import cn.xinguan.damain.SocialInfo;

/**
 * 会员管理 --- 业务层
 * 
 * @author MingJun Chen
 * 
 */
public class MemberManagerServer {
	private MemberManagerDao memberManagerDao = new MemberManagerDao();

	/**
	 * 添加会员记录
	 * 
	 * @param nature
	 * @param social
	 * @return
	 */
	public int addMember(NatureInfo nature, SocialInfo social) {
		return memberManagerDao.insertMember(nature, social);
	}

	/**
	 * 批量删除会员记录
	 * 
	 * @param ids
	 */
	public void delBatchMember(String[] ids) {
		memberManagerDao.delBatchMember(ids);
	}

	/**
	 * 通过id查询用户详情
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public List<Object> findById(String id) throws SQLException {
		return memberManagerDao.findById(id);
	}

	/**
	 * 根据id删除单个会员记录
	 * 
	 * @param id
	 * @throws SQLException
	 */
	public void deleteMember(String id) throws SQLException {
		memberManagerDao.deleteMember(id);
	}

	/**
	 * 提交修改信息
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
