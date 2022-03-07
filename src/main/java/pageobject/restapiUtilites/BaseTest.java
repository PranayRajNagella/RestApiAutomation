package pageobject.restapiUtilites;

import java.io.IOException;
import org.apache.poi.ss.usermodel.Sheet;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentReporter;

import pageobject.DataAndPropertyUtils.ExcelDataUtil;
import pageobject.DataAndPropertyUtils.ExtentReportsUtils;
import pageobject.DataAndPropertyUtils.JsonDataUtil;
import pageobject.DataAndPropertyUtils.PropertyReadWriteUtils;
import pageobject.factory.Factory;
import pageobject.retryAnalyser.RetryTestCases;

public class BaseTest extends RestBase{

	protected static ExcelDataUtil EDU;
	protected static Sheet sheet;
	protected static JsonDataUtil JSD;
	protected static PropertyReadWriteUtils Property;
	public static ExtentReports report;
	protected static RestValidations restValidater;
	protected static ExtentTest test;
	protected RetryTestCases retry;

	@BeforeSuite(groups={"ApiTest","Regression","Sanity"})
	public void setUpUtils(ITestContext context) throws Exception
	{
		for (ITestNGMethod method : context.getAllTestMethods()) {
			method.setRetryAnalyzer(new RetryTestCases());
		}
		EDU=new ExcelDataUtil();
		JSD=new JsonDataUtil();
		report=ExtentReportsUtils.setUpExtent();
		restValidater=new RestValidations();
		retry=new RetryTestCases();
		sheet=EDU.getSheetFromExcel(System.getProperty("user.dir")+"\\src\\test\\Resources\\TestData (3).xlsx","TestData");
		Property=new PropertyReadWriteUtils(System.getProperty("user.dir")+"\\src\\test\\Resources\\constant.properties");
	}
	
	@AfterSuite(groups={"ApiTest","Regression","Sanity"})
	public void BreakDown() throws IOException
	{
		EDU.closeWorkBook();
	}
}
