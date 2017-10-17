package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class HotelSearchPage {

	private WebDriver driver;
    
    @FindBy(id = "Tags")
    private WebElement localityTextBox;

    @FindBy(id = "SearchHotelsButton")
    private WebElement searchButton;

    @FindBy(id = "travellersOnhome")
    private WebElement travellerSelection;

    public HotelSearchPage(WebDriver driver){
    	this.driver = driver;
    	PageFactory.initElements(this.driver,this);
    }
    
    public void inputLocalityTextBox(String inputLocality){
    	this.localityTextBox.sendKeys(inputLocality);
    }
    
    public void selectTravellers(String visibleText){
    	new Select(this.travellerSelection).selectByVisibleText(visibleText);
    }
    
    public void clickSearch(){
    	this.searchButton.click();
    }
}
