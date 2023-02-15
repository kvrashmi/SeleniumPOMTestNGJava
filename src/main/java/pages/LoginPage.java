package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import selenium_base.BasePage;

public class LoginPage extends BasePage{
	
	public LoginPage(WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(driver, this);	
	}

	@FindBy(how=How.XPATH,using="//*[@id='username']")WebElement username;
	public void inputUserName(String uname)
	{
		//System.out.println("Username:"+uname);
		this.enterIntoTextBox("//*[@id='username']", loginBtn, uname);
	}
	
	@FindBy(how=How.XPATH,using="//*[@id='password']")WebElement password;
	public void inputPassword(String pass)
	{
		this.enterIntoTextBox("//*[@id='password']", loginBtn, pass);
	}

	@FindBy(how=How.XPATH,using="//*[@name='login']")WebElement loginBtn;
	public void clickLogin()
	{
		this.clickOnElement(loginBtn);
	}
}
