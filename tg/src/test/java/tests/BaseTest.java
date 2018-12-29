package tests;

import com.microsoft.appcenter.appium.Factory;
import com.relevantcodes.extentreports.LogStatus;

import common.Config;
import common.TestRail;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.gurock.testrail.APIException;
//import com.microsoft.appcenter.appium.EnhancedAndroidDriver;
//import com.microsoft.appcenter.appium.EnhancedIOSDriver;
import org.junit.rules.TestWatcher;
import org.junit.Rule;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;

import org.json.simple.JSONObject;
import org.junit.*;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.support.ui.WebDriverWait;

import pages.Dashboard;
import pages.Login;
import pages.Registration;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class BaseTest {	
	//public static EnhancedIOSDriver<IOSElement> driver;
	public static AppiumDriver driver;
   // protected WebDriverWait wait;
    public static Registration reg;
    public static Login login;
    public static Dashboard dashboard;
    public TestRail obj;
    public boolean passed;
    public String caseId;
    public long currentTestRailRunId;
    public static ExtentReports extent;
	public static ExtentTest test;
	
	private static AppiumServiceBuilder builder;
	private static AppiumDriverLocalService service;


    
    @Rule
    public TestWatcher watcher = Factory.createWatcher();
    
    public static DesiredCapabilities setIOSCapabilities() throws MalformedURLException {
    	File appDir = new File("apps");
		File app = new File(appDir, "PortoBeta_Portuguese.app.zip");
    	DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("deviceName", "iPhone X");
		caps.setCapability("platformName", "iOS");
		caps.setCapability("platformVersion", "11.2");		// 10.3
		caps.setCapability("automationName", "XCUITest");
		//caps.setCapability("unicodeKeyboard", "false");
		//caps.setCapability("resetKeyboard", "false");
		caps.setCapability("app", app.getAbsolutePath());
		caps.setCapability("autoAcceptAlerts", true);

		caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "60");
		
		/*//Build the Appium service
		int port = 4723;
		builder = new AppiumServiceBuilder();
		builder.withIPAddress("127.0.0.1");
		builder.usingPort(port);
		builder.withCapabilities(caps);
		builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
		builder.withArgument(GeneralServerFlag.LOG_LEVEL,"error");
		
		//Start the server with the builder
		service = AppiumDriverLocalService.buildService(builder);
		
		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);
			serverSocket.close();
		} catch (IOException e) {
			//If control comes here, then it means that the port is in use
			isServerRunning = true;
		} finally {
			serverSocket = null;
		}
		if (!isServerRunning) {
			System.out.println("in if condition");
			service.start();
		}
		else{
			System.out.println("Appium Server already running on Port - " + port);
		}*/
		
		return caps;
    }
    
    public static AppiumDriver prepareIOSDevice() throws MalformedURLException {
		DesiredCapabilities caps;
		caps = setIOSCapabilities();
		return Factory.createIOSDriver(new URL("http://localhost:4723/wd/hub"), caps);
	}
    
    public static AppiumDriver<MobileElement> prepareAndroidDevice() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        File appDir = new File("apps");
		File app = new File(appDir, "app-release.apk");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "6.0");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Test Device");
        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 7913);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        URL url = new URL("http://localhost:4723/wd/hub");
        return Factory.createAndroidDriver(url, capabilities);
    }
    
    @Before
    public void setUp() throws InterruptedException, IOException, APIException {
    	if (driver == null) {
    		driver = prepareIOSDevice();
    	}
//        driver = prepareAndroidDevice();
       // driver.label("App Launched");
        reg = new Registration(driver);
        login = new Login(driver);
        dashboard = new Dashboard(driver);
        obj = new TestRail();
        if (Config.shouldLogOnTestRail) {
        	JSONObject currentRun = TestRail.addRun(Config.projectName);
        	this.currentTestRailRunId = (Long) currentRun.get("id");
        }
        extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/TestResults.html", true);
    }

    @After
    public void tearDown() throws Exception {
    	if (Config.shouldLogOnTestRail) {
    		String msg = this.passed ? "Test Passes": "Test Failed";
        	obj.updateTestRail(this.caseId, this.currentTestRailRunId, this.passed, msg);
        }
    	//driver.label("Test tear down called");
//        driver.quit();
    }
    
//    @BeforeClass
//    public static void beforeClass() throws MalformedURLException {
//    	driver = prepareIOSDevice();
//    	driver.label("App Launched");
//        reg = new Registration(driver);
//        login = new Login(driver);
//        dashboard = new Dashboard(driver);    	
//    }
//    
    @AfterClass 
    public static void afterClass() {
    	if (driver != null) {
//    	driver.label("Stopping App");
    		//driver.resetApp();
    		System.out.println("Close the app");
    		driver.closeApp();
    		driver.quit();
    	}
//    	extent.endTest(test);
//        test.log(LogStatus.INFO, "endTest() method will stop capturing information about the test log");
//        extent.flush();
//        test.log(LogStatus.INFO, "flush() method of ExtentReports wil push/write everything to the document");
//        test.log(LogStatus.INFO, "close() method will clear/close all resource of the ExtentReports object");
//        extent.close();
    }
}

