package data_providers;

import java.io.*;
import java.util.*;

public class ConfigFileReader {
	private String filePath=null;
	
	public ConfigFileReader()
	{
		this.filePath=System.getProperty("user.dir")+"/src/test/resources/config/config.properties";
	}
	
	public String getFilePath()
	{
		return this.filePath;
	}
	
	public Map<String,String> getConfigFileContents()
	{
		Map<String,String> configMap = new HashMap<String,String>();
		File f = new File(this.filePath);
		try
		{
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			Properties p = new Properties();
			p.load(br);
			br.close();
			
			/*
			for(Map.Entry<Object,Object> s:p.entrySet())
			{
				configMap.put(s.getKey().toString(),s.getValue().toString());
			}
			*/
			
			/*
			for(Object s:p.keySet())
			{
				configMap.put(s.toString(), p.getProperty(s.toString()).toString());
			}
			*/
			for(Object s: p.keySet())
			{
				String key = (String)s;
				configMap.put(key,p.getProperty(key));
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		System.out.println("ConfigMapContents:"+configMap);
		return configMap;
	}
	
	

	/*
	public static void main(String[] args)
	{
		ConfigFileReader cfg = new ConfigFileReader();
		cfg.getConfigFileContents();
	}
	*/
}
