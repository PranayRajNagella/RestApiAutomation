package pageobject.restapiUtilites;

import java.io.PrintStream;
import java.util.Map;
import static io.restassured.RestAssured.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


public class RestBase {
	
	
	public static RequestSpecification requestSpec;
	public static ResponseSpecification responseSpec;
	public static RequestSpecBuilder requestSpecBuilder;
	public static ResponseSpecBuilder responseSpecBuilder;
	public static PrintStream logger;
	
	
	//* Adding build is Mandatory
	public static RequestSpecification requestSpecification(String baseUri) throws Exception
	{
		if(requestSpecBuilder==null)
		{
			try
			{
				logger=new PrintStream("logger.txt");
				requestSpecBuilder=new RequestSpecBuilder();
			}
			catch (Exception e) {
				throw e;
			}
		}	
		requestSpec=requestSpecBuilder.setBaseUri(baseUri).setRelaxedHTTPSValidation()
		.addFilter(RequestLoggingFilter.logRequestTo(logger))
		.addFilter(ResponseLoggingFilter.logResponseTo(logger))
		.setAccept(ContentType.ANY).build();
		
		return given().spec(requestSpec).when();
	}
	
	
	public static ResponseSpecification responseSpec()
	{
		responseSpecBuilder=new ResponseSpecBuilder();
		responseSpec=responseSpecBuilder.expectContentType(ContentType.ANY).build();
		return responseSpec;
	}
	
	public static Response httpGet(String baseUri,String path,Map<String,Object> headers,Map<String,Object>pathparams,Map<String,Object>queryParams) throws Exception
	{
		requestSpec=RestBase.requestSpecification(baseUri);
		return requestSpec.headers(headers).queryParams(queryParams).pathParams(pathparams).get(path).then().extract().response();
	}
	
	public static Response httpPost(String baseUri,String path,Map<String,Object> headers,Map<String,Object>pathparams,Map<String,Object>queryParams,Object body) throws Exception
	{
		requestSpec=RestBase.requestSpecification(baseUri);
		return requestSpec.headers(headers).queryParams(queryParams).pathParams(pathparams).body(body).post(path).then().extract().response();
	}
	
	public static Response httpPostFormParams(String baseUri,String path,Map<String,Object> headers,Map<String,Object>pathparams,Map<String,Object>queryParams,Map<String,Object> formParams) throws Exception
	{
		requestSpec=RestBase.requestSpecification(baseUri);
		return requestSpec.headers(headers).queryParams(queryParams).pathParams(pathparams).formParams(formParams).post(path).then().extract().response();
	}
	
	public static Response httpPut(String baseUri,String path,Map<String,Object> headers,Map<String,Object>pathparams,Map<String,Object>queryParams,Object body) throws Exception
	{
		requestSpec=RestBase.requestSpecification(baseUri);
		return requestSpec.headers(headers).queryParams(queryParams).pathParams(pathparams).body(body).put(path).then().extract().response();
	}
	
	public static Response httpDelete(String baseUri,String path,Map<String,Object> headers,Map<String,Object>pathparams,Map<String,Object>queryParams,Object body) throws Exception
	{
		requestSpec=RestBase.requestSpecification(baseUri);
		return requestSpec.headers(headers).queryParams(queryParams).pathParams(pathparams).body(body).delete().then().extract().response();
	}
	
}
