package pageobject.DataAndPropertyUtils;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyReadWriteUtils {
	private Properties prop;
	public PropertyReadWriteUtils(String FileName) throws Exception
	{
		try
		{
		prop=new Properties();
		prop.load(new FileInputStream(FileName));
		}
		catch (Exception e) {
			throw e;
		}
	}
	
	public String readPropetyValue(String Key)
	{
		return prop.getProperty(Key);
	}
	
	public void writePropertyValue(String key,String value)
	{
		prop.setProperty(key, value);
	}
}
