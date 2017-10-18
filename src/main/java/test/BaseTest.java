package test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import common.BaseSteps;
import utils.FileUtils;

public class BaseTest {
	 protected WebDriver driver;
	 
	 @BeforeMethod
	 public void setup(){
		 driver = BaseSteps.instantiateDriver();
		 String url = FileUtils.getPropertiesFromFile("connection.properties").getProperty("url");
		 driver.get(url);
		 driver.manage().window().maximize();
	 }
	 
	 @AfterMethod
	 public void tearDown(){
		 driver.quit();
	 }
	 
	 @DataProvider
	 public Object[][] getFlightBookingTestData(){
		 return FileUtils.getDataFromCsvFile("flightSearchData.csv",",");
	 }
	 
	 @DataProvider
	 public Object[][] getHotelBookingTestData(){
		 return FileUtils.getDataFromCsvFile("hotelSearchData.csv","%");
	 }

}
