package cn.xinguan.server;

import java.sql.SQLException;
import java.util.Map;

import cn.xinguan.Dao.FellowShipDao;
import cn.xinguan.damain.NatureInfo;
import cn.xinguan.damain.PageBean;

/**
 * 单身联谊 --> 业务层
 * 
 * @author MingJun Chen
 * 
 */
public class FellowShipServer {
	private FellowShipDao fsd = new FellowShipDao();

	/**
	 * 查询所有会员记录
	 * 
	 * @return List<NatureInfo>
	 * @throws SQLException
	 */
	public PageBean<NatureInfo> seachAllMember(int pNum) throws SQLException {
		return fsd.selectAllMember(pNum);
	}

	/**
	 * 多条件组合查询
	 * 
	 * @param params
	 * @param pNum
	 * @return List<NatureInfo>
	 * @throws SQLException
	 */
	public PageBean<NatureInfo> conditionsQuery(Map<String, String> params,
			int pNum) throws SQLException {
		return fsd.conditionsQuery(params, pNum);
	}

}
