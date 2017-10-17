import com.sun.javafx.PlatformUtil;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignInTest {

    WebDriver driver;
    static{
    	setDriverPath();
    }

    @Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() {
    	driver = new ChromeDriver();
        driver.get("https://www.cleartrip.com/");
        waitFor(2000);
        String primaryWindowHandle = driver.getWindowHandle();
        driver.findElement(By.linkText("Your trips")).click();
        driver.findElement(By.id("SignIn")).click();
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
        driver.switchTo().window(primaryWindowHandle);
        driver.quit();
    }

    private void waitFor(int durationInMilliSeconds) {
        try {
            Thread.sleep(durationInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    private static void setDriverPath() {
        if (PlatformUtil.isMac()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver");
        }
        if (PlatformUtil.isWindows()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        }
        if (PlatformUtil.isLinux()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
        }
    }


}
