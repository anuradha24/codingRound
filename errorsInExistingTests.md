## Test Errors
#### In SignInTest,HotelBookingTest,FlightBookingTest:
#### Error:
setDriverPath() was called in the test after the call of instantiating the driver as follows:
 

    WebDriver driver = new ChromeDriver();
#### Fix:
I made the setDriverPath() method as a static member of the test class and called it in the static block so that it is called when the class is first loaded into memory and the setting of driver path is done before the test method is executed. This is not the ideal way of doing it but I took this approach as the first step to get the test working.

    WebDriver driver;
        static{
        	setDriverPath();
        }

---
#### In SignInTest:
#### Error:
Once the signIn button is clicked, the signIn page is coming as a different window (different DOM structure from the current page), so the signInButton was not found with an exception as follows: 

org.openqa.selenium.NoSuchElementException: no such element: Unable to locate element: {"method":"id","selector":"signInButton"}

#### Fix:
The fix is to switch to the signIn window by getting hold of its handle as follows:

     Set<String> windowHandles = driver.getWindowHandles();
            for(String handle : windowHandles){
            	if(!primaryWindowHandle.equalsIgnoreCase(handle)){
            		driver.switchTo().window(primaryWindowHandle);
            		driver.findElement(By.id("signInButton")).click();
            		String errors1 = driver.findElement(By.id("errors1")).getText();
                    Assert.assertTrue(errors1.contains("There were errors in your submission"));
                    driver.close();
            	}
            }

---
#### In HotelBookingTest:
#### Error:
Here, in the test class PageFactory class is being used to find the Web Elements as follows:

    @FindBy(linkText = "Hotels")
        private WebElement hotelLink;

but there was no initElements called to initialize the web elements so the test fails with a NullPointerException
#### Fix:
As a temporary fix, I have put the Web Elements and the method performing the actions on them into a HotelBookingPage.java class with a constructor as follows:
 

     public HotelBookingPage(){
    	driver = new ChromeDriver();
    	PageFactory.initElements(driver,this);
    }
From the test class, I am just instantiating the above page class and calling the method as follows:
 

    @Test
        public void shouldBeAbleToSearchForHotels() {
        	HotelBookingPage page = new HotelBookingPage();
            page.clickHotelLink();
    
        }
Again, this is a temporary fix and needs to be refactored further.

---
#### In FlightBookingTest:
#### Error:
Here, one small error was that the id of the "To" field on the page was "toTag" had to be changed to "ToTag".
Second issue was that in the following step, we were trying to get the date of 15 where as today's date is 16 so 15 was disabled and could not be selected/clicked:

    driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[3]/td[7]/a")).click();
    
#### Fix:
So, I assumed that the test case trying to select the current date so I wrote a selector as follows which fixed the test:

    driver.findElement(By.xpath(".//*[contains(@class,'ui-state-highlight')]")).click();

The current date is highlighted by default and therefore has the above css class included in its class property