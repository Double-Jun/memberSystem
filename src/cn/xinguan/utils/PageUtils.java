package cn.xinguan.utils;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.jdbc.TxQueryRunner;
import cn.xinguan.damain.NatureInfo;
import cn.xinguan.damain.PageBean;

/**
 * ��ҳ������
 * 
 * @author MingJun Chen
 * 
 */
public class PageUtils {

	/**
	 * ͨ�õĲ�ѯ����
	 * 
	 * @param exprList
	 * @param pc
	 * @return
	 * @throws SQLException
	 */
	public static PageBean<NatureInfo> findByCriteria(
			List<Expression> exprList, int pNum) throws SQLException {
		TxQueryRunner qr = new TxQueryRunner();

		/*
		 * 1. �õ�PageSize 2. �õ�totalRoc 3. �õ�listBean 4. ����PageBean������
		 */
		/*
		 * 1. �õ�ps
		 */
		int ps = PageBean.PAGE_SIZE; // ÿҳ��¼��
		/*
		 * 2. ͨ��exprList������where�Ӿ�
		 */
		StringBuilder whereSql = new StringBuilder(" where 1=1");
		List<Object> params = new ArrayList<Object>();// SQL�����ʺţ����Ƕ�Ӧ�ʺŵ�ֵ
		for (Expression expr : exprList) {
			/*
			 * ���һ�������ϣ� 1) ��and��ͷ 2) ���������� 3) �������������������=��!=��>��< ... is null��is
			 * nullû��ֵ 4) �����������is null����׷���ʺţ�Ȼ������params�����һ���ʺŶ�Ӧ��ֵ
			 */
			whereSql.append(" and ").append(expr.getName()).append(" ")
					.append(expr.getOperator()).append(" ");
			// where 1=1 and bid = ?
			if (!expr.getOperator().equals("is null")) {
				whereSql.append("?");
				params.add(expr.getValue());
			}
		}

		/*
		 * 3. �ܼ�¼��
		 */
		String sql = "select count(*) from tb_natureinfo" + whereSql;
		Number number = (Number) qr.query(sql, new ScalarHandler(),
				params.toArray());
		int tr = number.intValue();// �õ����ܼ�¼��
		/*
		 * 4. �õ�beanList������ǰҳ��¼
		 */
		sql = "select * from tb_natureinfo" + whereSql
				+ " order by name limit ?,?";
		params.add((pNum - 1) * ps);// ��ǰҳ���м�¼���±�
		params.add(ps);// һ����ѯ���У�����ÿҳ��¼��

		List<NatureInfo> beanList = qr.query(sql,
				new BeanListHandler<NatureInfo>(NatureInfo.class),
				params.toArray());
		/*
		 * 5. ����PageBean�����ò���
		 */
		PageBean<NatureInfo> pb = new PageBean<NatureInfo>();
		/*
		 * ����PageBeanû��url�����������Servlet���
		 */
		pb.setListBean(beanList);
		pb.setTotalRecord(tr);
		pb.setpNum(pNum);

		return pb;
	}

	/**
	 * ��ȡ��ǰҳ��
	 * 
	 * @param req
	 * @return
	 */
	public static int getPNum(HttpServletRequest request) {
		int pc = 1;
		String param = request.getParameter("pNum");
		if (param != null && !param.trim().isEmpty()) {
			try {
				pc = Integer.parseInt(param);
			} catch (RuntimeException e) {
			}
		}
		return pc;
	}

	/**
	 * ��ȡurl��ҳ���еķ�ҳ��������Ҫʹ������Ϊ�����ӵ�Ŀ�꣡
	 * 
	 * @param req
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	/*
	 * http://localhost:8080/goods/BookServlet?methed=findByCategory&cid=xxx&pc=3
	 * /goods/BookServlet + methed=findByCategory&cid=xxx&pc=3
	 */
	public static String getUrl(HttpServletRequest request)
			throws UnsupportedEncodingException {
		String url = request.getRequestURI() + "?" + request.getQueryString();
		// url = URLDecoder.decode(url, "UTF-8");
		// System.out.println("PageUtils's getUrl:" + url);
		/*
		 * ���url�д���pc��������ȡ��������������ǾͲ��ý�ȡ��
		 */
		int index = url.lastIndexOf("&pNum=");
		if (index != -1) {
			url = url.substring(0, index);
		}
		return url;
	}

	/**
	 * ����û�ip
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {

		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

}
