package pageobject.restapiUtilites;

import org.apache.poi.ss.usermodel.Sheet;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import pageobject.DataAndPropertyUtils.ExcelDataUtil;

public class BaseTest extends RestBase{

	protected  ExcelDataUtil EDU;
	protected  Sheet sheet;
	@BeforeSuite()
	public void setUpUtils() throws Exception
	{
		EDU=new ExcelDataUtil();
		sheet=EDU.getSheetFromExcel(System.getProperty("user.dir")+"\\TestData\\TestData (3).xlsx","TestData");
	}
	
	
	@AfterSuite()
	public void BreakDown()
	{
		
	}
}
