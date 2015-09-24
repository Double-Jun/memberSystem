package cn.xinguan.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.jdbc.TxQueryRunner;
import cn.xinguan.damain.NatureInfo;
import cn.xinguan.damain.PageBean;
import cn.xinguan.utils.Expression;
import cn.xinguan.utils.PageUtils;

public class PhysicalDao {
	private TxQueryRunner qr = new TxQueryRunner();

	/**
	 * ͨ����������������ѯ��Ա
	 * 
	 * @param params
	 * @param pNum
	 * @return
	 */
	public PageBean<NatureInfo> searchByAge(HashMap<String, String> params,
			int pNum) {
		PageBean<NatureInfo> pageBeanNature = new PageBean<NatureInfo>();
		List<Expression> exprList = new ArrayList<Expression>();

		String minAge = params.get("minAge");
		String maxAge = params.get("maxAge");

		if (!("".equals(minAge) || "".equals(maxAge))) {
			// where��䣬�˴�Ϊ��
			Expression ex1 = new Expression();
			ex1.setName("year(now())-year(birthday)");
			ex1.setOperator(">=");
			ex1.setValue(minAge);
			exprList.add(ex1);
			Expression ex2 = new Expression();
			ex2.setName("year(now())-year(birthday)");
			ex2.setOperator("<=");
			ex2.setValue(maxAge);
			exprList.add(ex2);

			// ����PageUtils�еĶ�������ѯ������ʵ��PageBean<natureInfo>�ķ�װ
			try {
				pageBeanNature = PageUtils.findByCriteria(exprList, pNum);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			try {
				pageBeanNature = PageUtils.findByCriteria(exprList, pNum);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return pageBeanNature;
	}

	/**
	 * ��������ϲ�ѯ
	 * 
	 * @param params
	 * @param pNum
	 * @return
	 * @throws SQLException
	 */
	public PageBean<NatureInfo> conditionsQuery(Map<String, String> params,
			int pNum) throws SQLException {
		String gender = params.get("conditionGender"); // �Ա�
		String depName = params.get("conditionDep"); // ���ڲ���
		String physical = params.get("physical"); // �Ƿ����
		String minAge = params.get("conditionMinAge"); // ��������
		String maxAge = params.get("conditionMaxAge"); // ��������

		System.out.println("gender��" + gender);
		List<Object> list = new ArrayList<Object>(); // ��Ų�Ϊ�յ�����

		String sql = "select * from tb_natureinfo"; // �̶���sql���
		String sql2 = "select count(*) from tb_natureinfo";
		String whereSql = " where 1=1";

		if (!("".equals(gender)) && gender != null) { // �Ա��Ƿ�Ϊ��
			whereSql = whereSql + " and gender = ?";
			list.add(gender);
		}
		if (!("".equals(depName)) && depName != null) { // ���ڲ����Ƿ�Ϊ��
			whereSql = whereSql + " and depName = ?";
			list.add(depName);
		}
		if (!("".equals(physical)) && physical != null) {
			whereSql = whereSql + " and status = ?";
			list.add(physical);
		}
		if (!("".equals(minAge) || "".equals(maxAge)) && minAge != null
				&& maxAge != null) {
			whereSql = whereSql
					+ " and year(now())-year(birthday)>=? and year(now())-year(birthday)<=? ";
			list.add(minAge);
			list.add(maxAge);
		}

		sql = sql + whereSql + " order by name limit ?,?";
		sql2 = sql2 + whereSql;
		Object[] param2 = list.toArray();

		list.add((pNum - 1) * 9); // �����ʼ��ѯλ��
		list.add(9); // ��Ӳ�ѯ��������

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
