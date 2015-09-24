package cn.xinguan.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.JdbcUtils;
import cn.itcast.jdbc.TxQueryRunner;
import cn.xinguan.damain.NatureInfo;
import cn.xinguan.damain.PageBean;
import cn.xinguan.damain.SocialInfo;
import cn.xinguan.utils.MemberUtils;

/**
 * ��Ա���� --- �־ò�
 * 
 * @author MingJun Chen
 * 
 */
public class MemberManagerDao {
	private TxQueryRunner qr = new TxQueryRunner();

	/**
	 * ��ӻ�Ա
	 * 
	 * @param nature
	 * @param social
	 * @return
	 */
	public int insertMember(NatureInfo natureInfo, SocialInfo social) {
		// ����natureinfo��
		String n_sql = "insert into tb_natureInfo value(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		// ����socialinfo��
		String s_sql = "insert into tb_socialInfo value(?,?,?,?,?,?,?,?,?)";
		//
		String id = MemberUtils.generateID(natureInfo);
		Object[] param1 = { id, natureInfo.getName(), natureInfo.getGender(),
				natureInfo.getNation(), natureInfo.getBirthday(),
				natureInfo.getIDcard(), natureInfo.getAddress(),
				natureInfo.getTelphone(), natureInfo.getSchoolTag(),
				natureInfo.getMarriage(), natureInfo.getPhoto(),
				natureInfo.getDepName(), null };
		Object[] param2 = { id, social.getPolitics(), social.getDegree(),
				social.getProfession(), social.getWorkingTime(),
				social.getWorkStatus(), social.getStartTime(),
				social.getPosition(), social.getPartyPosition() };

		int result = 0;
		try {
			// 1.��������
			JdbcUtils.beginTransaction();

			// 2.ִ��sql���
			result = qr.update(n_sql, param1);
			qr.update(s_sql, param2);

			// 3.�ύ����
			JdbcUtils.commitTransaction();
		} catch (SQLException e) {
			// 4.�ع�����
			try {
				JdbcUtils.rollbackTransaction();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * ����ɾ����Ա
	 * 
	 * @param ids
	 */
	public void delBatchMember(String[] ids) {

		String sql = "delete from tb_natureInfo where tb_natureInfo.id =?";

		try {
			JdbcUtils.beginTransaction();
			for (String id : ids) {
				qr.update(sql, id);
			}
			JdbcUtils.commitTransaction();
			//
		} catch (Exception e) {
			try {
				JdbcUtils.rollbackTransaction();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}

	/**
	 * ����id���һ�Ա����
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public List<Object> findById(String id) throws SQLException {
		List<Object> list = new ArrayList<Object>();
		String sql = "select * from tb_natureInfo n,tb_socialInfo s where n.id = s.id and n.id =?";

		Map<String, Object> map = qr.query(sql, new MapHandler(), id);

		NatureInfo natureInfo = CommonUtils.toBean(map, NatureInfo.class);
		SocialInfo socialInfo = CommonUtils.toBean(map, SocialInfo.class);

		list.add(natureInfo);
		list.add(socialInfo);

		return list;
	}

	/**
	 * ����idɾ��������Ա��¼
	 * 
	 * @param id
	 * @throws SQLException
	 */
	public void deleteMember(String id) throws SQLException {
		String sql = "delete from tb_natureInfo where tb_natureInfo.id =?";
		qr.update(sql, id);
	}

	/**
	 * �޸Ļ�Ա��Ϣ
	 * 
	 * @param natureInfo
	 * @param socialInfo
	 */
	public void alterMemberInfo(NatureInfo natureInfo, SocialInfo socialInfo) {
		String sql1 = "update tb_natureInfo set name=?,gender=?,nation=?,birthday=?,IDcard=?,"
				+ "address=?,telphone=?,schoolTag=?,marriage=?,photo=?,depName=? where id=?";

		String sql2 = "update tb_socialInfo set politics=?,degree=?,profession=?,workingTime=?,"
				+ "workStatus=?,startTime=?,position=?,partyPosition=? where id=?";

		Object[] param1 = { natureInfo.getName(), natureInfo.getGender(),
				natureInfo.getNation(), natureInfo.getBirthday(),
				natureInfo.getIDcard(), natureInfo.getAddress(),
				natureInfo.getTelphone(), natureInfo.getSchoolTag(),
				natureInfo.getMarriage(), natureInfo.getPhoto(),
				natureInfo.getDepName(), natureInfo.getId() };

		Object[] param2 = { socialInfo.getPolitics(), socialInfo.getDegree(),
				socialInfo.getProfession(), socialInfo.getWorkingTime(),
				socialInfo.getWorkStatus(), socialInfo.getStartTime(),
				socialInfo.getPosition(), socialInfo.getPartyPosition(),
				socialInfo.getId() };
		try {
			// 1. ��������
			JdbcUtils.beginTransaction();
			// 2. ִ��sql���
			qr.update(sql1, param1);
			qr.update(sql2, param2);
			// 3. �ύ����
			JdbcUtils.commitTransaction();

		} catch (SQLException e) {
			// 4.��������쳣����ع�����
			try {
				JdbcUtils.rollbackTransaction();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	/**
	 * ����Ա����������ѯ
	 * 
	 * @param name
	 * @param pNum
	 * @return
	 * @throws SQLException
	 */
	public PageBean<NatureInfo> findByName(String name, int pNum)
			throws SQLException {

		String sql = "select * from tb_natureinfo ";
		String sql2 = "select count(*) from tb_natureinfo";
		String whereSql = " where name like ?";

		sql = sql + whereSql + " order by name limit ?,?";
		sql2 = sql2 + whereSql;

		if (name.trim() == null) {
			name = "*";
		} else {
			name = "%" + name + "%";
		}

		List<NatureInfo> listBean = qr.query(sql,
				new BeanListHandler<NatureInfo>(NatureInfo.class), name,
				(pNum - 1) * PageBean.PAGE_SIZE, PageBean.PAGE_SIZE);

		Number number = (Number) qr.query(sql2, new ScalarHandler(), name);
		int totalRecord = number.intValue();

		PageBean<NatureInfo> pageBean = new PageBean<NatureInfo>();

		pageBean.setListBean(listBean);
		pageBean.setpNum(pNum);
		pageBean.setTotalRecord(totalRecord);

		return pageBean;
	}
}