package pageobject.DataAndPropertyUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.apache.poi.ss.usermodel.Sheet;


public class TestDataProvider {
	
	
	static String methodName;
	public static String getMethodName(Method M)
	{
		TestCaseName testCaseAnnotation = (TestCaseName) M.getAnnotation(TestCaseName.class);
		if(M.isAnnotationPresent(TestCaseName.class))
		{
			methodName=testCaseAnnotation.testCaseName();
			//methodName=
		}
		else
		{
			methodName=M.getName();
		}
		return methodName;
		
	}
	

}
