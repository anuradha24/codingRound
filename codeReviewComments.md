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
    
Also, in the implementation of the waitFor method, Thread.sleep() is being used which should be completely avoided in the test cases as this will block the current thread's execution until the given time whether or not it is required. Alternatively, we can use selenium's implicit or explicit wait mechanisms based on the requirement

----------


#### Code should be re-factored to use Page Object Model(POM):
The code to find the element on a web page and performing some action on it is included in the test itself which should not be the case considering re-usability and maintenance. A test case's flow may span across multiple web pages but including all of the actions performed on the web elements of the pages in the test itself has the following disadvantages:

 - Maintenance: The selector to identify a web element may change and then we will have check which tests are failing due to the change and change the code in multiple places. If we use the Page Object Model (POM), then the selector can be changed only in place.
 - Re-usability: With the absence of POM, the same code to find an element on a given web page and performing some action on it should be repeated in all the test cases that involve the element in the test case flow 