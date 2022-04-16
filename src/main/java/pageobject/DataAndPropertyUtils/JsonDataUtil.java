package pageobject.DataAndPropertyUtils;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonDataUtil {

	
	public ArrayList<Object> getJsonData(String TestScriptName,String testCaseName) throws Exception
	{
		try
		{
			String json=this.readFileAsString(System.getProperty("user.dir")+"\\src\\test\\Resources\\TestData.json");
			ObjectMapper mapper=new ObjectMapper();
			JsonNode startNode=mapper.readTree(json).get("TestData");		
			JsonNode TestScriptNode=null;
			for(int i=0;i<startNode.size();i++)
			{
				if(startNode.get(i).get("TestScriptName").asText().equalsIgnoreCase(TestScriptName))
				{
					TestScriptNode=startNode.get(i);
					break;
				}
			}
			JsonNode testCase=TestScriptNode.get("TestCases");
			JsonNode data=null;
			for(int i=0;i<testCase.size();i++)
			{
				if(testCase.get(i).get("TestCaseName").asText().equalsIgnoreCase(testCaseName))
				{
					data=testCase.get(i).get("data");
					break;
				}
			}
			ArrayList<Object> dataArray=new ArrayList<>();
			dataArray=mapper.convertValue(data,new TypeReference<ArrayList<Object>>() {});
			dataArray.removeIf(n -> this.check(n));
			return dataArray;
		}
		catch (Exception e) {
			System.out.println(e.getMessage().toString());
			throw e;
		}
		
		
	}
	
	
	private boolean check(Object n) {
		
		return ((Map<String,Object>)n).get("RunMode").equals("NO");
	}


	public String readFileAsString(String file)throws Exception
	{
	        return new String(Files.readAllBytes(Paths.get(file)));
	}
	
	public void checkRunMode() 
	{
		
	}
	
	
}
