package cn.xinguan.server;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import cn.xinguan.Dao.PhysicalDao;
import cn.xinguan.damain.NatureInfo;
import cn.xinguan.damain.PageBean;

public class PhysicalServer {
	private PhysicalDao pd = new PhysicalDao();

	/**
	 * 根据年龄查询
	 * 
	 * @param params
	 * @param pNum
	 * @return
	 */
	public PageBean<NatureInfo> searchByAge(HashMap<String, String> params,
			int pNum) {
		return pd.searchByAge(params, pNum);
	}

	/**
	 * 多条件组合查询
	 * 
	 * @param params
	 * @param pNum
	 * @return
	 */
	public PageBean<NatureInfo> conditionsQuery(Map<String, String> params,
			int pNum) {
		try {
			return pd.conditionsQuery(params, pNum);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
