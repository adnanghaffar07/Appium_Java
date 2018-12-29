package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import com.microsoft.appcenter.appium.EnhancedAndroidDriver;
import com.microsoft.appcenter.appium.EnhancedIOSDriver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSElement;

public class Login extends Base {

	public Login(AppiumDriver driver) {
		super(driver);
	}
	String elementPredicateString;
	public void elementPredciate(String elementPredicateString){
		MobileElement elemenetPredicate = (MobileElement) driver.findElement(MobileBy.iOSNsPredicateString(elementPredicateString)); 

	}

	
	public void clickOnAllowNotificationsAlert() throws InterruptedException {
		String AllowElement = "type == 'XCUIElementTypeButton' AND name == 'Allow'";
		Thread.sleep(5000);
		MobileElement el = (MobileElement) driver.findElement(MobileBy.iOSNsPredicateString(AllowElement)); 
		try {
			waitTillElementVisible(el);
			el.click();
			System.out.println("Allow notification found");
			// click(By.xpath("//XCUIElementTypeButton[@name='Allow']"));
		} catch (TimeoutException e) {
			System.out.println("Allow notification not found");
		}
	}
	
	public void clickOnNext() throws InterruptedException {
		String NextButton = "type == 'XCUIElementTypeOther' AND name == 'NEXT'";
		MobileElement elNextButton = (MobileElement) driver.findElement(MobileBy.iOSNsPredicateString(NextButton)); 
		waitTillElementVisible(elNextButton);
		elNextButton.click();
		//click(By.xpath("//XCUIElementTypeOther[@name='NEXT']"));
	}
	
	public void clickOnBack() throws InterruptedException {
		click(By.xpath("(//XCUIElementTypeOther[@name=''])[2]"));
	}
	
	public void submitLoginForm(String email, String password) throws InterruptedException {
		MobileElement emailElem = getElement(By.xpath("//XCUIElementTypeTextField"));
		emailElem.clear();
		emailElem.sendKeys(email);
		MobileElement passwordElem = getElement(By.xpath("//XCUIElementTypeSecureTextField"));
		passwordElem.clear();
		passwordElem.sendKeys(password);
	}
	
	public void clickOnForgotPassword() throws InterruptedException {
		int[] location = new int[]{116, 47};
		tap(location);
	}
	
	public void submitForgotPasswordForm(String email) throws InterruptedException {
		MobileElement emailElem = getElement(By.xpath("//XCUIElementTypeTextField"));
		emailElem.clear();
		emailElem.sendKeys(email);
	}
	
	public void clickSubmitForgotPasswordBtn() throws InterruptedException {
		String SubmitForgotPasswordBtn = "type == 'XCUIElementTypeButton' AND name == 'Enviar'";
		MobileElement elSubmitForgotPasswordBtn = (MobileElement) driver.findElement(MobileBy.iOSNsPredicateString(SubmitForgotPasswordBtn)); 
		waitTillElementVisible(elSubmitForgotPasswordBtn);
		elSubmitForgotPasswordBtn.click();
		//click(By.xpath("//XCUIElementTypeButton[@name='Enviar']"));
	}
	
	public MobileElement getTextAfterForgotPasswordBtn() throws InterruptedException {
		return getElement(By.xpath("//XCUIElementTypeStaticText[@name='Confira seu e-mail']"));
	}
	
	public MobileElement getFacebookContinueBtn() throws InterruptedException {
		return getElement(By.xpath("//XCUIElementTypeButton[@name='Open']"));
	}
	
	public MobileElement getAppLogo() throws InterruptedException {
		return getElement(By.xpath("//XCUIElementTypeImage"));
	}
	
	public MobileElement getEmailField() throws InterruptedException {
		return getElement(By.xpath("//XCUIElementTypeTextField"));
	}
	
	public MobileElement getPasswordField() throws InterruptedException {
		return getElement(By.xpath("//XCUIElementTypeSecureTextField"));
	}
	
	public MobileElement getLoginBtn() throws InterruptedException {
		return getElement(By.xpath("//XCUIElementTypeButton[@name='Entrar']"));
	}
	
	public void clickLoginBtn() throws InterruptedException {
		click(By.xpath("//XCUIElementTypeButton[@name='Entrar']"));
	}
	
	public void loginWithFacebook() throws InterruptedException {
		
	}

}
