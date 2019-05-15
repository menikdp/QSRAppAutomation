package testcase;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.PropertyUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class BaseTest {
    protected AppiumDriver driver;
    private AppiumDriverLocalService service;

    @BeforeMethod
    public void setUpAppium() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        setDesiredCapabilitiesForAndroid(capabilities);

        service = AppiumDriverLocalService.buildDefaultService();
        service.start();
        String service_url = service.getUrl().toString();
        System.out.println("Appium Service Address: " + service_url);
        driver = new AndroidDriver(new URL(service_url), capabilities);

    }

    /**
     * It will set the DesiredCapabilities for the local
     execution
     *
     * @param desiredCapabilities
     */
    private void setDesiredCapabilitiesForAndroid(DesiredCapabilities desiredCapabilities) {
        String PLATFORM_NAME = PropertyUtils.getProperty("android.platform");
        String PLARFORM_VERSION = PropertyUtils.getProperty("android.platformVersion");
        String DEVICE_NAME = PropertyUtils.getProperty("android.deviceName");
        String APP_PACKAGE = PropertyUtils.getProperty("android.appPackage");
        String APP_ACTIVITY = PropertyUtils.getProperty("android.appActivity");
        String CLEAR_SYSTEM_FILE = PropertyUtils.getProperty("android.clearSystemFile");

        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, PLATFORM_NAME);
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, PLARFORM_VERSION);
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, DEVICE_NAME);
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, APP_PACKAGE);
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, APP_ACTIVITY);
        desiredCapabilities.setCapability(MobileCapabilityType.CLEAR_SYSTEM_FILES, CLEAR_SYSTEM_FILE);
    }

    @AfterMethod
    public void tearDown() {
        try {
            this.driver.quit();
            this.service.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BeforeTest
    public abstract void setupPage();

}
