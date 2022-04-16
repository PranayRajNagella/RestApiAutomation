package pageObject.restTests;

import com.aventstack.extentreports.ExtentTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;
import pageobject.DataAndPropertyUtils.*;
import pageobject.factory.Factory;
import pageobject.factory.RestFactory;
import pageobject.restapiUtilites.BaseTest;
import pageobject.restapiUtilites.RestBase;
import pageobject.restapiUtilites.RestValidations;

import java.lang.reflect.Method;
import java.util.*;

public class GetMembers extends BaseTest {
	
	public Map<String,Object> pathParams=new HashMap<String,Object>();
	public Map<String,Object> queryParams=new HashMap<String,Object>();
	public Map<String,Object> headers=new HashMap<String,Object>();
	Response Response;
	List<String> passedResults=new ArrayList<>();
	List<String> failedResults =new ArrayList<>();
	private ExtentTest test;

	@DataProvider(name="TestData")
	public Iterator<Object> getTestData(Method M) throws Exception
	{
		ArrayList<Object> datalist=JSD.getJsonData(this.getClass().getSimpleName(),TestDataProvider.getMethodName(M));
		return datalist.listIterator();
	}

	@BeforeClass()
	public void beforeClass()
	{
		RestFactory.getRestFactory().setRestBase(new RestBase());
	}


	@BeforeMethod(groups={"ApiTest","Regression","Sanity"},alwaysRun = true)
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
	 * MTB-1456  MTB-1457
	 * @param datamap
	 * @throws Exception
	 */
	@Test(dataProvider="TestData",groups={"ApiTest","Regression","Sanity"})
	public void test_01_GetMembers_ValidInput(Map<String,Object> datamap) throws Exception
	{
		boolean result=true;
		passedResults=TestResultUtility.setResults("MTB-1456,MTB-1457");
		try
		{
			test=report.createTest("test_01_GetMembers_ValidInput for "+datamap.get("page").toString(), "MTB-1456 -verify get member Status Code for valid input" +
					"<div> MTB-1457 -verify get member Status line for valid input").assignCategory("GET MEMBERS");
			Factory.getFactory().setExtentObject(test);
			Factory.getFactory().getExtentObject().info(datamap.toString());
			Response=RestFactory.getRestFactory().getRestBase().httpGet(Property.readPropetyValue("BaseURI"),Property.readPropetyValue("GetMembers"), headers, pathParams, queryParams);
			if(!RestValidations.StatusCodeValidation(Response, 200))
		//	if(Integer.parseInt(datamap.get("page").toString())==220)
			{
				passedResults.remove("MTB-1456");
				failedResults.add("MTB-1456");
				result=false;
			}

			if(!RestValidations.checkStatusLine(Response,"HTTP/1.1 200 OK"))
			{
				Reporter.log(passedResults+" before removing");
				result=false;
				passedResults.remove("MTB-1457");
				failedResults.add("MTB-1457");
			}
			Factory.getFactory().getExtentObject().info("passed Test Cases "+passedResults +"<div> failed Test Cases is "+ failedResults);
			TestResultUtility.writePassedTestResult(passedResults);
			TestResultUtility.writeFailedTestResult(failedResults);
			Assert.assertTrue(result,"Failed GetMember Valid Input");
		}
		catch (Exception e) {
			TestResultUtility.writeFailedTestResult(TestResultUtility.setResults("MTB-1456,MTB-1457"));
			System.out.println("prtinting stack tarce");
			e.printStackTrace();
			Factory.getFactory().getExtentObject().fail(e);
			Assert.fail(e.toString());
		}
	}

	/**
	 * MTB-1458  MTB-1459
	 * @param datamap
	 * @throws Exception
	 */
	@Test(dataProvider="TestData",groups={"ApiTest","Regression","Sanity"})
	@TestCaseName(testCaseName="test_01_GetMembers_ValidInput")
	public void test_02_GetMembers_ValidInput(Map<String,Object> datamap) throws Exception
	{
		boolean result=true;
		passedResults=TestResultUtility.setResults("MTB-1458,MTB-1459");
		try
		{
			test=report.createTest("test_02_GetMembers_ValidInput for "+datamap.get("page").toString(), " verifying get valid input").assignCategory("GET MEMBERS");
			Factory.getFactory().setExtentObject(test);
			Factory.getFactory().getExtentObject().info(datamap.toString());
			Response=RestFactory.getRestFactory().getRestBase().httpGet(Property.readPropetyValue("BaseURI"),Property.readPropetyValue("GetMembers"), headers, pathParams, queryParams);
			if(!RestValidations.StatusCodeValidation(Response, 200))
		//		if(Integer.parseInt(datamap.get("page").toString())==220 && (retry.getTriedCount()==0 || retry.getTriedCount()==1))
			{
				System.err.println("Failing the test Cases");
				result=false;
				passedResults.remove("MTB-1458");
				failedResults.add("MTB-1458");
			}
			else
			{
				System.err.println("test_02_GetMembers_ValidInput for "+datamap.get("page").toString());
				System.err.println(retry.getTriedCount());
			}

		if(!RestValidations.checkStatusLine(Response,"HTTP/1.1 200 OK"))
		{
			result=false;
			passedResults.remove("MTB-1459");
			failedResults.add("MTB-1459");
		}
		Factory.getFactory().getExtentObject().info("passed Test Cases "+passedResults +"<div> failed Test Cases is "+ failedResults);
		TestResultUtility.writePassedTestResult(passedResults);
		TestResultUtility.writeFailedTestResult(failedResults);
		Assert.assertTrue(result,"Failed GetMember Valid Input");
		}
		catch (Exception e) {
			TestResultUtility.writeFailedTestResult(TestResultUtility.setResults("MTB-1458,MTB-1459"));
			e.printStackTrace();
			Factory.getFactory().getExtentObject().fail(e);
			Assert.fail(e.toString());
		}
	}

}
