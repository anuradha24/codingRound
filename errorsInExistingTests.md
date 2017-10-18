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
Once the signIn button is clicked, the signIn page is an iframe, so the signInButton was not found with an exception as follows: 

org.openqa.selenium.NoSuchElementException: no such element: Unable to locate element: {"method":"id","selector":"signInButton"}

#### Fix:
The fix is to switch to the frame using the frameid as follows:

     driver.switchTo().frame("modal_window");

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

    driver.findElement(By.xpath(".//td[contains(@class,'ui-datepicker')]/a[contains(@class,'ui-state-highlight')]")).click();

The current date is highlighted by default and therefore has the above css class included in its class property

Also, on multiple runs of this test, some times the test was failing because the auto-complete options for the source and destination were not appearing when the text was input into the from and to fields.
Inorder to make sure the test does not fail due to this, I used a loop to keep re-trying the input and wait for the auto-complete options upto 3 times as follows:

    public WebElement waitForSourceAutoCompleteOptions(String source){
    			for(int i=0;i<3;i++){
    				try{
    					return driverUtils.waitForVisibility(this.sourceOptionsList);
    				}catch(TimeoutException e){
    				this.inputFromField(source);
    			}
    		}
    			return driverUtils.waitForVisibility(this.sourceOptionsList);
    	}