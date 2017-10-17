package test;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import common.CommonSteps;
import page.FlightSearchPage;
import page.HomePage;
import page.HotelSearchPage;
import page.SearchResultsPage;
import page.SignInPage;
import utils.Utils;

import org.testng.Assert;

public class SampleTest {
	 private WebDriver driver;
	 private SignInPage signInPage;
	 private HomePage homePage;
	 private FlightSearchPage flightSearchPage;
	 private HotelSearchPage hotelSearchPage;
	 private SearchResultsPage searchResultsPage;
	 private Utils utils;
	 
	 @BeforeMethod
	 public void setup(){
		 driver = CommonSteps.instantiateDriver();
		 driver.get("https://www.cleartrip.com/");
	 }
	 @Test
	 public void shouldThrowAnErrorIfSignInDetailsAreMissing() {
		 signInPage =  new SignInPage(driver);
		 homePage = new HomePage(driver);
		 String primaryWindowHandle = driver.getWindowHandle();
		 homePage.clickYourTripsLink();
		 homePage.clickOnSignIn();
		 Set<String> windowHandles = driver.getWindowHandles();
	        for(String handle : windowHandles){
	        	if(!primaryWindowHandle.equalsIgnoreCase(handle)){
	        		driver.switchTo().window(primaryWindowHandle);
	        		signInPage.clickOnSignIn();
	        		Assert.assertTrue(signInPage.getErrorMessage().contains("There were errors in your submission"));
	        		driver.close();
	        	}
	        }
	        driver.switchTo().window(primaryWindowHandle);
	 }
	 
	 @Test
	 public void shouldBeAbleToSearchForHotels() {
		 hotelSearchPage =  new HotelSearchPage(driver);
		 if(homePage==null){
			 homePage = new HomePage(driver);
		 }
		 homePage.clickHotelLink();
		 hotelSearchPage.inputLocalityTextBox("Indiranagar, Bangalore");
		 hotelSearchPage.selectTravellers("1 room, 2 adults");
		 hotelSearchPage.clickSearch();
	 }
	 
	 @Test
	 public void testThatResultsAppearForAOneWayJourney() {
		 flightSearchPage =  new FlightSearchPage(driver);
		 searchResultsPage = new SearchResultsPage(driver);
		 if(utils == null){
			 utils = new Utils(driver);
		 }
		 flightSearchPage.selectOneWayOption();
		 flightSearchPage.inputFromField("Bangalore");
		 flightSearchPage.selectFirstSourceOption();
		 flightSearchPage.inputToField("Delhi");
		 flightSearchPage.selectFirstDestinationOption();
		 flightSearchPage.selectCurrentDate();
		 flightSearchPage.clickSearchButton();
		 Assert.assertTrue(utils.isElementPresent(searchResultsPage.getSearchSummaryElement()));
	 }
	 @AfterMethod
	 public void tearDown(){
		 driver.quit();
	 }
}
