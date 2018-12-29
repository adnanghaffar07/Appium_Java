package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.*;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Properties;

public class AppiumDriverBuilder {

    protected static AppiumDriverLocalService service;
    protected static final int PORT = 4723;

    public void build(Properties properties, String deviceName, String wdaLocalPort) throws Exception {
//        final String ip = startAppiumServer();
//
//        if (service == null || !service.isRunning()) {
//            throw new AppiumServerHasNotBeenStartedLocallyException("An appium server node is not started!");
//        }

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, properties.getProperty("platfromVersion"));
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);


        if (properties.getProperty("platfrom").equalsIgnoreCase("ios")) {
           // capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
            capabilities.setCapability(IOSMobileCapabilityType.LAUNCH_TIMEOUT, 500000);
            // capabilities.setCapability(MobileCapabilityType.UDID, "cffec8a93dc58be41832797547f349fd714a9320");
            capabilities.setCapability(IOSMobileCapabilityType.AUTO_ACCEPT_ALERTS, Boolean.TRUE);
            capabilities.setCapability(MobileCapabilityType.APP, System.getProperty("appPath"));

            //capabilities.setCapability(IOSMobileCapabilityType.SHOW_IOS_LOG, true);)
            //capabilities.setCapability(IOSMobileCapabilityType.WDA_LOCAL_PORT, Integer.valueOf(wdaLocalPort));
            //capabilities.setCapability("clearSystemFiles", true);
           // capabilities.setCapability("wdaStartupRetryInterval", 1000);
          //  capabilities.setCapability("useNewWDA", true);
            //capabilities.setCapability("waitForQuiescence", false);
           // capabilities.setCapability("shouldUseSingletonTestManager", false);
            //return new AppiumDriver(new URL(properties.getProperty("url")), capabilities);
        } else if (properties.getProperty("platfrom").equalsIgnoreCase("android")) {
            capabilities.setCapability(MobileCapabilityType.UDID, properties.getProperty("device.udid"));
            capabilities.setCapability(MobileCapabilityType.FULL_RESET, true);
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
            capabilities.setCapability(AndroidMobileCapabilityType.UNICODE_KEYBOARD, true);
            capabilities.setCapability(AndroidMobileCapabilityType.RESET_KEYBOARD, true);
            capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, properties.getProperty("app.package.name"));
            capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, properties.getProperty("app.activity.name"));

            return;
        } else {
            throw new Exception("Unable to read device platform.");
        }
    }


    /**
     * Starts a local server.
     *
     * @return ip of a local host
     * @throws UnknownHostException when it is impossible to get ip address of a local host
     */
    public static String startAppiumServer() throws UnknownHostException {
        getService();
        InetAddress inetAddress = InetAddress.getLocalHost();
        return inetAddress.getHostAddress();
    }

    public static AppiumDriverLocalService getService() {
        service = new AppiumServiceBuilder().usingPort(PORT).build();
        service.start();
        return service;
    }

}