package pageObject.restTests;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import pageobject.restapiUtilites.BaseTest;
import pageobject.restapiUtilites.RestValidations;

public class GetMembers extends BaseTest {
	
	public Map<String,Object> pathParams=new HashMap<String,Object>();
	public Map<String,Object> queryParams=new HashMap<String,Object>();
	public Map<String,Object> headers=new HashMap<String,Object>();
	Response Response;
	
	@DataProvider(name="TestData")
	public Iterator<Object> getTestData(Method M) throws Exception
	{
		ArrayList<Object> datalist=JSD.getJosnData(this.getClass().getSimpleName(), M.getName());
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
		extentReport.extentStatusUpdate(result,test);
		extentReport.endReport();
	}
	
	
	@Test(dataProvider="TestData",groups={"ApiTest","Regression","Sanity"})
	public void test_01_GetMembers_ValidInput(Map<String,Object> datamap) throws Exception
	{
		System.out.println("Above Create Test");
		test=report.createTest("test_01_GetMembers_ValidInput", "GetMembers").assignCategory("ApiTest");
		try
		{
			test.info(datamap.toString());
			System.out.println(datamap);
			Response=httpGet(Property.readPropetyValue("BaseURI"),Property.readPropetyValue("GetMembers"), headers, pathParams, queryParams);
			RestValidations.StatusCodeValidation(Response, 200);
		
		}
		catch (Exception e) {
			throw e;
		}
	}

}
