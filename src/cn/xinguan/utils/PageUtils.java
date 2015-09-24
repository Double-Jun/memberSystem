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
 * 分页工具类
 * 
 * @author MingJun Chen
 * 
 */
public class PageUtils {

	/**
	 * 通用的查询方法
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
		 * 1. 得到PageSize 2. 得到totalRoc 3. 得到listBean 4. 创建PageBean，返回
		 */
		/*
		 * 1. 得到ps
		 */
		int ps = PageBean.PAGE_SIZE; // 每页记录数
		/*
		 * 2. 通过exprList来生成where子句
		 */
		StringBuilder whereSql = new StringBuilder(" where 1=1");
		List<Object> params = new ArrayList<Object>();// SQL中有问号，它是对应问号的值
		for (Expression expr : exprList) {
			/*
			 * 添加一个条件上， 1) 以and开头 2) 条件的名称 3) 条件的运算符，可以是=、!=、>、< ... is null，is
			 * null没有值 4) 如果条件不是is null，再追加问号，然后再向params中添加一与问号对应的值
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
		 * 3. 总记录数
		 */
		String sql = "select count(*) from tb_natureinfo" + whereSql;
		Number number = (Number) qr.query(sql, new ScalarHandler(),
				params.toArray());
		int tr = number.intValue();// 得到了总记录数
		/*
		 * 4. 得到beanList，即当前页记录
		 */
		sql = "select * from tb_natureinfo" + whereSql
				+ " order by name limit ?,?";
		params.add((pNum - 1) * ps);// 当前页首行记录的下标
		params.add(ps);// 一共查询几行，就是每页记录数

		List<NatureInfo> beanList = qr.query(sql,
				new BeanListHandler<NatureInfo>(NatureInfo.class),
				params.toArray());
		/*
		 * 5. 创建PageBean，设置参数
		 */
		PageBean<NatureInfo> pb = new PageBean<NatureInfo>();
		/*
		 * 其中PageBean没有url，这个任务由Servlet完成
		 */
		pb.setListBean(beanList);
		pb.setTotalRecord(tr);
		pb.setpNum(pNum);

		return pb;
	}

	/**
	 * 获取当前页码
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
	 * 截取url，页面中的分页导航中需要使用它做为超链接的目标！
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
		 * 如果url中存在pc参数，截取掉，如果不存在那就不用截取。
		 */
		int index = url.lastIndexOf("&pNum=");
		if (index != -1) {
			url = url.substring(0, index);
		}
		return url;
	}

	/**
	 * 获得用户ip
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
