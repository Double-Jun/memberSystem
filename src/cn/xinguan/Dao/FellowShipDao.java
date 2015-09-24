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
 * 单身联谊--> 数据层
 * 
 * @author MingJun Chen
 * 
 */
public class FellowShipDao {
	private QueryRunner qr = new TxQueryRunner();
	PageBean<NatureInfo> pageBeanNature = new PageBean<NatureInfo>();

	/**
	 * 查询所有会员记录
	 * 
	 * @return
	 * @throws SQLException
	 */
	public PageBean<NatureInfo> selectAllMember(int pNum) throws SQLException {
		// where语句，此处为空
		List<Expression> exprList = new ArrayList<Expression>();
		// 调用PageUtils中的多条件查询方法，实现PageBean<natureInfo>的封装
		PageBean<NatureInfo> pageBeanNature = PageUtils.findByCriteria(
				exprList, pNum);
		return pageBeanNature;
	}

	/**
	 * 多条件查询 性别， 婚姻状况， 所在部门， 年龄范围
	 * 
	 * @param params
	 * @param pNum
	 * @return List<NatureInfo>
	 * @throws SQLException
	 */
	public PageBean<NatureInfo> conditionsQuery(Map<String, String> params,
			int pNum) throws SQLException {
		String gender = params.get("conditionGender"); // 性别
		String marriage = params.get("conditionMarriage"); // 婚姻状况
		String depName = params.get("conditionDep"); // 所在部门
		String minAge = params.get("conditionMinAge"); // 年龄上限
		String maxAge = params.get("conditionMaxAge"); // 年龄下限

		List<Object> list = new ArrayList<Object>(); // 存放不为空的条件

		String sql = "select * from tb_natureinfo"; // 固定的sql语句
		String sql2 = "select count(*) from tb_natureinfo";
		String whereSql = " where 1=1";

		if (!("".equals(gender))) { // 性别是否为空
			whereSql = whereSql + " and gender = ?";
			list.add(gender);
		}
		if (!("".equals(marriage))) { // 婚姻状况是否为空
			whereSql = whereSql + " and marriage = ?";
			list.add(marriage);
		}
		if (!("".equals(depName))) { // 所在部门是否为空
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

		list.add((pNum - 1) * PageBean.PAGE_SIZE); // 添加起始查询位置
		list.add(PageBean.PAGE_SIZE); // 添加查询条数限制

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
