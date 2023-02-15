package tests;
import org.openqa.selenium.WebDriver;
import java.util.*;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import managers.FileReaderManager;
import managers.PageObjectManager;
import managers.WebDriverManager;
import pages.LoginPage;
import testng_listeners.TestListener;

@Listeners(TestListener.class)
public class LoginPageTestFirefox {
		WebDriverManager wdm;
		LoginPage lp;
		PageObjectManager mgr;
		WebDriver driver;
		Map<?,?> lm;
		
		@BeforeTest
		public void setUp()
		{
			Reporter.log("Initializing webdriver manager...");
			Reporter.log("Initializing driver...");
			Reporter.log("Initializing pageobject manager...");
		}
		
		@Test(priority=0,groups="firefox")
		public void setUpFirefox()
		{
			wdm = new WebDriverManager();
			driver=wdm.setUpDriverForFireFox();
			mgr = new PageObjectManager(driver);
			lp=mgr.getLoginPage();
		}
		
		@Test(priority=1,groups="firefox")
		public void launchLandingPage()
		{
			Reporter.log("Launching Login Page In Firefox...");
			this.driver.get(FileReaderManager.getInstance().getConfigReader().getConfigFileContents().get("url"));
			Reporter.log("Finished Launching...");

		}
		
		@Test(priority=2,groups="firefox")
		public void testLoginPage()
		{
			Reporter.log("Testing Login Page");
			FileReaderManager.getInstance().getJSONFileReader().setFilePath(System.getProperty("user.dir")+"/src/test/resources/testData/LoginInfo.json");
			lm=FileReaderManager.getInstance().getJSONFileReader().getJsonFileContentsAsMap();
			Reporter.log("Passing value read from json.");
			lp.inputUserName(lm.get("username").toString());
			Reporter.log("Passing value read from json.");
			lp.inputPassword(lm.get("password").toString());
			Reporter.log("Click login button.");
			lp.clickLogin();
			Reporter.log("Login button click completed.");

		}
		
		@AfterTest
		public void tearDown()
		{
			wdm.tearDown();
			Reporter.log("Closing the driver...");
			Reporter.log("Quitting the driver...");

		}
		
}
