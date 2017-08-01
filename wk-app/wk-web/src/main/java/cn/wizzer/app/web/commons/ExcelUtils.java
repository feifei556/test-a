package cn.wizzer.app.web.commons;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import cn.wizzer.app.rp.modules.models.Rp_qrcode;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;


/**
 * 读取 excel表格，兼容2003和2007
 * 
 * @author ChuanJing
 */
public class ExcelUtils<T> {

	/** 总行数 */
	private int totalRows = 0;

	/** 总列数 */
	private int totalCells = 0;

	/** 错误信息 */
	private String errorInfo;

	/** 构造方法 */
	public ExcelUtils() {
	}

	/**
	 * 得到总行数
	 */
	public int getTotalRows() {
		return totalRows;
	}

	/**
	 * 得到总列数
	 */
	public int getTotalCells() {
		return totalCells;
	}

	/**
	 * 得到错误信息
	 */
	public String getErrorInfo() {
		return errorInfo;
	}

	/**
	 * 
	 * 验证excel文件
	 * 
	 * @param：filePath 文件完整路径
	 * @return 返回 true 表示文件没有问题
	 */
	public boolean validateExcel(String filePath) {
		/** 检查文件名是否为空或者是否是Excel格式的文件 */
		if (filePath == null || !(isExcel2003(filePath) || isExcel2007(filePath))) {
			errorInfo = "文件不是excel格式";
			return false;
		}

		/** 检查文件是否存在 */
		File file = new File(filePath);

		if (file == null || !file.exists()) {
			errorInfo = "文件不存在";
			return false;
		}

		return true;
	}

	/**
	 * 根据文件名读取excel文件
	 * 
	 * @param filePath
	 *            文件完整路径
	 * @param ignoreRows
	 *            读取数据忽略的行数，比喻第一行不需要读入，忽略的行数为1
	 * @return：List 最后读取的结果，数据结构：List<List<String>>
	 */
	public List<List<String>> read(String filePath, int ignoreRows) {

		List<List<String>> dataLst = new ArrayList<List<String>>();
		InputStream is = null;

		try {
			/** 验证文件是否合法 */
			if (!validateExcel(filePath)) {
				System.out.println(errorInfo);
				return null;
			}

			/** 判断文件的类型，是2003还是2007 */
			boolean isExcel2003 = true;
			if (isExcel2007(filePath)) {
				isExcel2003 = false;
			}

			/** 调用本类提供的根据流读取的方法 */
			File file = new File(filePath);
			is = new FileInputStream(file);
			dataLst = read(is, isExcel2003, ignoreRows);
			is.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (is != null) {

				try {
					is.close();
				} catch (IOException e) {
					is = null;
					e.printStackTrace();
				}

			}
		}

		/** 返回最后读取的结果 */
		return dataLst;
	}

