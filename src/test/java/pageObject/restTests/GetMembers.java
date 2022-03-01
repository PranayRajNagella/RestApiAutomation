package pageObject.restTests;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
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


public class GetMembers extends BaseTest {
	
	public Map<String,Object> pathParams=new HashMap<String,Object>();
	public Map<String,Object> queryParams=new HashMap<String,Object>();
	public Map<String,Object> headers=new HashMap<String,Object>();
	Response Response;
	private ExtentTest test;
	
	@DataProvider(name="TestData")
	public Iterator<Object> getTestData(Method M) throws Exception
	{
		System.out.println("test Info " +M.getName());
		System.out.println(this.getClass().getSimpleName()+" Hello");
		ArrayList<Object> datalist=JSD.getJsonData(this.getClass().getSimpleName(),TestDataProvider.getMethodName(M));
		return datalist.listIterator();
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
	
	
	@Test(dataProvider="TestData",groups={"ApiTest","Regression","Sanity"})
	public void test_01_GetMembers_ValidInput(Map<String,Object> datamap) throws Exception
	{
		System.out.println("Above Create Test");
		try
		{
		
			Factory.getFactory().setReporter(report.createTest("test_01_GetMembers_ValidInput", " verying get valid input"));   
			Factory.getFactory().getReporter().info(datamap.toString());
			System.out.println(datamap);
			Response=httpGet(Property.readPropetyValue("BaseURI"),Property.readPropetyValue("GetMembers"), headers, pathParams, queryParams);
			restValidater.StatusCodeValidation(Response, 200,Factory.getFactory().getReporter());
			System.out.println("Test INfo added");
		
		}
		catch (Exception e) {
			e.getStackTrace();
			Factory.getFactory().getReporter().fail(e);
			Assert.fail(e.toString());
		}
	}
	
	
	@Test(dataProvider="TestData",groups={"ApiTest","Regression","Sanity"})
	@TestCaseName(testCaseName="test_01_GetMembers_ValidInput")
	public void test_02_GetMembers_ValidInput(Map<String,Object> datamap) throws Exception
	{
		System.out.println("Above Create Test");
		try
	{
		
			Factory.getFactory().setReporter(report.createTest("test_02_GetMembers_ValidInput", " verying get valid input"));   
			Factory.getFactory().getReporter().info(datamap.toString());
			System.out.println(datamap);
			Response=httpGet(Property.readPropetyValue("BaseURI"),Property.readPropetyValue("GetMembers"), headers, pathParams, queryParams);
			System.err.println(Response.asString()+" Priting erro");
			restValidater.StatusCodeValidation(Response, 200,Factory.getFactory().getReporter());
			System.out.println("Test INfo added");
		
		}
		catch (Exception e) {
			e.getStackTrace();
			Factory.getFactory().getReporter().fail(e);
			Assert.fail(e.toString());
		}
	}

}
