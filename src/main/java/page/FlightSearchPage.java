package page;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FlightSearchPage {
	private WebDriver driver;
	private WebDriverWait wait;
	
	@FindBy(id = "OneWay")
    private WebElement oneWayOption;
	
	@FindBy(id = "FromTag")
    private WebElement fromField;
	
	@FindBy(id = "ToTag")
    private WebElement toField;
	
	@FindBy(id = "ui-id-1")
	private WebElement sourceOptionsList;

	@FindBy(id = "ui-id-2")
	private WebElement destinationOptionsList;
	
	@FindBy(id = "SearchBtn")
	private WebElement searchButton;
	
	@FindBy(xpath = ".//td[contains(@class,'ui-datepicker')]/a[contains(@class,'ui-state-highlight')]")
	private WebElement currentDate;
	
	public FlightSearchPage(WebDriver driver){
    	this.driver = driver;
    	PageFactory.initElements(this.driver,this);
    	wait = new WebDriverWait(this.driver,60);
    }
	
	public void selectOneWayOption(){
		this.oneWayOption.click();
	}
	
	public void inputFromField(String source){
		this.fromField.clear();
		this.fromField.sendKeys(source);
	}
	
	public void inputToField(String destination){
		this.toField.clear();
		this.toField.sendKeys(destination);
	}
	
	public WebElement waitForSourceAutoCompleteOptions(String source){
			for(int i=0;i<3;i++){
				try{
					return this.wait.until(ExpectedConditions.visibilityOf(this.sourceOptionsList));
				}catch(TimeoutException e){
				this.inputFromField(source);
			}
		}
			return this.wait.until(ExpectedConditions.visibilityOf(this.sourceOptionsList));
	}
	
	public void selectFirstSourceOption(String source){
		this.waitForSourceAutoCompleteOptions(source).findElements(By.tagName("li")).get(0).click();
	}
	
	public WebElement waitForDestinationAutoCompleteOptions(String destination){
		for(int i=0;i<3;i++){
			try{
				return this.wait.until(ExpectedConditions.visibilityOf(this.destinationOptionsList));
			}catch(TimeoutException e){
			this.inputToField(destination);
		}
	}
		return this.wait.until(ExpectedConditions.visibilityOf(this.destinationOptionsList));
	}
	
	public void selectFirstDestinationOption(String destination){
		this.waitForDestinationAutoCompleteOptions(destination).findElements(By.tagName("li")).get(0).click();
	}
	
	public void clickSearchButton(){
		this.searchButton.click();
	}
	
	public void selectCurrentDate(){
		this.currentDate.click();
	}
}
