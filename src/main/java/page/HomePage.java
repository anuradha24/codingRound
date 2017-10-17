package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	private WebDriver driver;
	
	@FindBy(linkText = "Hotels")
	private WebElement hotelLink;
	
	@FindBy(linkText = "Your trips")
	private WebElement yourTripsLink;
	
	@FindBy(id = "SignIn")
	private WebElement signInButton;
	
	public HomePage(WebDriver driver){
    	this.driver = driver;
    	PageFactory.initElements(this.driver,this);
    }
	
	public void clickHotelLink(){
	    	this.hotelLink.click();
	    }
	
	public void clickYourTripsLink(){
		this.yourTripsLink.click();
	}
	
	public void clickOnSignIn(){
		this.signInButton.click();
	}
}
