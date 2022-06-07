package Project.Appium;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class khanacademylogin {
	AndroidDriver driver;

	@BeforeClass
	public void launchKhanAcademy() throws MalformedURLException
	{
		DesiredCapabilities capability = new DesiredCapabilities();
		capability.setCapability("deviceName", "emulator-5558");
		capability.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		capability.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "org.khanacademy.android");
		capability.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,
				"org.khanacademy.android.ui.library.MainActivity");
		capability.setCapability("unicodeKeyboard", true);
		capability.setCapability("resetKeyboard", true);
		driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capability);
	}

	//Method for scrolling down
   public void scrolldown()
	{
	TouchAction act = new TouchAction(driver);
	Dimension d = driver.manage().window().getSize();
	int width = d.width;
	int height = d.height;
	int x1 = width / 2;
	int y1 = 4 * height / 5;
	int x2 = width / 2;
	int y2 = height / 5;
	act.press(PointOption.point(x1, y1)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3)))
			.moveTo(PointOption.point(x2, y2)).release().perform();
	}
   
   
	@Test
	public void clickCampkhan() throws InterruptedException
	{
		driver.findElement(MobileBy.AccessibilityId("Search tab")).click();
		Thread.sleep(3000);
	    driver.findElements(By.className("android.widget.Button"));
		Thread.sleep(3000);
		scrolldown();
		driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().text(\"Life skills\")")).click();
	    Thread.sleep(3000);
		scrolldown();
		driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().text(\"Camp Khan\")")).click();
		Thread.sleep(3000);
		String Expected = "Camp Khan";
		String Actual = driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().text(\"Camp Khan\")")).getText();
		Assert.assertEquals(Actual, Expected);
	}

	@AfterClass
	public void quitBrowser() 
	{	
		driver.quit();
	}
}