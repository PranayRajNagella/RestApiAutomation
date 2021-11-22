package pageObject.restTests;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobject.DataAndPropertyUtils.TestDataProvider;
import pageobject.restapiUtilites.BaseTest;

public class TestPostMemeber extends BaseTest {
	
	@DataProvider(name="Testdata")
	public  Iterator<Object> getTestData(Method M) throws Exception
	{
		ArrayList<Object> list=new ArrayList<>();
		list=JSD.getJosnData(getClass().getSimpleName(),TestDataProvider.getMethodName(M));
	    return  list.iterator();
	}
	
	
	
	@Test(dataProvider="Testdata")
	public void test_01_postnewmember(Map<String,Object> testData)
	{
		System.out.println("TestData for the new members");
		System.out.println(testData);
	}
	
	@Test(dataProvider="Testdata")
	public void test_02_postExsitingmember(Map<String,Object> testData)
	{
		System.out.println("TestData for the existing members");
		System.out.println(testData);
	}

}
