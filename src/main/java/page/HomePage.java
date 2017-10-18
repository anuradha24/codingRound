package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.WebDriverUtils;

public class HomePage {
	private WebDriver driver;
	private WebDriverUtils driverUtils;
	
	@FindBy(linkText = "Hotels")
	private WebElement hotelLink;
	
	@FindBy(linkText = "Your trips")
	private WebElement yourTripsLink;
	
	@FindBy(id = "SignIn")
	private WebElement signInButton;
	
	public HomePage(WebDriver driver){
    	this.driver = driver;
    	PageFactory.initElements(this.driver,this);
    	this.driverUtils = new WebDriverUtils(this.driver);
    }
	
	public void clickHotelLink(){
		driverUtils.waitForVisibility(this.hotelLink).click();
	    }
	
	public void clickYourTripsLink(){
		driverUtils.waitForVisibility(this.yourTripsLink).click();
	}
	
	public void clickOnSignIn(){
		driverUtils.waitForVisibility(this.signInButton).click();
	}
}
