package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtils {
	private WebDriver driver;
	private WebDriverWait wait;
	
	public WebDriverUtils(WebDriver driver){
		this.driver = driver;
		this.wait = new WebDriverWait(this.driver,60);
	}
	
	public boolean isElementPresent(WebElement element){
		try{
			this.wait.until(ExpectedConditions.visibilityOf(element));
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
	public WebElement waitForVisibility(WebElement element){
		return this.wait.until(ExpectedConditions.visibilityOf(element));
	}
}
