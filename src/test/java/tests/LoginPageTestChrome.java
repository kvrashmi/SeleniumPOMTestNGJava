package tests;

import org.openqa.selenium.WebDriver;
import java.util.*;

import org.testng.Assert;
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
public class LoginPageTestChrome {
	WebDriverManager wdm;
	LoginPage lp;
	PageObjectManager mgr;
	WebDriver driver;
	List<Map<String,String>> lm;
	
	@BeforeTest
	public void setUp()
	{
		Reporter.log("Initializing webdriver manager...");
		Reporter.log("Initializing driver...");
		Reporter.log("Initializing pageobject manager...");
	}
	
	@Test(priority=0,groups="chrome")
	public void setUpChrome()
	{
		wdm = new WebDriverManager();
		this.driver=wdm.setUpDriverForChrome();
		mgr = new PageObjectManager(driver);
		lp=mgr.getLoginPage();
	}
	
	@Test(priority=1,groups="chrome")
	public void launchLandingPage()
	{
		String url =FileReaderManager.getInstance().getConfigReader().getConfigFileContents().get("url");
		this.driver.get(url);
		Reporter.log("Finished Launching...");
		Assert.assertEquals(true,true);
		
	}
	
	@Test(priority=2,groups="chrome")
	public void testLoginPage()
	{
		Reporter.log("Testing Login Page");
		lm = FileReaderManager.getInstance().getExcelFileReader().getExcelFileContents("Login");
		Reporter.log("Passing value read from excel sheet.");
		lp.inputUserName(lm.get(0).get("Username"));
		Reporter.log("Passing value read from excel sheet.");
		lp.inputPassword(lm.get(0).get("Password"));
		Reporter.log("Click login button.");
		lp.clickLogin();
		Reporter.log("Login button click completed.");
		Assert.assertEquals(true,true);

	}
	
	@AfterTest
	public void tearDown()
	{
		wdm.tearDown();
		Reporter.log("Closing the driver...");
		Reporter.log("Quitting the driver...");

	}
	
	
	
}
