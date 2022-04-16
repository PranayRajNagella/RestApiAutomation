package pageObject.restTests;

import com.aventstack.extentreports.ExtentTest;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pageobject.DataAndPropertyUtils.ExtentReportsUtils;
import pageobject.DataAndPropertyUtils.TestResultUtility;
import pageobject.DataAndPropertyUtils.Utility;
import pageobject.factory.Factory;
import pageobject.factory.RestFactory;
import pageobject.restapiUtilites.BaseTest;
import pageobject.restapiUtilites.RestBase;
import pageobject.restapiUtilites.RestValidations;

import java.lang.reflect.Method;
import java.util.*;


public class GetCustomTest extends BaseTest {

    Map<String, Object> headers = new HashMap<String, Object>();
    Map<String, Object> pathParameters = new HashMap<String, Object>();
    Map<String, Object> queryParameters = new HashMap<String, Object>();
    RequestSpecification respec;
    List<String> passedTestCase=new ArrayList<String>();
    List<String> failedTestCase=new ArrayList<String>();
    private ExtentTest test;


    @DataProvider(name="data")
    public Iterator<Object>  getTestData(Method M) throws Exception {
      ArrayList<Object> Testdata=JSD.getJsonData("GetCustomTest",M.getName());
      return Testdata.iterator();
    }

    @BeforeClass(alwaysRun = true)
    public void beforeClass() throws Exception {
        respec =  requestSpecification(Property.readPropetyValue("BaseURI"));
        headers.put("Content-Type","application/json");
        setHeaders(respec,headers);
        RestFactory.getRestFactory().setRestBase(new RestBase());
    }

    @AfterClass(alwaysRun = true)
    public void afterClass()
    {
        RestFactory.getRestFactory().removeResBase();
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod()
    {
        pathParameters.clear();
        queryParameters.clear();
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult result,Method M)
    {
        passedTestCase.clear();
        failedTestCase.clear();
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

    @Test(dataProvider = "data")
    public void test_01_GetMemeber(Map<String,String> jsonData)
    {
        boolean result=true;
        try {
            passedTestCase= TestResultUtility.setResults("MTB-3001,MTB-4001");
            test=report.createTest("test_01_GetMemeber").assignCategory("getMembers");
            Factory.getFactory().setExtentObject(test);
            Factory.getFactory().getExtentObject().info("test info");
            System.out.println(jsonData);
            Response response=RestFactory.getRestFactory().getRestBase().httpGet(respec,Property.readPropetyValue("GetMembers"), pathParameters, queryParameters);
            if(!RestValidations.StatusCodeValidation(response,200))
            {
                result=false;
                passedTestCase.remove("MTB-3001");
                failedTestCase.add("MTB-3001");
            }

            if(!RestValidations.checkStatusLine(response,"HTTP/1.1 200 OK"))
            {
                result=false;
                passedTestCase.remove("MTB-4001");
                failedTestCase.add("MTB-4001");
            }
            TestResultUtility.writePassedTestResult(passedTestCase);
            TestResultUtility.writeFailedTestResult(failedTestCase);
            Assert.assertTrue(result,"get Custom did not pass validation did not pass");
        }
        catch (Exception e)
        {
            TestResultUtility.writeFailedTestResult(failedTestCase);
            e.getStackTrace();
            Factory.getFactory().getExtentObject().fail(e);
            Assert.fail(e.getMessage());
        }
    }

}
