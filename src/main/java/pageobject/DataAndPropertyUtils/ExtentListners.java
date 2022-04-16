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
		Factory.getFactory().getExtentObject().pass(result.getMethod().getMethodName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		Factory.getFactory().getExtentObject().fail(result.getMethod().getMethodName());
		Factory.getFactory().getExtentObject().fail(result.getMethod().getMethodName()+" failed due to reason "+result.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		Factory.getFactory().getExtentObject().skip(result.getMethod().getMethodName());
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
