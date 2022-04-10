package pageobject.DataAndPropertyUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestResult;
import pageobject.factory.Factory;

public class ExtentReportsUtils{
	
	static ExtentReports extent ;
	
	public static ExtentReports setUpExtent()
	{
		extent=new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir")+"\\src\\test\\Resources\\Results.html");
		extent.attachReporter(spark);
		extent.setSystemInfo("userName", System.getProperty("user.name"));
		extent.setSystemInfo("OS", System.getProperty("os.version"));
		return extent;
	}
	
	
	public static void extentStatusUpdate(ITestResult result,ExtentTest test)
	{
		switch (result.getStatus()) {
		case 1:
			Factory.getFactory().getExtentObject().pass(result.getName()+" is passed");
			Factory.getFactory().removeReport();
			break;
		case 2:
			Factory.getFactory().getExtentObject().fail(result.getName()+" is failed due to following reason");
			Factory.getFactory().getExtentObject().fail(result.getThrowable());
			Factory.getFactory().removeReport();
			break;
		case 3:
			Factory.getFactory().getExtentObject().skip(result.getName()+" got Skipped");
			break;
		default:
			break;
		}
	}
	
	public static void endReport()
	{
		extent.flush();
	}

}
