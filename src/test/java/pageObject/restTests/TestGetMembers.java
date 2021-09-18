package pageObject.restTests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import pageobject.restapiUtilites.RestBase;
import pageobject.restapiUtilites.RestValidations;



public class TestGetMembers extends RestBase{
	
	public Map<String,Object> pathParams=new HashMap<String,Object>();
	public Map<String,Object> queryParams=new HashMap<String,Object>();
	public Map<String,Object> headers=new HashMap<String,Object>();
	Response Response;
	
	@Test()
	public void testGetMethod()
	{
		try
		{
			queryParams.put("page",2);
			Response=httpGet("https://reqres.in/api","users", headers, pathParams, queryParams);
			System.out.println(Response.asString());
			RestValidations.StatusCodeValidation(Response, 200);
			System.out.println(Response.prettyPrint());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
