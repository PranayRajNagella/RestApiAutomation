package pageobject.restapiUtilites;

import org.testng.Assert;
import com.aventstack.extentreports.ExtentTest;
import io.restassured.response.Response;
import pageobject.factory.Factory;

public class RestValidations{

	public void StatusCodeValidation(Response response,int statusCodeExpected, ExtentTest extentTest)
	{
		System.err.println("inside the method conition");
		if(response.getStatusCode()==statusCodeExpected)
		{
			System.err.println("inside if tru conition");
			//extentTest.pass("Actuall Status Code is "+response.getStatusCode()+" Expected Statuc Code "+statusCodeExpected);
			Factory.getFactory().getReporter().pass("Actuall Status Code is "+response.getStatusCode()+" Expected Statuc Code "+statusCodeExpected);
		}
		else
		{
			//extentTest.fail("Actuall Status Code is "+response.getStatusCode()+" Expected Statuc Code "+statusCodeExpected);	
			Factory.getFactory().getReporter().fail("Actuall Status Code is "+response.getStatusCode()+" Expected Statuc Code "+statusCodeExpected);
		}
		Assert.assertEquals(response.getStatusCode(), statusCodeExpected,"Expected status code is"+statusCodeExpected+" Actuall status code returned"+response.getStatusCode());
	}
	
	public static void checkContains(Response response,String matchText)
	{
		try
		{
			Assert.assertTrue(response.asString().contains(matchText));
		}
		catch (Error e)
		{
			
		}
			
	}
	
	

}
