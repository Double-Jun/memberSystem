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
 * 读取Excel工具包
 * 
 * @author MingJun Chen
 * 
 */
public class ReadExcelUtils {

	/**
	 * 读取Excel
	 * 
	 * @param filePath
	 * @param sql
	 * @throws BiffException
	 * @throws IOException
	 */
	public static void readExcel(String filePath, String sql)
			throws BiffException, IOException {

		TxQueryRunner qr = new TxQueryRunner(); // 创建一个sql执行对象

		ArrayList<Object> paras = new ArrayList<Object>(); //
		ArrayList<Object> para = new ArrayList<Object>();

		File file = new File(filePath); // 创建文件对象

		Workbook wb = Workbook.getWorkbook(file); // 从文件流中获取Excel工作区对象

		Sheet sheet = wb.getSheet(0); // 从工作区中取得页（Sheet）

		for (int i = 1; i < sheet.getRows(); i++) { // 循环遍历所有行

			paras.add(para);
			para.clear();

			// for (int j = 0; j < sheet.getColumns(); j++) { // 遍历一行的所有列
			for (int j = 0; j <= 1; j++) {
				Cell cell = sheet.getCell(j, i);

				if (cell.getType() == CellType.DATE) {
					DateCell dc = (DateCell) cell;
					Date date = dc.getDate();
					SimpleDateFormat ds = new SimpleDateFormat("yyyy-MM-dd");
					para.add(ds.format(date));
				} else {
					para.add(cell.getContents().trim());
					System.out.println("表单数据：" + cell.getContents());
				}
				System.out.println("数据添加：" + para.size());
				System.out.println("para:" + para.toArray());
			}

		}

		int i = 1;
		for (Object list : paras) {

			try {
				JdbcUtils.beginTransaction();

				// qr.update(sql, list.toArray());

				// System.out
				// .println(list.toString() + ":" + "第" + i + "行记录更新成功！");
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
