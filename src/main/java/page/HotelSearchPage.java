package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import utils.WebDriverUtils;

public class HotelSearchPage {

	private WebDriver driver;
    private WebDriverUtils driverUtils;
    
    @FindBy(id = "Tags")
    private WebElement localityTextBox;

    @FindBy(id = "SearchHotelsButton")
    private WebElement searchButton;

    @FindBy(id = "travellersOnhome")
    private WebElement travellerSelection;

    public HotelSearchPage(WebDriver driver){
    	this.driver = driver;
    	PageFactory.initElements(this.driver,this);
    	this.driverUtils = new WebDriverUtils(this.driver);
    }
    
    public void inputLocalityTextBox(String inputLocality){
    	driverUtils.waitForVisibility(this.localityTextBox).sendKeys(inputLocality);
    }
    
    public void selectTravellers(String visibleText){
    	new Select(driverUtils.waitForVisibility(this.travellerSelection)).selectByVisibleText(visibleText);
    }
    
    public void clickSearch(){
    	driverUtils.waitForVisibility(this.searchButton).click();
    }
}
