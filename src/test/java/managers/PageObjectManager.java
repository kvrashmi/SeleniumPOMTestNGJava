package managers;

import org.openqa.selenium.WebDriver;

import pages.LoginPage;

public class PageObjectManager {
	private WebDriver driver;
	private LoginPage loginPage;
	
	
	public PageObjectManager(WebDriver driver) {
		this.driver = driver;
	}

	public LoginPage getLoginPage(){
		return (this.loginPage == null) ? this.loginPage = new LoginPage(this.driver) : loginPage;

	}
	

}
