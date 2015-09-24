package cn.xinguan.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.jdbc.TxQueryRunner;
import cn.xinguan.damain.NatureInfo;
import cn.xinguan.damain.PageBean;
import cn.xinguan.utils.Expression;
import cn.xinguan.utils.PageUtils;

/**
 * ��������--> ���ݲ�
 * 
 * @author MingJun Chen
 * 
 */
public class FellowShipDao {
	private QueryRunner qr = new TxQueryRunner();
	PageBean<NatureInfo> pageBeanNature = new PageBean<NatureInfo>();

	/**
	 * ��ѯ���л�Ա��¼
	 * 
	 * @return
	 * @throws SQLException
	 */
	public PageBean<NatureInfo> selectAllMember(int pNum) throws SQLException {
		// where��䣬�˴�Ϊ��
		List<Expression> exprList = new ArrayList<Expression>();
		// ����PageUtils�еĶ�������ѯ������ʵ��PageBean<natureInfo>�ķ�װ
		PageBean<NatureInfo> pageBeanNature = PageUtils.findByCriteria(
				exprList, pNum);
		return pageBeanNature;
	}

	/**
	 * ��������ѯ �Ա� ����״���� ���ڲ��ţ� ���䷶Χ
	 * 
	 * @param params
	 * @param pNum
	 * @return List<NatureInfo>
	 * @throws SQLException
	 */
	public PageBean<NatureInfo> conditionsQuery(Map<String, String> params,
			int pNum) throws SQLException {
		String gender = params.get("conditionGender"); // �Ա�
		String marriage = params.get("conditionMarriage"); // ����״��
		String depName = params.get("conditionDep"); // ���ڲ���
		String minAge = params.get("conditionMinAge"); // ��������
		String maxAge = params.get("conditionMaxAge"); // ��������

		List<Object> list = new ArrayList<Object>(); // ��Ų�Ϊ�յ�����

		String sql = "select * from tb_natureinfo"; // �̶���sql���
		String sql2 = "select count(*) from tb_natureinfo";
		String whereSql = " where 1=1";

		if (!("".equals(gender))) { // �Ա��Ƿ�Ϊ��
			whereSql = whereSql + " and gender = ?";
			list.add(gender);
		}
		if (!("".equals(marriage))) { // ����״���Ƿ�Ϊ��
			whereSql = whereSql + " and marriage = ?";
			list.add(marriage);
		}
		if (!("".equals(depName))) { // ���ڲ����Ƿ�Ϊ��
			whereSql = whereSql + " and depName = ?";
			list.add(depName);
		}
		if (!("".equals(minAge) || "".equals(maxAge))) {
			whereSql = whereSql
					+ " and year(now())-year(birthday)>=? and year(now())-year(birthday)<=? ";
			list.add(minAge);
			list.add(maxAge);
		}

		sql = sql + whereSql + " order by name limit ?,?";
		sql2 = sql2 + whereSql;
		Object[] param2 = list.toArray();

		list.add((pNum - 1) * PageBean.PAGE_SIZE); // �����ʼ��ѯλ��
		list.add(PageBean.PAGE_SIZE); // ��Ӳ�ѯ��������

		Object[] param = list.toArray(); // ��Set����ת��Ϊ���飬��Ϊsql���Ĳ���
		List<NatureInfo> listBean = qr.query(sql,
				new BeanListHandler<NatureInfo>(NatureInfo.class), param);

		Number number = (Number) qr.query(sql2, new ScalarHandler(), param2);
		int totalRecord = number.intValue();// �õ����ܼ�¼��

		PageBean<NatureInfo> pageBean = new PageBean<NatureInfo>();

		pageBean.setListBean(listBean);
		pageBean.setpNum(pNum);
		pageBean.setTotalRecord(totalRecord);

		return pageBean;
	}
}
