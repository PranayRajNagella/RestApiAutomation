package pageObject.restTests;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import pageobject.DataAndPropertyUtils.ExtentReportsUtils;
import pageobject.DataAndPropertyUtils.TestCaseName;
import pageobject.DataAndPropertyUtils.TestDataProvider;
import pageobject.factory.Factory;
import pageobject.restapiUtilites.BaseTest;
import pageobject.restapiUtilites.RestValidations;



public class TestGetMembers extends BaseTest{
	
	public Map<String,Object> pathParams=new HashMap<String,Object>();
	public Map<String,Object> queryParams=new HashMap<String,Object>();
	public Map<String,Object> headers=new HashMap<String,Object>();
	Response Response;
	
	@BeforeMethod()
	public void BeforMethod()
	{
		pathParams.clear();
		queryParams.clear();
		headers.clear();
	}
	
	
	@AfterMethod()
	public void AfterMethod(ITestResult result)
	{
		ExtentReportsUtils.extentStatusUpdate(result,test);
		ExtentReportsUtils.endReport();
	}
	
	
	@DataProvider(name="TestData")
	public Object[][] getTestData(Method M) throws Exception {
		return EDU.getDataForTestCase(sheet,TestDataProvider.getMethodName(M));
	}
	
	@Test(dataProvider="TestData",groups={"ApiTest","Regression","Sanity"})
	public void test_01_GetMembers(Map<String,Object> data)
	{
		Factory.getFactory().setReporter(report.createTest("test_01_GetMembers", " GetMembers_1 get valid input").assignCategory("GET_MEMBERS"));   
		Factory.getFactory().getReporter().info(data.toString());
		System.out.println("inside the First Test");
		try
		{
			System.out.println(data);
			queryParams.put("page",2);
			Response=httpGet("https://reqres.in/api","users", headers, pathParams, queryParams);
			System.out.println(Response.asString());
			restValidater.StatusCodeValidation(Response, 200,Factory.getFactory().getReporter());
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
		
		Factory.getFactory().setReporter(report.createTest("test_02_GetMembers", " GetMembers_1 get valid input").assignCategory("GET_MEMBERS"));   
		Factory.getFactory().getReporter().info(data.toString());
		System.out.println("Inside the Second Test");
		System.out.println(data);
	}
	
	@Test(dataProvider="TestData",groups={"ApiTest","Regression","Sanity"})
	public void test_03_GetMembers(Map<String,Object> data)
	{
		
		Factory.getFactory().setReporter(report.createTest("test_03_GetMembers", " GetMembers_1 get valid input").assignCategory("GET_MEMBERS"));   
		Factory.getFactory().getReporter().info(data.toString());
		System.out.println("Inside third one Second Test");
		System.out.println(data);
	}

}
