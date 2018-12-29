package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.microsoft.appcenter.appium.EnhancedAndroidDriver;
import com.microsoft.appcenter.appium.EnhancedIOSDriver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.iOSFindBy;

public class Registration extends Base {

    @iOSFindBy(xpath = "//XCUIElementTypeButton[@name='Allow']")
    private MobileElement allowNotification; 
    
    @iOSFindBy(xpath = "//XCUIElementTypeApplication[@name='Trânsito+gentil']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeWebView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeTextField")
    private String emailField; 
    
    @iOSFindBy(xpath = "//XCUIElementTypeOther[3]/XCUIElementTypeSecureTextField")
    private String passwordField; 
    
    protected MobileElement waitTillElementVisible(MobileElement mobileElement) {
        return (MobileElement) wait.until(ExpectedConditions.visibilityOf(mobileElement));

    }
    
    protected void clickElement(MobileElement mobileElement) {
        mobileElement = waitTillElementVisible(mobileElement);
        mobileElement.click();
    }
    
    public void inputString(MobileElement mobileElement, String stringToBeEntered) {
        mobileElement = waitTillElementVisible(mobileElement);
        mobileElement.click();
        mobileElement.sendKeys(stringToBeEntered);
    }
    
    protected void enterKeys(MobileElement mobileEement, String text){
    	mobileEement.sendKeys(text);
    }
   
    public void inputStringUsingiOSNsPredicate(String iOSNsPredicate, String emailId) {
        MobileElement el = (MobileElement) driver.findElement(MobileBy.iOSNsPredicateString(iOSNsPredicate)); // name is ID in most cases
        waitTillElementVisible(el);
        el.click();
        el.setValue(emailId);
    }
   
	public Registration(AppiumDriver driver) {
		super(driver);
	}
	
	public void clickOnAllowNotificationsAlert() throws InterruptedException {
    	clickElement(allowNotification);

		//driver.findElement(allowNotification).click();
		//click(allowNotification);
	}
	

	
    private String emailIdTextBox = "type == 'XCUIElementTypeTextField' AND value == 'Digite seu e-mail' AND visible == 1";

	public void submitRegistrationForm(String email, String password) throws InterruptedException {
		MobileElement elementEmail = (MobileElement) driver.findElementByXPath("//XCUIElementTypeOther[3]/XCUIElementTypeSecureTextField");
		inputString(elementEmail, "send Keys");
		//elementEmail.sendKeys("Hello world!");


    	//clickElement(emailField);
		//inputStringUsingiOSNsPredicate(emailIdTextBox,"email id");
		//enterKeys(emailField, email);
		MobileElement elementPassword = (MobileElement) driver.findElementByXPath("//XCUIElementTypeOther[2]/XCUIElementTypeTextField");
		//inputString(elementPassword, "send Keys password");
    	//clickElement(passwordField);
		//enterKeys(passwordField, password);
		enterString("//XCUIElementTypeTextField", email);
		enterString("//XCUIElementTypeOther[3]/XCUIElementTypeSecureTextField", password);
		enterString("//XCUIElementTypeOther[4]/XCUIElementTypeSecureTextField", password);
	}
	
	public void clickOnNext() throws InterruptedException {
		MobileElement NextButton = (MobileElement) driver.findElementsByXPath("//XCUIElementTypeOther[@name='NEXT']");
		NextButton.click();
		//click(By.xpath("//XCUIElementTypeOther[@name='NEXT']"));
	}
	
	public void clickOnBack() throws InterruptedException {
		
		MobileElement BackButton = (MobileElement) driver.findElementsByXPath("(//XCUIElementTypeOther[@name=''])[2]");
		BackButton.click();
		//click(By.xpath("(//XCUIElementTypeOther[@name=''])[2]"));
	}
	
	public MobileElement getValidateCodeBtn() throws InterruptedException {
		return getElement(By.xpath("(//XCUIElementTypeOther[@name='VALIDATE CODE'])[2]"));
	}
	
	public void enterActivationCode(String code) throws InterruptedException {
		int j = 0;
		for(int i=1, n=code.length(); i<=n; i++) {
			enterText(String.format("//XCUIElementTypeOther/XCUIElementTypeOther[%d]/XCUIElementTypeSecureTextField", i), code.charAt(j));
			j += 1;
		}
	}
	
	public MobileElement getSubmitBtn() throws InterruptedException {
		return getElement(By.xpath("//XCUIElementTypeButton[@name='Cadastrar']"));
	}
	
	public void clickOnRegistrationLink() throws InterruptedException {
		try {
			click(By.xpath("//XCUIElementTypeStaticText[@name='Cadastrar']"));
			System.out.println("Registration Link found");
		} finally {
			System.out.println("Registration Link final block");
		}
	}
	
	public MobileElement getRegistrationLink() throws InterruptedException {
		return getElement(By.xpath("//XCUIElementTypeStaticText[@name='Cadastrar']"));
	}
	
	
	
	public void clickOnSubmit() throws InterruptedException {
		click(By.xpath("//XCUIElementTypeButton[@name='Cadastrar']"));
	}
	 
	public void submitCpfScreen(String cpf, String data) throws InterruptedException {
		enterString("//XCUIElementTypeOther[3]/XCUIElementTypeTextField", cpf);
		enterString("//XCUIElementTypeOther[4]/XCUIElementTypeTextField", data);
		click(By.xpath("//XCUIElementTypeButton[@name='Enviar']"));
	}
	
	public MobileElement getSubmitActivationBtn() throws InterruptedException {
		return getElement(By.xpath("//XCUIElementTypeButton[@name='Enviar']"));
	}
	
	public MobileElement getOkBtnAfterActivationForm() throws InterruptedException {
		return getElement(By.xpath("//XCUIElementTypeButton[@name='OK']"));
	}
	
	public void clickOnLoginLink() throws InterruptedException {
		try {
			click(By.xpath("//XCUIElementTypeTextField"));
			click(By.xpath("//XCUIElementTypeStaticText[@name='Acessar']"));
			System.out.println("clicked on Login link");
		} catch (TimeoutException e) {
			System.out.println("Login link not found");
		}
		
	}
	
	public MobileElement getConfirmatoinAfterCpf() throws InterruptedException {
		return getElement(By.xpath("//XCUIElementTypeStaticText[@name='Parabéns!']"));
	}
	
	public MobileElement getErrorDialogAfterCpf() throws InterruptedException {
		return getElement(By.xpath("//XCUIElementTypeStaticText[@name='Erro']"));
	}
	
	public void clickOnAcceptAgreement() throws InterruptedException {
		click(By.xpath("//XCUIElementTypeButton[@name='Aceitar']"));
	}
	
	public void clickOnOkAfterAgreementAlert() throws InterruptedException {
		click(By.xpath("(//XCUIElementTypeButton[@name='Pular'])[1]"));
	}

}
