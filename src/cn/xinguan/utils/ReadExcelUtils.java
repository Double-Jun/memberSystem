package cn.xinguan.utils;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import cn.itcast.jdbc.JdbcUtils;
import cn.itcast.jdbc.TxQueryRunner;

/**
 * ��ȡExcel���߰�
 * 
 * @author MingJun Chen
 * 
 */
public class ReadExcelUtils {

	/**
	 * ��ȡExcel
	 * 
	 * @param filePath
	 * @param sql
	 * @throws BiffException
	 * @throws IOException
	 */
	public static void readExcel(String filePath, String sql)
			throws BiffException, IOException {

		TxQueryRunner qr = new TxQueryRunner(); // ����һ��sqlִ�ж���

		ArrayList<Object> paras = new ArrayList<Object>(); //
		ArrayList<Object> para = new ArrayList<Object>();

		File file = new File(filePath); // �����ļ�����

		Workbook wb = Workbook.getWorkbook(file); // ���ļ����л�ȡExcel����������

		Sheet sheet = wb.getSheet(0); // �ӹ�������ȡ��ҳ��Sheet��

		for (int i = 1; i < sheet.getRows(); i++) { // ѭ������������

			paras.add(para);
			para.clear();

			// for (int j = 0; j < sheet.getColumns(); j++) { // ����һ�е�������
			for (int j = 0; j <= 1; j++) {
				Cell cell = sheet.getCell(j, i);

				if (cell.getType() == CellType.DATE) {
					DateCell dc = (DateCell) cell;
					Date date = dc.getDate();
					SimpleDateFormat ds = new SimpleDateFormat("yyyy-MM-dd");
					para.add(ds.format(date));
				} else {
					para.add(cell.getContents().trim());
					System.out.println("�����ݣ�" + cell.getContents());
				}
				System.out.println("������ӣ�" + para.size());
				System.out.println("para:" + para.toArray());
			}

		}

		int i = 1;
		for (Object list : paras) {

			try {
				JdbcUtils.beginTransaction();

				// qr.update(sql, list.toArray());

				// System.out
				// .println(list.toString() + ":" + "��" + i + "�м�¼���³ɹ���");
				i++;

				JdbcUtils.commitTransaction();
			} catch (SQLException e) {
				try {
					JdbcUtils.rollbackTransaction();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
		}
	}
}
