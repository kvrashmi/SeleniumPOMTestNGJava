package selenium_base;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	private WebDriver driver;
	
	public BasePage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public void enterIntoTextBox(String locatorText,WebElement element,String text)
	{
		this.explicitlyWaitForElements("TextBox",locatorText,element);
		//System.out.println("BasePage:"+text);
		element.sendKeys(text);
	}

	public void clickOnElement(WebElement element)
	{
		element.click();
	}
	
	public void selectFromDropDown(By locator,WebElement element,String visibleText)
	{
		Select sel=new Select(this.driver.findElement(locator));
		sel.selectByVisibleText(visibleText); 
	}
	
	public void selectFromDropDown(WebElement element, String visibleText)
	{
		Select sel=new Select(element);
		sel.selectByVisibleText(visibleText);
	}

	public void selectFromDropDown(WebElement element,int index)
	{
		Select sel=new Select(element);
		sel.selectByIndex(index);
	}
	
	public void performOnElementAfterWait(String elemType,String locatorText,WebElement element,WebDriverWait wait)
	{
		if(elemType.equals("Select"))
		{
			wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(By.xpath(locatorText),By.tagName("option")));
		}
		else if(elemType.equals("Link")|| elemType.equals("Button"))
		{
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(locatorText))));
		}
		else if(elemType.equals("Div"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorText)));
		}
		else if(elemType.equals("TextBox"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorText)));
		}
	}
	
	public void explicitlyWaitForElements(String elemType,String locatorText,WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(this.driver,30);
		try
		{
			this.performOnElementAfterWait(elemType, locatorText, element, wait);
		}
		catch(TimeoutException e)
		{
			//If timeout exception despite this- then try to find the element by JavaScript executor
			JavascriptExecutor js = (JavascriptExecutor)driver;
			
			//wait until the page return complete as its status
			if(wait.until(driver -> ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete")))
			{
				//Now try to locate the element again
				this.performOnElementAfterWait(elemType, locatorText, element, wait);
			}			
		}
		catch(StaleElementReferenceException e)
		{
			if(wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(element))))
			{
				this.performOnElementAfterWait(elemType, locatorText, element, wait);			
			}
		}	
	}
	
	
	public int getTableColumnCount(String tableLocator)
	{
		//Get the number of columns in this table
		List <WebElement> col = this.driver.findElements(By.xpath(tableLocator));
	    return col.size();	    
	}
	
	public int getTableRowCount(String tableLocator)
	{
		List <WebElement> rows = this.driver.findElements(By.xpath(tableLocator)); 
		return rows.size();
	}
	
	public void hoverOverElement(WebElement element)
	{
		Actions action = new Actions(this.driver);
		action.moveToElement(element).perform();
	}

}
