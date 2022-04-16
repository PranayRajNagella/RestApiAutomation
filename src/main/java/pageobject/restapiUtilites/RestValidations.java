package pageobject.restapiUtilites;

import io.restassured.response.Response;
import pageobject.factory.Factory;

import java.io.File;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.MatcherAssert.assertThat;

public class RestValidations{

	/**
	 * To Validate StatusCode
	 * @param response
	 * @param statusCodeExpected
	 * @return
	 */
	public static boolean StatusCodeValidation(Response response,int statusCodeExpected)
	{
		boolean flag=false;
		if(response.getStatusCode()==statusCodeExpected)
		{
			flag=true;
			Factory.getFactory().getExtentObject().pass("Actual Status Code is "+response.getStatusCode()+" Expected Status Code "+statusCodeExpected);
		}
		else
		{

			Factory.getFactory().getExtentObject().fail("Actual Status Code is "+response.getStatusCode()+" Expected Status Code "+statusCodeExpected);
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
			Factory.getFactory().getExtentObject().pass("the given string contains the value expected "+matchText);
		}
		else
		{
			Factory.getFactory().getExtentObject().fail("the given string dose not contains the value expected "+matchText);
		}
		return flag;
	}

	/**
	 * To validate status line.
	 * @param response
	 * @param statusLine
	 * @return
	 */
	public static boolean checkStatusLine(Response response,String statusLine) {
		boolean flag = false;
		switch (response.getStatusCode()) {
			case (200):
			case (201):
			case (400):
				flag = response.toString().length() > 0 && response.getStatusLine().equalsIgnoreCase(statusLine);
				break;
			case (204):
				flag = response.toString().length() == 0 && response.getStatusLine().equalsIgnoreCase(statusLine);
				break;
			default:
				Factory.getFactory().getExtentObject().fail("The Staus line you looking is not expected");
				break;
		}

		if (flag) {
			Factory.getFactory().getExtentObject().pass("Expected status line is " + response.getStatusLine() + " and is matching with " + statusLine);
		} else {
			Factory.getFactory().getExtentObject().fail("Expected status line is " + response.getStatusLine() + " and is not matching with " + statusLine);
		}
		return flag;
	}


    /**
     * to validate JsonSchema From different sources
     * @param response
     * @param JsonSchemaName
     * @param type (i.e file location,URL)
     * @return
     */
	public static boolean validateJsonSchema(String response,String JsonSchemaName,String type)
	{
        boolean result=true;
        try {
            System.out.println(JsonSchemaName);
            switch (type.toUpperCase()) {
                case "FILE":
                    assertThat(response, matchesJsonSchema(new File(JsonSchemaName)));
                    System.out.println("Matched the Json schema");
                    break;
                case "URL":
                    assertThat(response, matchesJsonSchema(JsonSchemaName));
                    break;
                default:
                    System.out.println("please pass any on the above validator types");
                    result=false;
                    break;
            }
        }
        catch (Throwable e)
        {
            System.out.println(e.toString());
            result=false;
        }
        return  result;
	}










	
	

}
