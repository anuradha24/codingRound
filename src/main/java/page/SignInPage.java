package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.WebDriverUtils;

public class SignInPage {
	private WebDriver driver;
	private static final String FRAME_ID = "modal_window";
	private WebDriverUtils driverUtils;
	
	@FindBy(id = "signInButton")
	private WebElement signInButton;
	
	@FindBy(id = "errors1")
	private WebElement errorMessage;
	
	public SignInPage(WebDriver driver){
    	this.driver = driver;
    	PageFactory.initElements(this.driver,this);
    	this.driverUtils = new WebDriverUtils(this.driver);
    }
	
	public void clickOnSignIn(){
		driverUtils.waitForVisibility(this.signInButton).click();
	}
	
	public String getFrameId(){
		return FRAME_ID;
	}
	
	public String getErrorMessage(){
		return driverUtils.waitForVisibility(this.errorMessage).getText();
	}
}
