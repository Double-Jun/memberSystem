package cn.xinguan.server;

import java.sql.SQLException;
import java.util.Map;

import cn.xinguan.Dao.FellowShipDao;
import cn.xinguan.damain.NatureInfo;
import cn.xinguan.damain.PageBean;

/**
 * �������� --> ҵ���
 * 
 * @author MingJun Chen
 * 
 */
public class FellowShipServer {
	private FellowShipDao fsd = new FellowShipDao();

	/**
	 * ��ѯ���л�Ա��¼
	 * 
	 * @return List<NatureInfo>
	 * @throws SQLException
	 */
	public PageBean<NatureInfo> seachAllMember(int pNum) throws SQLException {
		return fsd.selectAllMember(pNum);
	}

	/**
	 * ��������ϲ�ѯ
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
