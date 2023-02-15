package data_providers;

import java.nio.file.Paths;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import data_class.LoginInfo;

public class JSONFileReader {
	
	String filePath=null;
	
	public void setFilePath(String filePath)
	{
		this.filePath=filePath;
	}
	
	public Map<?,?> getJsonFileContentsAsMap()
	{
		 Map<?, ?> map =null;
		 try 
		 {
		    // create object mapper instance
		    ObjectMapper mapper = new ObjectMapper();

		    // convert JSON file to map
		   map  = mapper.readValue(Paths.get(this.filePath).toFile(), Map.class);

		    // print map entries
		    for (Map.Entry<?, ?> entry : map.entrySet()) {
		        System.out.println(entry.getKey() + "=" + entry.getValue()); 
		        }
		 } 
		catch (Exception ex) 
		{
		    ex.printStackTrace();
		}
	
		 return map;
	}
	
	public LoginInfo getJsonFileContentsAsObject()
	{
		LoginInfo lInfo=null;
		try {
		    // create object mapper instance
		    ObjectMapper mapper = new ObjectMapper();

		    // convert a JSON string to a Book object
		    lInfo = mapper.readValue(Paths.get(this.filePath).toFile(), LoginInfo.class);

		    // print book
		    System.out.println(lInfo);

		} catch (Exception ex) {
		    ex.printStackTrace();
		}
		return lInfo;
	}
	
	public List<LoginInfo> getJsonFileContentsAsListOfObjects()
	{
		List<LoginInfo> loginInfoList=null;
		try {
		    // create object mapper instance
		    ObjectMapper mapper = new ObjectMapper();

		    // convert JSON array to list of books
		    loginInfoList= Arrays.asList(mapper.readValue(Paths.get(this.filePath).toFile(), LoginInfo[].class));

		    // print books
		    loginInfoList .forEach(System.out::println);

		} catch (Exception ex) {
		    ex.printStackTrace();
		}
		return loginInfoList;
	}
	
	/*
	public static void main(String[] args)
	{
		JSONFileReader jfr = new JSONFileReader();
		jfr.setFilePath(System.getProperty("user.dir")+"/src/test/resources/testData/LoginInfo.json");
		Map<?, ?> map=jfr.getJsonFileContentsAsMap();System.out.println(map);
	}
	*/
}