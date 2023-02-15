package managers;

import java.util.*;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import enums.DriverType;

public class WebDriverManager {
	private WebDriver driver=null;
	private static DriverType driverType;
	private Map<String,String> cfd = FileReaderManager.getInstance().getConfigReader().getConfigFileContents();
	
	//Get the driver type
	public WebDriverManager()
	{
		//driverType=DriverType.valueOf(cfd.get("browser").toUpperCase());
	}
	
	 //Set Up the driver
	public WebDriver getDriver()
	{
		if(driver==null)
		{
			switch(driverType) {
			case CHROME:
				driver=this.setUpDriverForChrome();
				break;
			
			case FIREFOX:
				driver=this.setUpDriverForFireFox();
				break;	
			}	
		}
		return driver;
	}
	
	public WebDriver setUpDriverForChrome()
	{
		String chromeBrowserPath=FileReaderManager.getInstance().getConfigReader().getConfigFileContents().get("chromeBrowserPath");
		System.setProperty("webdriver.chrome.driver","/usr/local/bin/chromedriver");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return driver;
	}
	
	public WebDriver setUpDriverForFireFox()
	{
		String fireFoxDriverPath=FileReaderManager.getInstance().getConfigReader().getConfigFileContents().get("firefoxBrowserPath");
		System.setProperty("webdriver.gecko.driver","/usr/local/bin/geckodriver");
		driver=new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return driver;
	}
	
	public void tearDown()
	{
		driver.close();
		driver.quit();
	}
	

}
