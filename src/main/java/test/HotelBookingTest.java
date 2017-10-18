package test;

import org.testng.annotations.Test;

import page.HomePage;
import page.HotelSearchPage;

public class HotelBookingTest extends BaseTest{
	private HotelSearchPage hotelSearchPage;
	private HomePage homePage;
	
	@Test(dataProvider="getHotelBookingTestData")
	 public void shouldBeAbleToSearchForHotels(String locality,String travellers) {
		     hotelSearchPage = new HotelSearchPage(driver);
		     homePage = new HomePage(driver);
			 homePage.clickHotelLink();
			 hotelSearchPage.inputLocalityTextBox(locality);
			 hotelSearchPage.selectTravellers(travellers);
			 hotelSearchPage.clickSearch();
	}
		 
}
