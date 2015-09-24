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
	 * 通过年龄上下限来查询会员
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
			// where语句，此处为空
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

			// 调用PageUtils中的多条件查询方法，实现PageBean<natureInfo>的封装
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
	 * 多条件组合查询
	 * 
	 * @param params
	 * @param pNum
	 * @return
	 * @throws SQLException
	 */
	public PageBean<NatureInfo> conditionsQuery(Map<String, String> params,
			int pNum) throws SQLException {
		String gender = params.get("conditionGender"); // 性别
		String depName = params.get("conditionDep"); // 所在部门
		String physical = params.get("physical"); // 是否体检
		String minAge = params.get("conditionMinAge"); // 年龄上限
		String maxAge = params.get("conditionMaxAge"); // 年龄下限

		System.out.println("gender：" + gender);
		List<Object> list = new ArrayList<Object>(); // 存放不为空的条件

		String sql = "select * from tb_natureinfo"; // 固定的sql语句
		String sql2 = "select count(*) from tb_natureinfo";
		String whereSql = " where 1=1";

		if (!("".equals(gender)) && gender != null) { // 性别是否为空
			whereSql = whereSql + " and gender = ?";
			list.add(gender);
		}
		if (!("".equals(depName)) && depName != null) { // 所在部门是否为空
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

		list.add((pNum - 1) * 9); // 添加起始查询位置
		list.add(9); // 添加查询条数限制

		Object[] param = list.toArray(); // 将Set集合转换为数组，作为sql语句的参数
		List<NatureInfo> listBean = qr.query(sql,
				new BeanListHandler<NatureInfo>(NatureInfo.class), param);

		Number number = (Number) qr.query(sql2, new ScalarHandler(), param2);
		int totalRecord = number.intValue();// 得到了总记录数

		PageBean<NatureInfo> pageBean = new PageBean<NatureInfo>();

		pageBean.setListBean(listBean);
		pageBean.setpNum(pNum);
		pageBean.setTotalRecord(totalRecord);

		return pageBean;
	}
}
