package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import com.microsoft.appcenter.appium.EnhancedIOSDriver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSElement;

public class Dashboard extends Base {

	public Dashboard(AppiumDriver driver) {
		super(driver);
	}
	
	public MobileElement getDashboardMainElement() throws InterruptedException {
	    String DashboardText = "type == 'XCUIElementTypeOther' AND name == 'Dashboard'";

        MobileElement el = (MobileElement) driver.findElement(MobileBy.iOSNsPredicateString(DashboardText)); // name is ID in most cases

		//return getElement(By.xpath("//XCUIElementTypeOther[@name='Dashboard']"));
        return el;
	}
	
	public void clickOnLeftMenu() throws InterruptedException {
		click(By.xpath("//XCUIElementTypeButton[@name='']"));
	}
	
	public MobileElement getLogoutBtn() throws InterruptedException {
		return getElement(By.xpath("//XCUIElementTypeStaticText[@name='Sair']"));
	}
	
	public void clickOnSettings() throws InterruptedException {
		int retries = 3;
		while (retries > 0) {
			try {
				int[] location = new int[]{250, 43};
				tap(location);
				if (this.getLogoutBtn() != null) {
					break;
				}
			} catch (Exception e) {
				Thread.sleep(2000);
				retries -= 1;
			}
		}
	}
	
	public void clickOnLogout() throws InterruptedException {
		click(By.xpath("//XCUIElementTypeStaticText[@name='Sair']"));
	}
	
	public void clickOnAlwaysAllowAlert() throws InterruptedException {
		//String AlwaysAllowAlert = "type == 'XCUIElementTypeButton' AND name == 'Always Allow'";
		//MobileElement elAlwaysAllowAlert = (MobileElement) driver.findElement(MobileBy.iOSNsPredicateString(AlwaysAllowAlert));
		try {
			//waitTillElementVisible(elAlwaysAllowAlert);
			//elAlwaysAllowAlert.click();
			System.out.print("Always Allow alert found");
			click(By.xpath("//XCUIElementTypeButton[@name=\"Always Allow\"]"));
		} catch (TimeoutException e) {
			System.out.print("Always Allow alert not found");
		}
	}
	
    private String emailIdTextBox = "type == 'XCUIElementTypeOther' AND name == 'Email Email' AND visible == 1";
    public void enterUserEmailId(String emailId) {
        //System.out.println(driver.getPageSource());
        inputStringUsingiOSNsPredicate(emailIdTextBox, emailId);
        //return this;
    }

}
