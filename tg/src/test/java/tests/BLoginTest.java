package tests;

//import com.microsoft.appcenter.appium.Factory;
//import com.relevantcodes.extentreports.LogStatus;

import common.providers.LoginData;

import org.junit.runners.MethodSorters;
import org.openqa.selenium.TimeoutException;

import com.relevantcodes.extentreports.LogStatus;

import org.junit.Test;
import org.junit.Assert;
import org.junit.FixMethodOrder;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BLoginTest extends BaseTest {
	
	@Test
	public void test001VerifyLoginPage() throws Exception {
		test = extent.startTest("Verify login page","Login test");
		caseId = "647";
		//login.clickOnAllowNotificationsAlert();
        test.log(LogStatus.INFO, "startTest() Clicked on Allow Notifcation Alert");
        System.out.println("Clicked on Allow Notifcation Alert");
		Assert.assertNotNull(login.getEmailField());
		test.log(LogStatus.INFO, "Verified emial field");
		Assert.assertNotNull(login.getPasswordField());
		test.log(LogStatus.INFO, "Verified password field");
		passed = true;
		extent.flush();
	}
	
	@Test
	public void test002LoginPageLogoVisible() throws Exception {
		caseId = "648";
		//login.clickOnAllowNotificationsAlert();
		Assert.assertNotNull(login.getAppLogo());
		passed = true; 
	}
	
	@Test
	public void test003LoginWithEmptyEmailPassword() throws Exception {
		caseId = "1069";
		boolean btnFound;
		//login.clickOnAllowNotificationsAlert();
		try {
			btnFound = login.getLoginBtn().isEnabled();
		} catch (TimeoutException e) {
			btnFound = false;
		}
		Assert.assertFalse(btnFound);
		passed = true; 
	}
	
	@Test
	public void test004LoginWithInvalidEmail() throws Exception {
		caseId = "1070";
		boolean btnFound;
		//login.clickOnAllowNotificationsAlert();
		login.submitLoginForm(LoginData.invalid_email, LoginData.password);
		try {
			btnFound = login.getLoginBtn().isEnabled();
		} catch (TimeoutException e) {
			btnFound = false;
		}
		Assert.assertFalse(btnFound);
		passed = true; 
	}
	
	@Test
	public void test005LoginWithSmallPassword() throws Exception {
		caseId = "1071";
		boolean btnFound;
		//login.clickOnAllowNotificationsAlert();
		login.submitLoginForm(LoginData.email, LoginData.small_password);
		try {
			btnFound = login.getLoginBtn().isEnabled();
		} catch (TimeoutException e) {
			btnFound = false;
		}
		Assert.assertFalse(btnFound);
		passed = true; 
	}
	
	@Test
	public void test006LoginWithInvalidPassword() throws Exception {
		caseId = "1072";
		boolean btnFound;
		//login.clickOnAllowNotificationsAlert();
		login.submitLoginForm(LoginData.email, LoginData.invalid_password);
		try {
			btnFound = login.getLoginBtn().isEnabled();
		} catch (TimeoutException e) {
			btnFound = false;
		}
		Assert.assertFalse(btnFound);
		passed = true; 
	}
	
	@Test
	public void test007LoginWithValidCredentials() throws Exception {
		caseId = "651";
		boolean btnFound = true;
		//login.clickOnAllowNotificationsAlert();
		login.submitLoginForm(LoginData.email, LoginData.password);
		try {
			login.clickLoginBtn();
		} catch (TimeoutException e) {
			btnFound = false;
		}
		Assert.assertTrue(btnFound);
		//login.clickOnAllowNotificationsAlert();
		dashboard.clickOnAlwaysAllowAlert();
		//login.clickOnAllowNotificationsAlert();
		Assert.assertNotNull(dashboard.getDashboardMainElement());
		passed = true; 
	}

//	@Test
//	public void test008VerifyFacebookLogin() throws Exception {
//		driver.quit();
//		driver = prepareIOSDevice();
//		wait = new WebDriverWait(driver, 15);
//		dashboard = new Dashboard(driver);
//		
//		caseId = "713";
//		login.clickOnAllowNotificationsAlert();
//		login.loginWithFacebook(LoginData.fb_email, LoginData.fb_password);
//		login.clickOnAllowNotificationsAlert();
//		dashboard.clickOnAlwaysAllowAlert();
//		Assert.assertNotNull(dashboard.getDashboardMainElement());
//		dashboard.clickOnLeftMenu();
//		dashboard.clickOnSettings();
//		dashboard.clickOnLogout();
//		Assert.assertNotNull(login.getLoginBtn());
//		passed = true;
//	}
//	
//	@Test
//	public void test009VerifyForgotPassword() throws Exception {
//		caseId = "1082";
//		login.clickOnAllowNotificationsAlert();
//		login.clickOnForgotPassword();
//		// TODO: Generate random email
//		// random_email = dummy{}@gmail.com int(time.time())
////		login.submitForgotPasswordForm(random_email);
//		login.clickSubmitForgotPasswordBtn();
//		Assert.assertNotNull(login.getTextAfterForgotPasswordBtn());
//		passed = true;
//	}
	
}

