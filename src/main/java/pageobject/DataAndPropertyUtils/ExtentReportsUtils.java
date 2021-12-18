package pageobject.DataAndPropertyUtils;

import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import pageobject.restapiUtilites.BaseTest;

public class ExtentReportsUtils{
	
	ExtentReports extent = new ExtentReports();
	
	public ExtentReports setUpExtent()
	{
		ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir")+"\\src\\test\\Resources\\Results.html");
		extent.attachReporter(spark);
		return extent;
	}
	
	public void extentStatusUpdate(ITestResult result,ExtentTest test)
	{
		switch (result.getStatus()) {
		case 1:
			test.pass(result.getName()+" is passed");
			break;
		case 2:
			test.fail(result.getName()+" is falied due to following reason");
			test.fail(result.getThrowable());
			break;
		case 3:
			test.skip(result.getTestName()+" got Skipped");
			break;
		default:
			break;
		}
	}
	
	public void endReport()
	{
		extent.flush();
		//extent.
	}

}
