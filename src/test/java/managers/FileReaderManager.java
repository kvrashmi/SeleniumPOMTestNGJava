package managers;

import data_providers.ConfigFileReader;
import data_providers.ExcelFileReader;
import data_providers.JSONFileReader;

public class FileReaderManager {
	
	private static FileReaderManager frm = new FileReaderManager();
	
	private static ConfigFileReader cfr = new ConfigFileReader();
	
	private static ExcelFileReader efr = new ExcelFileReader();
	
	private static JSONFileReader jfr = new JSONFileReader();
	
	private FileReaderManager()
	{
		
	}
	
	public static FileReaderManager getInstance()
	{
		return frm;
	}
	
	public ConfigFileReader getConfigReader()
	{
		return (cfr==null)?new ConfigFileReader():cfr;
	}
	//FileReaderManager.getInstance().getConfigReader();
	
	public ExcelFileReader getExcelFileReader()
	{
		return (efr==null)?new ExcelFileReader():efr;
	}
	public JSONFileReader getJSONFileReader()
	{
		return (jfr==null)?new JSONFileReader():jfr;
	}
}