	/**
	 * 根据流读取Excel文件
	 * 
	 * @param inputStream
	 * @param isExcel2003
	 *            是否是2003的表格（xls格式）
	 * @param ignoreRows
	 *            读取数据忽略的行数，比喻第一行不需要读入，忽略的行数为1
	 * @return：List
	 */
	public List<List<String>> read(InputStream inputStream, boolean isExcel2003, int ignoreRows) {
		List<List<String>> dataLst = null;
		try {

			/** 根据版本选择创建Workbook的方式 */
			Workbook wb = null;

			if (isExcel2003) {
				wb = new HSSFWorkbook(inputStream);
			} else {
				wb = new XSSFWorkbook(inputStream);
			}
			dataLst = read(wb, ignoreRows);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return dataLst;
	}

	/**
	 * 读取数据
	 *
	 * @param ignoreRows
	 *            读取数据忽略的行数，比喻第一行不需要读入，忽略的行数为1
	 * @reture：List<List<String>>
	 */
	private List<List<String>> read(Workbook wb, int ignoreRows) {
		List<List<String>> dataLst = new ArrayList<List<String>>();

		/** 得到第一个shell */
		Sheet sheet = wb.getSheetAt(0);

		/** 得到Excel的行数 */
		this.totalRows = sheet.getPhysicalNumberOfRows();

		/** 得到Excel的列数，不从表格的第一行得到列数，从忽略之后的，要读取的第一行 获取列数 */
		if (this.totalRows >= 1 && sheet.getRow(ignoreRows) != null) {
			this.totalCells = sheet.getRow(ignoreRows).getPhysicalNumberOfCells();
		}

		/** 循环Excel的行，不从表格的第一行循环，去掉忽略的行数 */
		for (int r = ignoreRows; r < this.totalRows; r++) {
			Row row = sheet.getRow(r);

			if (row == null) {
				continue;
			}

			List<String> rowLst = new ArrayList<String>();

			/** 循环Excel的列 */
			for (int c = 0; c <= this.getTotalCells(); c++) {
				Cell cell = row.getCell(c);
				String cellValue = "";

				if (null != cell) {
					// 以下是判断数据的类型
					switch (cell.getCellType()) {
					case HSSFCell.CELL_TYPE_NUMERIC: // 数字

						// 如果数字是日期类型，就转换成日期
						if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式
							SimpleDateFormat sdf = null;
							if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")) {
								sdf = new SimpleDateFormat("HH:mm");
							} else {// 日期
								sdf = new SimpleDateFormat("yyyy年MM月dd日");
							}
							Date date = cell.getDateCellValue();
							cellValue = sdf.format(date);
						} else if (cell.getCellStyle().getDataFormat() == 31) {
							// 处理自定义日期格式：yyyy年m月d日(通过判断单元格的格式id解决，id的值是31)
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
							double value = cell.getNumericCellValue();
							Date date = DateUtil.getJavaDate(value);
							cellValue = sdf.format(date);
						} else {
							double value = cell.getNumericCellValue();
							CellStyle style = cell.getCellStyle();
							DecimalFormat format = new DecimalFormat();
							String temp = style.getDataFormatString();
							// 单元格设置成常规
							if (temp.equals("General")) {
								format.applyPattern("#");
							}
							cellValue = format.format(value);
						}
						break;

					case HSSFCell.CELL_TYPE_STRING: // 字符串
						cellValue = cell.getStringCellValue();
						break;

					case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
						cellValue = cell.getBooleanCellValue() + "";
						break;

					case HSSFCell.CELL_TYPE_FORMULA: // 公式
						cellValue = cell.getCellFormula() + "";
						break;

					case HSSFCell.CELL_TYPE_BLANK: // 空值
						cellValue = "";
						break;

					case HSSFCell.CELL_TYPE_ERROR: // 故障
						cellValue = "非法字符";
						break;

					default:
						cellValue = "未知类型";
						break;
					}
				}
				rowLst.add(cellValue);
			}

			/** 保存第r行的第c列 */
			dataLst.add(rowLst);
		}
		return dataLst;
	}

	/**
	 * 是否是2003的excel，返回true是2003
	 * 
	 * @param filePath
	 *            文件完整路径
	 * @return boolean true代表是2003
	 */
	public static boolean isExcel2003(String filePath) {
		return filePath.matches("^.+\\.(?i)(xls)$");
	}

	/**
	 * 是否是2007的excel，返回true是2007
	 * 
	 * @param filePath
	 *            文件完整路径
	 * @return boolean true代表是2007
	 */
	public static boolean isExcel2007(String filePath) {
		return filePath.matches("^.+\\.(?i)(xlsx)$");
	}

	// public static boolean isExcel(String filePath) {
	// return filePath.matches("^.+\\.(?i)(xlsx)$");
	// }

