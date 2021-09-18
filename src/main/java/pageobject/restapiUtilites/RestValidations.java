package pageobject.restapiUtilites;

import org.testng.Assert;

import io.restassured.response.Response;

public class RestValidations {

	public static void StatusCodeValidation(Response response,int statusCodeExpected)
	{
		Assert.assertEquals(response.getStatusCode(), statusCodeExpected,"Expected status code is"+statusCodeExpected+" Actuall status code returned"+response.getStatusCode());
	}

}
