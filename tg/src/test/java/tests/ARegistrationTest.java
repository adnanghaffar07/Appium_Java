package tests;

//import com.microsoft.appcenter.appium.Factory;
import com.relevantcodes.extentreports.LogStatus;

import common.CPFGenerator;
import common.providers.LoginData;
import common.providers.RegistrationData;
import pages.Dashboard;

import org.junit.rules.TestWatcher;
import org.junit.runners.MethodSorters;
import org.junit.Rule;
import org.junit.Test;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.FixMethodOrder;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ARegistrationTest extends BaseTest {
	
	@Test
	public void test001VerifyRegistrationPage() throws Exception {
		test = extent.startTest("Verify Registration page","Login test");
		caseId = "653";
		login.clickOnAllowNotificationsAlert();
		reg.clickOnRegistrationLink();
		Assert.assertNotNull(reg.getSubmitBtn());
		test.log(LogStatus.INFO, "Verified password field");
		passed = true;
		extent.flush();
	}
	
	@Test
	public void test002VerifyRegistrationInvalidEmail() throws Exception {
		caseId = "1073";
		//login.clickOnAllowNotificationsAlert();
		reg.clickOnLoginLink();
		reg.clickOnRegistrationLink();
		 driver.getKeyboard(); 
		reg.submitRegistrationForm(LoginData.invalid_email, LoginData.password);
		Assert.assertFalse(reg.getSubmitBtn().isEnabled());
		passed = true;
	}
	
	@Test
	public void test003VerifyRegistrationInvalidPassword() throws Exception {
		caseId = "1074";
		//login.clickOnAllowNotificationsAlert();
		reg.clickOnLoginLink();
		reg.clickOnRegistrationLink();
		reg.submitRegistrationForm(LoginData.email, LoginData.invalid_password);
		Assert.assertFalse(reg.getSubmitBtn().isEnabled());
		passed = true;
	}
	
	@Test
	public void test004RegisterNewUser() throws Exception {
		caseId = "1075";
		//login.clickOnAllowNotificationsAlert();
		reg.clickOnLoginLink();
		Assert.assertNotNull(reg.getRegistrationLink());
		passed = true;
	}
	
	@Test
	public void test005RegisterNewUser() throws Exception {
		caseId = "654";
		//login.clickOnAllowNotificationsAlert();
		reg.clickOnRegistrationLink();
		long time = System.currentTimeMillis();
		String random_email = String.format("dummy%d@gmail.com", time);
		reg.submitRegistrationForm(random_email, LoginData.password);
		reg.clickOnSubmit();		
		passed = true;
	}
//	
	@Test
	public void test006VerifyInvalidCPF() throws Exception {
		caseId = "1077";
		reg.submitCpfScreen("58740411841", RegistrationData.dob);
		Assert.assertNotNull(reg.getErrorDialogAfterCpf());
		reg.getOkBtnAfterActivationForm().click();
		passed = true;
	}
	
	@Test
	public void test007VerifyInvalidDOB() throws Exception {
		caseId = "1078";
		System.out.println(CPFGenerator.generateCPF());		
		reg.submitCpfScreen(CPFGenerator.generateCPF(), RegistrationData.invalid_dob);
		Assert.assertNotNull(reg.getErrorDialogAfterCpf());
		reg.getOkBtnAfterActivationForm().click();
		passed = true;
	}
	
	@Test
	public void test008VerifyEmptyCPF() throws Exception {
		caseId = "1079";
		reg.submitCpfScreen("", RegistrationData.invalid_dob);
		Assert.assertNotNull(reg.getErrorDialogAfterCpf());
		reg.getOkBtnAfterActivationForm().click();
		passed = true;
	}
	
	@Test
	public void test009EnterActivationCodeDOB() throws Exception {
		caseId = "655";
		reg.submitCpfScreen(CPFGenerator.generateCPF(), RegistrationData.dob);
		Assert.assertNotNull(reg.getConfirmatoinAfterCpf());
		reg.getOkBtnAfterActivationForm().click();
		passed = true;
	}
	
}