/*	public static void main(String[] args) throws Exception {
		ExcelUtils re = new ExcelUtils();
		// List<List<String>> list =
		// re.read("C:\\Users\\Iris004\\Desktop\\测试表格.xls", 5);//忽略前5行
		List<List<String>> list = re.read("f:\\jl.xlsx", 0);// 忽略前5行

		// 遍历读取结果
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				System.out.print("第" + (6 + i) + "行");
				List<String> cellList = list.get(i);
				for (int j = 0; j < cellList.size(); j++) {
					System.out.print(" 第" + (j + 1) + "列值：");
					System.out.print(" " + cellList.get(j));
				}
				System.out.println();
			}
		}
	}*/

	/**
	 * 读取office 2007 xlsx
	 *	王家明
	 * @throws IllegalAccessException 
	 * @throws Exception 
	 */
	public List<Object> loadXlsx(/*String filePath*/File file,T t) throws Exception{
		List<Object> list = new ArrayList<>();
		// 构造 XSSFWorkbook 对象，strPath 传入文件路径
		XSSFWorkbook xwb = null;
		try {
			xwb = new XSSFWorkbook(new FileInputStream(file));
		} catch (IOException e) {
			System.out.println("读取文件出错");
			e.printStackTrace();
		}
		// 读取第一章表格内容
		XSSFSheet sheet = xwb.getSheetAt(0);
		xwb.getSheetAt(1);
		// 定义 row、cell
		XSSFRow row;
		String cell;
		// 循环输出表格中的内容
		Field[] fields = t.getClass().getDeclaredFields();
		for (int i = sheet.getFirstRowNum(); i < sheet.getPhysicalNumberOfRows(); i++) {
			Object o = t.getClass().newInstance();
			row = sheet.getRow(i);
			for (int j = row.getFirstCellNum(); j < row.getPhysicalNumberOfCells(); j++) {
				cell = row.getCell(j).toString();//获取单元格内容，
				//System.out.println(fields[j].getName()+" = "+fields[j].getType().getTypeName());
				fields[j].setAccessible(true);
				if("java.lang.Integer".equals(fields[j].getType().getTypeName())){
					fields[j].set(o, Integer.parseInt(cell.split("\\.")[0]));
				}else if("java.util.Date".equals(fields[j].getType().getTypeName())){
					fields[j].set(o, new Date());
				}else if("java.lang.Double".equals(fields[j].getType().getTypeName())){
					fields[j].set(o, Double.parseDouble(cell.split("\\.")[0]));
				}else if("java.lang.Boolean".equals(fields[j].getType().getTypeName())){
					fields[j].set(o, Boolean.parseBoolean(cell.split("\\.")[0]));
				}else{
					fields[j].set(o, (String)cell);
				}
			}
			list.add(o);
			System.out.println(o);
		}
		return list;
	}
	
	/**
	 * 王家明 ： 获取excel数据 返回对象集合
	 * @param file
	 * @param t
	 * @return
	 * @throws Exception 
	 */
	public List<Object>  getExcelData(File file,T t) throws Exception{
		if(this.isExcel2003(file.getName())){
			//this.loadXls(file);
		}
		if(this.isExcel2007(file.getName())){
			return this.loadXlsx(file,t);
		}
		return null;
	}
	/**
	 * 王家明   导出excel
	 * @param resp
	 * @throws Exception
	 */
	public static void  exportExcel(HttpServletResponse resp, List<Rp_qrcode> list, String fileName) throws Exception{
		// 生成提示信息，
		resp.setContentType("application/vnd.ms-excel");
		// 进行转码，使其支持中文文件名
		String codedFileName = java.net.URLEncoder.encode(fileName+"表格", "UTF-8");
		resp.setHeader("content-disposition", "attachment;filename=" + codedFileName + ".xlsx");
		XSSFWorkbook wb = new XSSFWorkbook();// 创建Excel工作簿对象
		XSSFSheet sheet = wb.createSheet("第一个页签工作簿");// 创建Excel工作表对象

		Field[] fields = null;

		XSSFCellStyle my_style = wb.createCellStyle();
		my_style.setFillPattern(XSSFCellStyle.FINE_DOTS );
		my_style.setFillForegroundColor(IndexedColors.RED.getIndex());
		my_style.setFillBackgroundColor(IndexedColors.GREY_40_PERCENT.getIndex());

		for(int i = 0; i < list.size()+1; i++){
			XSSFRow row = sheet.createRow((int)(i));// 创建一行

			if(i==0){
				fields = list.get(i).getClass().getDeclaredFields();
				for(int j = 0; j < fields.length-1; j++){

					XSSFCell cell = row.createCell(j);
					fields[j].setAccessible(true);
					cell.setCellStyle(my_style);
					cell.setCellValue(fields[j].getName());
					sheet.autoSizeColumn(j);
				}
				continue;
			}
			fields = list.get(i-1).getClass().getDeclaredFields();
			for(int j = 0; j < fields.length-1; j++){

				XSSFCell cell = row.createCell(j);
				fields[j].setAccessible(true);
				cell.setCellValue(""+fields[j].get(list.get(i-1)));/*fields[j].getName() + " = " +*/
				sheet.autoSizeColumn(j);
			}
		}
		try {
			wb.write(resp.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}finally {

		}
	}

	/**
	 * 读取office 2003 xls
	 * @param filePath
	 */
//	@SuppressWarnings({ "deprecation", "rawtypes" })
//	public void loadXls(/*String filePath*/MultipartFile file) {
//		try {
//			//InputStream input = new FileInputStream("D://test.xls");
//			POIFSFileSystem fs = new POIFSFileSystem(file.getInputStream());
//			HSSFWorkbook wb = new HSSFWorkbook(fs);
//			HSSFSheet sheet = wb.getSheetAt(0);
//			// Iterate over each row in the sheet
//			Iterator rows = sheet.rowIterator();
//			while (rows.hasNext()) {
//				HSSFRow row = (HSSFRow) rows.next();
//				System.out.println("Row #" + row.getRowNum());
//				// Iterate over each cell in the row and print out the cell"s
//				// content
//				Iterator cells = row.cellIterator();
//				while (cells.hasNext()) {
//					HSSFCell cell = (HSSFCell) cells.next();
//					System.out.println("Cell #" + cell.getCellNum());
//					switch (cell.getCellType()) {
//					case HSSFCell.CELL_TYPE_NUMERIC:
//						System.out.println(cell.getNumericCellValue());
//						break;
//					case HSSFCell.CELL_TYPE_STRING:
//						System.out.println(cell.getStringCellValue());
//						break;
//					case HSSFCell.CELL_TYPE_BOOLEAN:
//						System.out.println(cell.getBooleanCellValue());
//						break;
//					case HSSFCell.CELL_TYPE_FORMULA:
//						System.out.println(cell.getCellFormula());
//						break;
//					default:
//						System.out.println("unsuported sell type");
//						break;
//					}
//				}
//			}
//		} catch (IOException ex) {
//			ex.printStackTrace();
//		}
//	}
}