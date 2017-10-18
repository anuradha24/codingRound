package test;

import org.testng.Assert;
import org.testng.annotations.Test;

import page.FlightSearchPage;
import page.SearchResultsPage;
import utils.WebDriverUtils;

public class FlightBookingTest extends BaseTest{
	private FlightSearchPage flightSearchPage;
	private SearchResultsPage searchResultsPage;
	private WebDriverUtils driverUtils;
	
	@Test(dataProvider="getFlightBookingTestData")
	public void testThatResultsAppearForAOneWayJourney(String source, String destination) {
		 flightSearchPage =  new FlightSearchPage(driver);
		 searchResultsPage = new SearchResultsPage(driver);
		 driverUtils = new WebDriverUtils(driver);
		 flightSearchPage.selectOneWayOption();
		 flightSearchPage.inputFromField(source);
		 flightSearchPage.selectFirstSourceOption(source);
		 flightSearchPage.inputToField(destination);
		 flightSearchPage.selectFirstDestinationOption(destination);
		 flightSearchPage.selectCurrentDate();
		 flightSearchPage.clickSearchButton();
		 Assert.assertTrue(driverUtils.isElementPresent(searchResultsPage.getSearchSummaryElement()));
	 }
}
