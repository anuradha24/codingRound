## Code Review Comments:
#### Code Repetition:
There is a lot of code repetition for example, the method to set the driver path is present in every test class. This can be moved to a common class.
Similarly, the code to instantiate the driver instance, connecting the url and quitting the driver is common to all the tests and therefore should be placed in a method common to all tests

    driver = new ChromeDriver();
    driver.get("https://www.cleartrip.com/");
    driver.quit();

----------


#### Must use Selenium's Waiting Mechanisms:
There is a wait in almost every test after the get(url) method is called on the driver, which will not be necessary as the get method itself will block until the load of the page is complete. 

    driver.get("https://www.cleartrip.com/");
    waitFor(2000); 
    
Also, in the implementation of the waitFor method, Thread.sleep() is being used which should be completely avoided in the test cases as this will block the current thread's execution until the given time whether or not it is required. Alternatively, we can use selenium's implicit or explicit wait mechanisms based on the requirement.

Note: I have added explicit waits to all the web elements before any action is being performed on them in the tests to make sure the tests do not fail due to such issues

----------


#### Code should be re-factored to use Page Object Model(POM):
The code to find the element on a web page and performing some action on it is included in the test itself which should not be the case considering re-usability and maintenance. A test case's flow may span across multiple web pages but including all of the actions performed on the web elements of the pages in the test itself has the following disadvantages:

 - Maintenance: The selector to identify a web element may change and then we will have check which tests are failing due to the change and change the code in multiple places. If we use the Page Object Model (POM), then the selector can be changed only in one place
 - Re-usability: With the absence of POM, the same code to find an element on a given web page and performing some action on it should be repeated in all the test cases that involve the element in the test case flow 
 


----------


#### Test Data and other required input should be separated from test code:
Lets take the hotelSearchTest for example:

    driver.get("https://www.cleartrip.com/");
    hotelLink.click();
    localityTextBox.sendKeys("Indiranagar, Bangalore");
    new Select(travellerSelection).selectByVisibleText("1 room, 2 adults");
   
Here, first thing is the connection base url. In this case, we do not have any other connection properties(like login details) but these should be separated out from the test code for the following reasons:

 - Readability: It makes it easier for someone else reading the code to make note of all the connection properties that are required for a given test
 - Reuse: All tests related to the same product will need the same base url along with login details(if any) so instead of repeating them, it is better to separate them out

There are 2 ways by which this can be achieved:

 1. Static and final members of the test class
 2. Properties file

Apart from connection details we have hard-coded data here like "Indiranagar, Bangalore" and "1 room, 2 adults" which represent the input test data to the above test. Data like this should not be hard-coded in the tests for the following reasons:
- Readability & Parameterization: It makes it easier for someone else reading the code to make note of the test data against which a particular test is run. Also, going forward if we would like the run the same test for different input data sets, then seperating it out is the best approach to take
- Maintenance: Test data is expected to change more frequently than the test itself. If the test data is hardcoded as above then every time the test data changes we will have to go and change the test code which is not recommended

Note: The approach that I have taken to separate out the test data is to use csv files and parameterizing the testng tests.