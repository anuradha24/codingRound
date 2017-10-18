package test;

import org.testng.Assert;
import org.testng.annotations.Test;

import page.HomePage;
import page.SignInPage;

public class SignInTest extends BaseTest{
	private SignInPage signInPage;
	private HomePage homePage;
	private static final String EXPECTED_ERROR_MESSAGE = "There were errors in your submission";
	
	@Test
	public void shouldThrowAnErrorIfSignInDetailsAreMissing() {
		     signInPage = new SignInPage(driver);
		     homePage = new HomePage(driver);
			 homePage.clickYourTripsLink();
			 homePage.clickOnSignIn();
			 driver.switchTo().frame(signInPage.getFrameId());
			 signInPage.clickOnSignIn();
	     	 Assert.assertTrue(signInPage.getErrorMessage().contains(EXPECTED_ERROR_MESSAGE));
	 }	 
}
