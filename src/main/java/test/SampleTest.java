package test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import common.BaseSteps;
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
		 driver = BaseSteps.instantiateDriver();
		 driver.get("https://www.cleartrip.com/");
		 driver.manage().window().maximize();
	 }
	@Test
	 public void shouldThrowAnErrorIfSignInDetailsAreMissing() {
		 signInPage =  new SignInPage(driver);
		 homePage = new HomePage(driver);
		 homePage.clickYourTripsLink();
		 homePage.clickOnSignIn();
		 driver.switchTo().frame(signInPage.getFrameId());
		 signInPage.clickOnSignIn();
     	 Assert.assertTrue(signInPage.getErrorMessage().contains("There were errors in your submission"));
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
		 flightSearchPage.selectFirstSourceOption("Bangalore");
		 flightSearchPage.inputToField("Delhi");
		 flightSearchPage.selectFirstDestinationOption("Delhi");
		 flightSearchPage.selectCurrentDate();
		 flightSearchPage.clickSearchButton();
		 Assert.assertTrue(utils.isElementPresent(searchResultsPage.getSearchSummaryElement()));
	 }
	 @AfterMethod
	 public void tearDown(){
		 driver.quit();
	 }
}
