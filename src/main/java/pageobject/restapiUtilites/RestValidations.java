package pageobject.restapiUtilites;

import org.testng.Assert;
import com.aventstack.extentreports.ExtentTest;
import io.restassured.response.Response;
import pageobject.factory.Factory;

public class RestValidations{

	/**
	 * To Validate StatusCode
	 * @param response
	 * @param statusCodeExpected
	 * @param extentTest
	 * @return
	 */
	public boolean StatusCodeValidation(Response response,int statusCodeExpected, ExtentTest extentTest)
	{
		boolean flag=false;
		if(response.getStatusCode()==statusCodeExpected)
		{
			flag=true;
			Factory.getFactory().getReporter().pass("Actual Status Code is "+response.getStatusCode()+" Expected Status Code "+statusCodeExpected);
		}
		else
		{

			Factory.getFactory().getReporter().fail("Actual Status Code is "+response.getStatusCode()+" Expected Status Code "+statusCodeExpected);
		}
		return flag;
	}

	/**
	 * To Validate a response contains text.
	 * @param response
	 * @param matchText
	 * @return
	 */
	public static boolean checkContains(Response response, String matchText)
	{
		boolean flag=false;
		if(response.toString().contains(matchText))
		{
			flag=true;
			Factory.getFactory().getReporter().pass("the given string contains the value expected "+matchText);
		}
		else
		{
			Factory.getFactory().getReporter().fail("the given string dose not contains the value expected "+matchText);
		}
		return flag;
	}

	/**
	 * To validate status line.
	 * @param response
	 * @param statusLine
	 * @return
	 */
	public static boolean checkStatusLine(Response response,String statusLine)
	{
		boolean flag=false;
		switch (response.getStatusCode())
		{
			case(200):
			case(201):
			case(400):
				flag=response.toString().length()>0 && response.getStatusLine().equalsIgnoreCase(statusLine);
				break;
			case(204):
				flag=response.toString().length()==0 && response.getStatusLine().equalsIgnoreCase(statusLine);
				break;
			default:
				Factory.getFactory().getReporter().fail("The Staus line you looking is not expected");
				break;
		}

		if(flag) {
			Factory.getFactory().getReporter().pass("Expected status line is " + response.getStatusLine()+" and is matching with "+statusLine);
		}
		else
		{
			Factory.getFactory().getReporter().fail("Expected status line is " + response.getStatusLine()+" and is not matching with "+statusLine);
		}
		return flag;
	}






	
	

}
