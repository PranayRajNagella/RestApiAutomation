package pageObject.restTests;

import io.restassured.response.Response;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pageobject.DataAndPropertyUtils.ExtentReportsUtils;
import pageobject.DataAndPropertyUtils.TestCaseName;
import pageobject.DataAndPropertyUtils.TestDataProvider;
import pageobject.DataAndPropertyUtils.Utility;
import pageobject.factory.Factory;
import pageobject.factory.RestFactory;
import pageobject.restapiUtilites.BaseTest;
import pageobject.restapiUtilites.RestBase;
import pageobject.restapiUtilites.RestValidations;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;



public class TestGetMembers extends BaseTest{
	
	public Map<String,Object> pathParams=new HashMap<String,Object>();
	public Map<String,Object> queryParams=new HashMap<String,Object>();
	public Map<String,Object> headers=new HashMap<String,Object>();
	Response Response;

	@BeforeClass()
	public void beforeClass()
	{
		RestFactory.getRestFactory().setRestBase(new RestBase());
		RestFactory.getRestFactory().getRestBase();
	}

	@BeforeMethod()
	public void BeforMethod()
	{
		pathParams.clear();
		queryParams.clear();
		headers.clear();
	}

	@AfterClass
	public void afterClass()
	{
		RestFactory.getRestFactory().removeResBase();
	}
	
	@AfterMethod()
	public void AfterMethod(ITestResult result)
	{

		ExtentReportsUtils.extentStatusUpdate(result,Factory.getFactory().getExtentObject());
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
	
	
	@DataProvider(name="TestData")
	public Object[][] getTestData(Method M) throws Exception {
		return EDU.getDataForTestCase(sheet,TestDataProvider.getMethodName(M));
	}
	
	@Test(dataProvider="TestData",groups={"ApiTest","Regression","Sanity"})
	public void test_01_GetMembers(Map<String,Object> data)
	{
		Factory.getFactory().setExtentObject(report.createTest("test_01_GetMembers", " GetMembers_1 get valid input").assignCategory("GET_MEMBERS"));
		Factory.getFactory().getExtentObject().info(data.toString());
		System.out.println("inside the First Test");
		try
		{
			System.out.println(data);
			queryParams.put("page",2);
			Response=httpGet("https://reqres.in/api","users", headers, pathParams, queryParams);
			System.out.println(Response.asString());
			RestValidations.StatusCodeValidation(Response, 200);
			System.out.println(Response.prettyPrint());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	@Test(dataProvider="TestData",groups={"ApiTest","Regression","Sanity"})
	@TestCaseName(testCaseName="test_01_GetMembers")
	public void test_02_GetMembers(Map<String,Object> data)
	{
		
		Factory.getFactory().setExtentObject(report.createTest("test_02_GetMembers", " GetMembers_1 get valid input").assignCategory("GET_MEMBERS"));
		Factory.getFactory().getExtentObject().info(data.toString());
		System.out.println("Inside the Second Test");
		System.out.println(data);
	}
	
	@Test(dataProvider="TestData",groups={"ApiTest","Regression","Sanity"})
	public void test_03_GetMembers(Map<String,Object> data)
	{
		
		Factory.getFactory().setExtentObject(report.createTest("test_03_GetMembers", " GetMembers_1 get valid input").assignCategory("GET_MEMBERS"));
		Factory.getFactory().getExtentObject().info(data.toString());
		System.out.println("Inside third one Second Test");
		System.out.println(data);
	}

}
