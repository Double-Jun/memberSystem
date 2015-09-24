package cn.xinguan.web;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.jdbc.TxQueryRunner;
import cn.xinguan.damain.Member;
import cn.xinguan.utils.ExportExcel;

public class Export extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("octets/stream");
		response.setCharacterEncoding("utf-8");
		response.addHeader("Content-Disposition",
				"attachment;filename=member_info.xls"); // 设置输出格式 , 文件名称

		String gender = request.getParameter("conditionGender"); // 性别
		String depName = request.getParameter("conditionDep"); // 所在部门
		String minAge = request.getParameter("conditionMinAge"); // 年龄下限
		String maxAge = request.getParameter("conditionMaxAge"); // 年龄上线

		System.out.println(request.getParameter("pageBean"));

		// 创建一个ExportExcel报表到处对象
		ExportExcel<Member> ex = new ExportExcel<Member>();

		// 头部信息
		String[] head = { "会员编号", "姓名", "性别", "民族", "出身年月", "身份证号", "籍贯",
				"联系方式", "毕业院校", "婚姻状况", "照片", "所在部门", "政治面貌", "现从事专业方向", "学历",
				"工作时间", "工作状态", "职务", "来校时间", "党政职务" };

		// 存放所有符合条件的会员
		List<Member> list = new ArrayList<Member>();

		List<Object> params = new ArrayList<Object>(); // 存放不为空的条件

		TxQueryRunner qr = new TxQueryRunner();
		String sql = "select * from tb_natureinfo n,tb_socialinfo s";

		String whereSql = " where 1=1";
		if (!("".equals(gender)) && gender != null) { // 性别是否为空
			whereSql = whereSql + " and gender like ?";
			params.add(gender);
		}
		if (!("".equals(depName)) && depName != null) { // 所在部门是否为空
			whereSql = whereSql + " and depName like ?";
			params.add(depName);
		}
		if (!("".equals(minAge) || "".equals(maxAge)) && minAge != null
				&& maxAge != null) {
			whereSql = whereSql
					+ " and year(now())-year(birthday)>=? and year(now())-year(birthday)<=? ";
			params.add(minAge);
			params.add(maxAge);
		}

		sql = sql + whereSql + " and n.id=s.id;";

		Object[] param = params.toArray(); // 将Set集合转换为数组，作为sql语句的参数

		try {
			list = qr.query(sql, new BeanListHandler<Member>(Member.class),
					param);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		OutputStream out = response.getOutputStream();

		ex.exportExcel(head, list, out);

		out.close();

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
