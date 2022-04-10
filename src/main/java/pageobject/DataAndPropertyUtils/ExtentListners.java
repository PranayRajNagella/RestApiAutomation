package pageobject.DataAndPropertyUtils;

import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import pageobject.factory.Factory;
import pageobject.restapiUtilites.BaseTest;

public class ExtentListners extends BaseTest implements ITestListener{
    private ExtentTest test;

    @Override
	public void onTestStart(ITestResult result) {
		test=report.createTest(result.getName());
		Factory.getFactory().setExtentObject(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stu
	}

}
