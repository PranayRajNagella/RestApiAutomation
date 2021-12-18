package pageobject.DataAndPropertyUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataUtil {

	static Workbook book;
	static Sheet sheet;
	
	
	public Sheet getSheetFromExcel(String Path,String SheetName) throws Exception
	{
		try
		{
			String extention=Path.substring(Path.lastIndexOf("."),Path.length());
			File file=new File(Path);
			if(extention.equalsIgnoreCase(".xlsx"))
			{
				book=new XSSFWorkbook(file);	
			}
			else if(extention.equalsIgnoreCase(".xls"))
			{
				book=new HSSFWorkbook(new FileInputStream(file));
			}
			sheet=book.getSheet(SheetName);
		}
		catch (Exception e) {
			throw e;
		}
		return sheet;
	}
	
	public Object[][] getDataForTestCase(Sheet Sheet,String TestCaseName) throws Exception
	{
		Object[][] data=null;
		
		try
		{
		int getNumberOFRows=sheet.getLastRowNum();
		System.out.println(getNumberOFRows+" Number of Rows in the Excel");
		
		int runCountOfTestCase=0;
		List<Integer> runIndexs=new ArrayList<Integer>();
		
		for(int i=0;i<=getNumberOFRows;i++)
		{
			if(TestCaseName.equalsIgnoreCase(this.getColumnData(sheet,"TestCaseName",i)) && 
					this.getColumnData(sheet,"RunMode",i).equalsIgnoreCase("YES"))
			{
				runIndexs.add(i);
				runCountOfTestCase++;
			}
		}
		
		data=new Object[runCountOfTestCase][1];
		int k=0;
		Row HeaderRow=sheet.getRow(0);
		for(int i=0;i<runIndexs.size();i++)
		{
			Row CurrentRow=sheet.getRow(runIndexs.get(i));
			Map<String,Object> mapData=new HashMap <String,Object>();
			for(int j=0;j<CurrentRow.getLastCellNum();j++)
			{
				Cell CurrentCell=CurrentRow.getCell(j);
				switch (CurrentCell.getCellType()) {
				case NUMERIC:
					mapData.put(HeaderRow.getCell(j).getStringCellValue(),CurrentCell.getNumericCellValue());
					break;
				case STRING:
					mapData.put(HeaderRow.getCell(j).getStringCellValue(),CurrentCell.getStringCellValue());
					break;
				case BOOLEAN:
					mapData.put(HeaderRow.getCell(j).getStringCellValue(),CurrentCell.getBooleanCellValue());
					break;
				default:
					break;
				}
			}
			data[k][0]=mapData;
			k++;
		}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			try
			{
				book.close();
			}
			catch (Exception e) {
				throw e;
			}
		}
		return data;		
	}
	
	
	public String getColumnData(Sheet sheet,String columnName,int row)
	{
		Row HeaderRow=sheet.getRow(0);
		int coumn=0;
		for(int i=0;i<HeaderRow.getLastCellNum();i++)
		{
			if(HeaderRow.getCell(i).getStringCellValue().equals(columnName))
			{
				coumn=i;
				break;
			}
		}
		return sheet.getRow(row).getCell(coumn).getStringCellValue();
	}
	
	public void closeWorkBook() throws IOException
	{
		try
		{
			book.close();	
		}
		catch (Exception e) {
			throw e;
		}
	}
	
	
	
	
	
}
