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
				"attachment;filename=member_info.xls"); // ���������ʽ , �ļ�����

		String gender = request.getParameter("conditionGender"); // �Ա�
		String depName = request.getParameter("conditionDep"); // ���ڲ���
		String minAge = request.getParameter("conditionMinAge"); // ��������
		String maxAge = request.getParameter("conditionMaxAge"); // ��������

		System.out.println(request.getParameter("pageBean"));

		// ����һ��ExportExcel����������
		ExportExcel<Member> ex = new ExportExcel<Member>();

		// ͷ����Ϣ
		String[] head = { "��Ա���", "����", "�Ա�", "����", "��������", "���֤��", "����",
				"��ϵ��ʽ", "��ҵԺУ", "����״��", "��Ƭ", "���ڲ���", "������ò", "�ִ���רҵ����", "ѧ��",
				"����ʱ��", "����״̬", "ְ��", "��Уʱ��", "����ְ��" };

		// ������з��������Ļ�Ա
		List<Member> list = new ArrayList<Member>();

		List<Object> params = new ArrayList<Object>(); // ��Ų�Ϊ�յ�����

		TxQueryRunner qr = new TxQueryRunner();
		String sql = "select * from tb_natureinfo n,tb_socialinfo s";

		String whereSql = " where 1=1";
		if (!("".equals(gender)) && gender != null) { // �Ա��Ƿ�Ϊ��
			whereSql = whereSql + " and gender like ?";
			params.add(gender);
		}
		if (!("".equals(depName)) && depName != null) { // ���ڲ����Ƿ�Ϊ��
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

		Object[] param = params.toArray(); // ��Set����ת��Ϊ���飬��Ϊsql���Ĳ���

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
