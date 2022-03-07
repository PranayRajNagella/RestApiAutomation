package pageObject.restTests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pageobject.DataAndPropertyUtils.ExtentReportsUtils;
import pageobject.factory.Factory;
import pageobject.restapiUtilites.BaseTest;
import org.testng.Assert;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class GetCustomTest extends BaseTest {

    Map<String, Object> headers = new HashMap<String, Object>();
    Map<String, Object> pathParameters = new HashMap<String, Object>();
    Map<String, Object> queryParameters = new HashMap<String, Object>();
    RequestSpecification respec;



    @DataProvider(name="data")
    public Iterator<Object>  getTestData(Method M) throws Exception {
      ArrayList<Object> Testdata=JSD.getJsonData("GetCustomTest",M.getName());
      return Testdata.iterator();
    }

    @BeforeClass()
    public void beforeClass() throws Exception {
        respec =  requestSpecification(Property.readPropetyValue("BaseURI"));
        headers.put("Content-Type","application/json");
        setHeaders(respec,headers);
    }

    @AfterMethod()
    public void afterMethod(ITestResult result,Method M)
    {
        ExtentReportsUtils.extentStatusUpdate(result,Factory.getFactory().getReporter());
        ExtentReportsUtils.endReport();
    }

    @Test(dataProvider = "data")
    public void test_01_GetMemeber(Map<String,String> jsonData)
    {
        try {
            Factory.getFactory().setReporter(report.createTest("test_01_GetMemeber").assignCategory("getMembers"));
            Factory.getFactory().getReporter().info("test info");
            System.out.println(jsonData);
            httpGet(respec,Property.readPropetyValue("GetMembers"), pathParameters, queryParameters);
        }
        catch (Exception e)
        {
            e.getStackTrace();
            Factory.getFactory().getReporter().fail(e);
            Assert.fail(e.getMessage());
        }

    }

}
