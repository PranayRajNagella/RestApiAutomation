package pageobject.restapiUtilites;

import java.io.IOException;
import org.apache.poi.ss.usermodel.Sheet;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import pageobject.DataAndPropertyUtils.ExcelDataUtil;
import pageobject.DataAndPropertyUtils.ExtentReportsUtils;
import pageobject.DataAndPropertyUtils.JsonDataUtil;
import pageobject.DataAndPropertyUtils.PropertyReadWriteUtils;

public class BaseTest extends RestBase{

	protected  ExcelDataUtil EDU;
	protected  Sheet sheet;
	protected JsonDataUtil JSD;
	protected PropertyReadWriteUtils Property;
	protected ExtentTest test;
	protected ExtentReports report;
	protected ExtentReportsUtils extentReport;
	
	@BeforeSuite(groups={"ApiTest","Regression","Sanity"})
	public void setUpUtils() throws Exception
	{
		
		EDU=new ExcelDataUtil();
		JSD=new JsonDataUtil();
		extentReport=new ExtentReportsUtils();
		report=extentReport.setUpExtent();
		sheet=EDU.getSheetFromExcel(System.getProperty("user.dir")+"\\src\\test\\Resources\\TestData (3).xlsx","TestData");
		Property=new PropertyReadWriteUtils(System.getProperty("user.dir")+"\\src\\test\\Resources\\constant.properties");
	}
	
	@AfterSuite(groups={"ApiTest","Regression","Sanity"})
	public void BreakDown() throws IOException
	{
		EDU.closeWorkBook();
	}
}
