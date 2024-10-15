package com.eoxvantage.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataReader {
	
	static String excelFilePath;
	public ExcelDataReader(String excelFilePath){
		this.excelFilePath = excelFilePath;
	}

	static String path = System.getProperty("user.dir");

	static String dataFilePath = path.concat(excelFilePath);

	public String readExcel(int sheetNum, int rowNum, int columnNum) throws IOException {

		XSSFWorkbook wb = null; // initialize Workbook null

		try {
			// reading data from a file in the form of bytes
			FileInputStream fis = new FileInputStream(new File(dataFilePath));

			// constructs an XSSFWorkbook object, by buffering the whole stream into the
			// memory
			wb = new XSSFWorkbook(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		XSSFSheet sheet = wb.getSheetAt(sheetNum);
		Row row = sheet.getRow(rowNum);
		Cell cell = row.getCell(columnNum);

		String value = "";

		if (cell.getCellType() == CellType.STRING) {
			value = cell.getStringCellValue();
		} else if (cell.getCellType() == CellType.NUMERIC) {
			value = removeCharacters(String.valueOf(cell.getNumericCellValue()));
		}
		wb.close();

		return value;
	}

	private static String removeCharacters(String value) {

		return value.replaceAll("\\.?0*$", "");
	}
}
