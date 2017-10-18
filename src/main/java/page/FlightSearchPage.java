package page;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.WebDriverUtils;

public class FlightSearchPage {
	private WebDriver driver;
	private WebDriverUtils driverUtils;
	
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
    	this.driverUtils = new WebDriverUtils(this.driver);
    }
	
	public void selectOneWayOption(){
		driverUtils.waitForVisibility(this.oneWayOption).click();
	}
	
	public void inputFromField(String source){
		driverUtils.waitForVisibility(this.fromField).clear();
		this.fromField.sendKeys(source);
	}
	
	public void inputToField(String destination){
		driverUtils.waitForVisibility(this.toField).clear();
		this.toField.sendKeys(destination);
	}
	
	public WebElement waitForSourceAutoCompleteOptions(String source){
			for(int i=0;i<3;i++){
				try{
					return driverUtils.waitForVisibility(this.sourceOptionsList);
				}catch(TimeoutException e){
				this.inputFromField(source);
			}
		}
			return driverUtils.waitForVisibility(this.sourceOptionsList);
	}
	
	public void selectFirstSourceOption(String source){
		this.waitForSourceAutoCompleteOptions(source).findElements(By.tagName("li")).get(0).click();
	}
	
	public WebElement waitForDestinationAutoCompleteOptions(String destination){
		for(int i=0;i<3;i++){
			try{
				return driverUtils.waitForVisibility(this.destinationOptionsList);
			}catch(TimeoutException e){
			this.inputToField(destination);
		}
	}
		return driverUtils.waitForVisibility(this.destinationOptionsList);
	}
	
	public void selectFirstDestinationOption(String destination){
		this.waitForDestinationAutoCompleteOptions(destination).findElements(By.tagName("li")).get(0).click();
	}
	
	public void clickSearchButton(){
		driverUtils.waitForVisibility(this.searchButton).click();
	}
	
	public void selectCurrentDate(){
		driverUtils.waitForVisibility(this.currentDate).click();
	}
}
