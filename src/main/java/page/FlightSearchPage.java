package page;

import org.openqa.selenium.By;
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
	
	public WebElement waitForSourceAutoCompleteOptions(){
		return this.wait.until(ExpectedConditions.visibilityOf(this.sourceOptionsList));
	}
	
	public void selectFirstSourceOption(){
		this.waitForSourceAutoCompleteOptions().findElements(By.tagName("li")).get(0).click();
	}
	
	public WebElement waitForDestinationAutoCompleteOptions(){
		return this.wait.until(ExpectedConditions.visibilityOf(this.destinationOptionsList));
	}
	
	public void selectFirstDestinationOption(){
		this.waitForDestinationAutoCompleteOptions().findElements(By.tagName("li")).get(0).click();
	}
	
	public void clickSearchButton(){
		this.searchButton.click();
	}
	
	public void selectCurrentDate(){
		this.currentDate.click();
	}
}
