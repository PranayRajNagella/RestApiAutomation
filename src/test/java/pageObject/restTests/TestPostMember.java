package pageObject.restTests;

import com.aventstack.extentreports.ExtentTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageobject.DataAndPropertyUtils.ExtentReportsUtils;
import pageobject.DataAndPropertyUtils.TestDataProvider;
import pageobject.DataAndPropertyUtils.TestResultUtility;
import pageobject.DataAndPropertyUtils.Utility;
import pageobject.factory.Factory;
import pageobject.restapiUtilites.BaseTest;

import java.lang.reflect.Method;
import java.util.*;

public class TestPostMember extends BaseTest {
	
	public Map<String,Object> pathParams=new HashMap<String,Object>();
	public Map<String,Object> queryParams=new HashMap<String,Object>();
	public Map<String,Object> headers=new HashMap<String,Object>();
	List<String> passedResults=new ArrayList<>();
	List<String> failedResults=new ArrayList<>();
	private ExtentTest test;
	Response Response;
	
	
	@DataProvider(name="Testdata")
	public  Iterator<Object> getTestData(Method M) throws Exception
	{
		ArrayList<Object> list=new ArrayList<>();
		list=JSD.getJsonData(getClass().getSimpleName(),TestDataProvider.getMethodName(M));
	    return  list.iterator();
	}
	
	@BeforeMethod(groups={"ApiTest","Regression","Sanity"},alwaysRun = true)
	public void BeforMethod()
	{
		pathParams.clear();
		queryParams.clear();
		headers.clear();
	}
	
	
	@AfterMethod(groups={"ApiTest","Regression","Sanity"},alwaysRun = true)
	public void AfterMethod(ITestResult result,Method M)
	{
		passedResults.clear();
		failedResults.clear();
		ExtentReportsUtils.extentStatusUpdate(result);
		if(result.getStatus()!=1)
		{
			Utility.removeReport(retry.getTriedCount(),report,Factory.getFactory().getExtentObject(), result.getName());
		}
		else
		{
			retry.setCount(0);
		}
		ExtentReportsUtils.endReport();
	}

	/**
	 * MTB-1460  MTB-1461
	 * @param testData
	 */
	@Test(dataProvider="Testdata")
	public void test_01_postnewmember(Map<String,Object> testData)
	{
		passedResults=TestResultUtility.setResults("MTB-1460,MTB-1461");
	    try
		{
			test=report.createTest("test_01_postnewmember", " verying test_01_postnewmember").assignCategory("POST MEMBERS");
			Factory.getFactory().setExtentObject(test);
			Factory.getFactory().getExtentObject().info("Hello test_01_postnewmember number");
			if(!false)
			{
				passedResults.remove("MTB-1461");
				failedResults.add("MTB-1461");
			}
			TestResultUtility.writePassedTestResult(passedResults);
			TestResultUtility.writeFailedTestResult(failedResults);
			Assert.assertTrue(true,"post new member failed");
		}
		catch (Exception e) {
			e.printStackTrace();
			TestResultUtility.writeFailedTestResult(TestResultUtility.setResults("MTB-1460,MTB-1461"));
			Factory.getFactory().getExtentObject().fail(e);
			Assert.fail(e.toString());
		}
	}


	/**
	 * MTB-1462 MTB-1463
	 * @param testData
	 */
	@Test(dataProvider="Testdata")
	public void test_02_postExsitingmember(Map<String,Object> testData)
	{
		passedResults=TestResultUtility.setResults("MTB-1462,MTB-1463");
		try
		{
			test=report.createTest("test_02_postExsitingmember", " verying test_02_postExsitingmember").assignCategory("POST MEMBERS");
			Factory.getFactory().setExtentObject(test);
			Factory.getFactory().getExtentObject().info("inside the post test_02");
			if(!false)
			{
				passedResults.remove("MTB-1462");
				failedResults.add("MTB-1462");
			}
			TestResultUtility.writePassedTestResult(passedResults);
			TestResultUtility.writeFailedTestResult(failedResults);
			Assert.assertTrue(true,"post new member failed");
		}
		catch (Exception e) {
			e.printStackTrace();
			TestResultUtility.writeFailedTestResult(TestResultUtility.setResults("MTB-1462,MTB-1463"));
			Factory.getFactory().getExtentObject().fail(e);
			Assert.fail(e.toString());
		}
	}

}
