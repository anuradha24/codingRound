package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResultsPage {
	private WebDriver driver;
	
	@FindBy(className = "searchSummary")
	private WebElement searchSummary;
	
	public SearchResultsPage(WebDriver driver){
    	this.driver = driver;
    	PageFactory.initElements(this.driver,this);
    }

	public WebElement getSearchSummaryElement(){
		return this.searchSummary;
	}
	
}
