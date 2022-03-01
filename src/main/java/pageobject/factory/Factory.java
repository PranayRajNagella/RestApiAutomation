package pageobject.factory;
import com.aventstack.extentreports.ExtentTest;


public class Factory {
	
	ThreadLocal<ExtentTest> reporter=new ThreadLocal<ExtentTest>();

	private static Factory factory; 
	
	private Factory()
	{
		
	}
	
	
	
	public static Factory getFactory()
	{
		if(factory==null)
		{
			factory=new Factory();
		}
		return factory;
	}
	
	public void setReporter(ExtentTest test)
	{
		reporter.set(test);
	}
	
	public ExtentTest getReporter()
	{
		return reporter.get();
	}
	
}