package pageobject.restapiUtilites;

import java.io.PrintStream;
import java.util.Map;
import static io.restassured.RestAssured.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
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


	/**
	 * to construct and build a request specification
	 * @param baseUri
	 * @return
	 * @throws Exception
	 */
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

	/**
	 *  To make a call to get Method
	 * @param baseUri
	 * @param path
	 * @param headers
	 * @param pathparams
	 * @param queryParams
	 * @return
	 * @throws Exception
	 */
	public static Response httpGet(String baseUri,String path,Map<String,Object> headers,Map<String,Object>pathparams,Map<String,Object>queryParams) throws Exception
	{
		requestSpec=RestBase.requestSpecification(baseUri);
		return requestSpec.headers(headers).queryParams(queryParams).pathParams(pathparams).get(path).then().extract().response();
	}

	/**
	 *  To make a call to get Method using request spec
	 * @param requestSpec
	 * @param path
	 * @param pathparams
	 * @param queryParams
	 * @return
	 * @throws Exception
	 */
	public static Response httpGet(RequestSpecification requestSpec,String path,Map<String,Object>pathparams,Map<String,Object>queryParams) throws Exception
	{
		System.out.println(requestSpec.toString());
		return requestSpec.queryParams(queryParams).pathParams(pathparams).get(path).then().extract().response();
	}


	/**
	 * To make a call to Post Method
	 * @param baseUri
	 * @param path
	 * @param headers
	 * @param pathparams
	 * @param queryParams
	 * @param body
	 * @return
	 * @throws Exception
	 */
	public static Response httpPost(String baseUri,String path,Map<String,Object> headers,Map<String,Object>pathparams,Map<String,Object>queryParams,Object body) throws Exception
	{
		requestSpec=RestBase.requestSpecification(baseUri);
		return requestSpec.headers(headers).queryParams(queryParams).pathParams(pathparams).body(body).post(path).then().extract().response();
	}

	/**
	 * To make a call to Post Method using request spec
	 * @param requestSpec
	 * @param path
	 * @param pathparams
	 * @param queryParams
	 * @param body
	 * @return
	 * @throws Exception
	 */
	public static Response httpPost(RequestSpecification requestSpec,String path,Map<String,Object>pathparams,Map<String,Object>queryParams,Object body) throws Exception
	{
		System.out.println(requestSpec.toString());
		return requestSpec.queryParams(queryParams).pathParams(pathparams).body(body).post(path).then().extract().response();
	}

	/**
	 * To make a call to Post Method using form parameters
	 * @param baseUri
	 * @param path
	 * @param headers
	 * @param pathparams
	 * @param queryParams
	 * @param formParams
	 * @return
	 * @throws Exception
	 */
	public static Response httpPostFormParams(String baseUri,String path,Map<String,Object> headers,Map<String,Object>pathparams,Map<String,Object>queryParams,Map<String,Object> formParams) throws Exception
	{
		requestSpec=RestBase.requestSpecification(baseUri);
		return requestSpec.headers(headers).queryParams(queryParams).pathParams(pathparams).formParams(formParams).post(path).then().extract().response();
	}

	/**
	 * To make a call to Post Method using form parameters and request spec
	 * @param requestSpec
	 * @param path
	 * @param pathparams
	 * @param queryParams
	 * @param formParams
	 * @return
	 * @throws Exception
	 */
	public static Response httpPostFormParams(RequestSpecification requestSpec,String path,Map<String,Object>pathparams,Map<String,Object>queryParams,Map<String,Object> formParams) throws Exception
	{
		System.out.println(requestSpec.toString());
		return requestSpec.queryParams(queryParams).pathParams(pathparams).formParams(formParams).post(path).then().extract().response();
	}

	/**
	 * To make a call to Put Method
	 * @param baseUri
	 * @param path
	 * @param headers
	 * @param pathparams
	 * @param queryParams
	 * @param body
	 * @return
	 * @throws Exception
	 */
	public static Response httpPut(String baseUri,String path,Map<String,Object> headers,Map<String,Object>pathparams,Map<String,Object>queryParams,Object body) throws Exception
	{
		requestSpec=RestBase.requestSpecification(baseUri);
		return requestSpec.headers(headers).queryParams(queryParams).pathParams(pathparams).body(body).put(path).then().extract().response();
	}

	/**
	 * To make a call to Put Method using request spec
	 * @param requestSpec
	 * @param path
	 * @param pathparams
	 * @param queryParams
	 * @param body
	 * @return
	 * @throws Exception
	 */
	public static Response httpPut(RequestSpecification requestSpec,String path,Map<String,Object>pathparams,Map<String,Object>queryParams,Object body) throws Exception
	{
		System.out.println(requestSpec.toString());
		return requestSpec.queryParams(queryParams).pathParams(pathparams).body(body).put(path).then().extract().response();
	}


	/**
	 *To make a call to DELETE Method
	 * @param baseUri
	 * @param path
	 * @param headers
	 * @param pathparams
	 * @param queryParams
	 * @param body
	 * @return
	 * @throws Exception
	 */
	public static Response httpDelete(String baseUri,String path,Map<String,Object> headers,Map<String,Object>pathparams,Map<String,Object>queryParams,Object body) throws Exception
	{
		requestSpec=RestBase.requestSpecification(baseUri);
		return requestSpec.headers(headers).queryParams(queryParams).pathParams(pathparams).body(body).delete().then().extract().response();
	}

	/**
	 * *To make a call to DELETE Method using request spec
	 * @param requestSpec
	 * @param path
	 * @param pathparams
	 * @param queryParams
	 * @param body
	 * @return
	 * @throws Exception
	 */
	public static Response httpDelete(RequestSpecification requestSpec,String path,Map<String,Object>pathparams,Map<String,Object>queryParams,Object body) throws Exception
	{
		System.out.println(requestSpec.toString());
		return requestSpec.queryParams(queryParams).pathParams(pathparams).body(body).put(path).then().extract().response();
	}

	/**
	 * sets the headers
	 * @param requestSpec
	 * @param headers
	 */
	public static void setHeaders(RequestSpecification requestSpec,Map<String,Object> headers)
	{
		requestSpec.headers(headers);
	}

	/**
	 * set's url enconding (i.e to enable and disable url encoding)
	 * @param requestSpec
	 * @param flag
	 */
	public static void setEncoding(RequestSpecification requestSpec,Boolean flag)
	{
		requestSpec.urlEncodingEnabled(flag);
	}

	/**
	 * set the connection time out of a request
	 * @param milliseconds
	 */
	public static void setConnectionTimeOut(long milliseconds)
	{
		requestSpec.config(RestAssuredConfig.config().httpClient(HttpClientConfig.httpClientConfig().setParam("http.connection.timeout",milliseconds)));
	}



	
}
