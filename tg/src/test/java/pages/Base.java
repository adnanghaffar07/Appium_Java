package pages;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.microsoft.appcenter.appium.EnhancedAndroidDriver;
import com.microsoft.appcenter.appium.EnhancedIOSDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.touch.offset.PointOption;


public class Base {
	//public EnhancedIOSDriver<IOSElement> driver;
	AppiumDriver driver;
	public WebDriverWait wait;
	
	// Constructor
	public Base(AppiumDriver driver) {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		this.driver = driver;
		wait = new WebDriverWait(driver, 15);
	}
	protected MobileElement waitTillElementVisible1(MobileElement mobileElement) {
        return (MobileElement) wait.until(ExpectedConditions.visibilityOf(mobileElement));

    }
    protected MobileElement waitTillElementVisible(MobileElement mobileElement) {
        return (MobileElement) wait.until(ExpectedConditions.visibilityOf(mobileElement));

    }
	void click(By element) throws InterruptedException {
		//waitTillElementVisible(element);
		wait.until(ExpectedConditions.presenceOfElementLocated(element));
		driver.findElement(element).click();
//		WebElement ele = driver.findElementByXPath("//XCUIElementTypeOther[@name='NEXT']");
//		ele.click();
	}
	
	
	void tap(int arr[]) throws InterruptedException {
		TouchAction elem = new TouchAction(driver);
	//	elem.tap(arr[0], arr[1]).perform();
		elem.tap(new PointOption().withCoordinates(arr[0], arr[1])).perform();
	}
	
	MobileElement getElement(By element) throws InterruptedException {
		wait.until(ExpectedConditions.presenceOfElementLocated(element));
		return (MobileElement) driver.findElement(element);
		
	}
	
	String getText(By element) throws InterruptedException {
		wait.until(ExpectedConditions.presenceOfElementLocated(element));
		return driver.findElement(element).getText();
	}
	
	void assertText(By element, String text) throws InterruptedException {
		wait.until(ExpectedConditions.presenceOfElementLocated(element));
		Assert.assertEquals(driver.findElement(element).getText(), text);
	}
	
	boolean isEnabledElement(By element) throws InterruptedException {
		wait.until(ExpectedConditions.presenceOfElementLocated(element));
		Assert.assertTrue(driver.findElement(element).isEnabled());
		return true;
	}
	
	void enterText(String selector, char c) throws InterruptedException {
		MobileElement ele = (MobileElement) driver.findElementByXPath(selector);
		ele.clear();
		String s = new StringBuilder().append(c).toString();
		ele.sendKeys(s);
	}
	
	void enterString(String selector, String value) throws InterruptedException {
		Thread.sleep(2000);
		MobileElement ele = this.getElement(By.xpath(selector));
		ele.clear();
		ele.sendKeys(value);
	}
	
    public void inputStringUsingiOSNsPredicate(String iOSNsPredicate, String emailId) {
        MobileElement el = (MobileElement) driver.findElement(MobileBy.iOSNsPredicateString(iOSNsPredicate)); // name is ID in most cases
        //waitTillElementVisible(el);
        el.click();
        el.setValue(emailId);
    }
	
}
