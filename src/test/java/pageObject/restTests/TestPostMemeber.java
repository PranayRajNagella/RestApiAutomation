package pageObject.restTests;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import io.restassured.response.Response;
import pageobject.DataAndPropertyUtils.ExtentReportsUtils;
import pageobject.DataAndPropertyUtils.TestCaseName;
import pageobject.DataAndPropertyUtils.TestDataProvider;
import pageobject.factory.Factory;
import pageobject.restapiUtilites.BaseTest;

public class TestPostMemeber extends BaseTest {
	
	public Map<String,Object> pathParams=new HashMap<String,Object>();
	public Map<String,Object> queryParams=new HashMap<String,Object>();
	public Map<String,Object> headers=new HashMap<String,Object>();
	Response Response;
	
	
	@DataProvider(name="Testdata")
	public  Iterator<Object> getTestData(Method M) throws Exception
	{
		ArrayList<Object> list=new ArrayList<>();
		list=JSD.getJsonData(getClass().getSimpleName(),TestDataProvider.getMethodName(M));
	    return  list.iterator();
	}
	
	@BeforeMethod(groups={"ApiTest","Regression","Sanity"})
	public void BeforMethod()
	{
		pathParams.clear();
		queryParams.clear();
		headers.clear();
	}
	
	
	@AfterMethod(groups={"ApiTest","Regression","Sanity"})
	public void AfterMethod(ITestResult result)
	{
		ExtentReportsUtils.extentStatusUpdate(result,Factory.getFactory().getReporter());
		ExtentReportsUtils.endReport();
	}
	
	
	@Test(dataProvider="Testdata")
	public void test_01_postnewmember(Map<String,Object> testData)
	{

	    try
		{
			Factory.getFactory().setReporter(report.createTest("test_01_postnewmember", " verying test_01_postnewmember"));
			Factory.getFactory().getReporter().info("inside the post test_01");
		System.out.println("TestData for the new members");
		System.out.println(testData);
		}
		catch (Exception e) {
			Factory.getFactory().getReporter().fail(e);
			Assert.fail(e.toString());
		}
	}
	
	@Test(dataProvider="Testdata")
	public void test_02_postExsitingmember(Map<String,Object> testData)
	{

		try
		{
			Factory.getFactory().setReporter(report.createTest("test_02_postExsitingmember", " verying test_02_postExsitingmember"));
			Factory.getFactory().getReporter().info("inside the post test_02");
			System.out.println("TestData for the existing members");
			System.out.println(testData);
		}
		catch (Exception e) {
			 Factory.getFactory().getReporter().fail(e);
			 Assert.fail(e.toString());
		}
	}

}
