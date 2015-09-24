package cn.xinguan.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.xinguan.damain.NatureInfo;
import cn.xinguan.damain.PageBean;
import cn.xinguan.utils.Expression;
import cn.xinguan.utils.PageUtils;

/**
 * ��Ա��Ϣ --- �־ò�
 * 
 * @author MingJun Chen
 * 
 */
public class MemberInfoDao {

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
		List<Expression> exprList = new ArrayList<Expression>();
		exprList.add(new Expression(condition, "like", "%" + value + "%"));
		return PageUtils.findByCriteria(exprList, pNum);
	}
}
