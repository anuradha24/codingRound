package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utils {
	private WebDriver driver;
	private WebDriverWait wait;
	
	public Utils(WebDriver driver){
		this.driver = driver;
		wait = new WebDriverWait(this.driver,60);
	}
	
	public boolean isElementPresent(WebElement element){
		try{
			wait.until(ExpectedConditions.visibilityOf(element));
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
}
