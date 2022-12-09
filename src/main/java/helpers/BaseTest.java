package helpers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import pages.HomePage;
import pages.LoginPage;

import java.net.URL;
import java.util.Properties;

public class BaseTest {

    protected static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();
    protected static Properties prop;
    protected static HelperMethods helperMethods;
    protected static ThreadLocal<String> deviceName = new ThreadLocal<>();
    protected static ThreadLocal<String> platformName = new ThreadLocal<>();
    protected LoginPage loginPage;
    protected HomePage homePage;

    public AppiumDriver getDriver() {
        return driver.get();
    }

    public void setDriver(AppiumDriver driver) {
        this.driver.set(driver);
    }

    public String getDeviceName() {
        return deviceName.get();
    }

    public void setDeviceName(String deviceName) {
        this.deviceName.set(deviceName);
    }

    public String getPlatformName() {
        return platformName.get();
    }

    public void setPlatformName(String platformName) {
        this.platformName.set(platformName);
    }

    @Parameters({"deviceName", "platformVersion", "platformName", "virtualDevice"})
    @BeforeTest
    public void prepareTest(String deviceName, String platformVersion, String platformName, String virtualDevice) {
        helperMethods = new HelperMethods();
        AppiumDriver driver;
        setPlatformName(platformName);
        setDeviceName(deviceName);
        try {
            prop = helperMethods.readPropertiesFile("src/main/resources/config.properties");

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("platformName", platformName);
            capabilities.setCapability("appiumVersion", "1.20.2");
            capabilities.setCapability("newCommandTimeout", prop.getProperty("newCommandTimeout"));
            capabilities.setCapability("autoGrantPermissions", prop.getProperty("autoGrantPermissions"));
            if (virtualDevice.equalsIgnoreCase("false")) {
                capabilities.setCapability("launchTimeout", prop.getProperty("launchTimeout"));
                capabilities.setCapability("pCloudy_DeviceFullName", deviceName);
                capabilities.setCapability("pCloudy_Username", prop.getProperty("pcloudy_username"));
                capabilities.setCapability("pCloudy_ApiKey", prop.getProperty("pcloudy_apikey"));
                capabilities.setCapability("pCloudy_DurationInMinutes", prop.getProperty("pCloudy_DurationInMinutes"));
                capabilities.setCapability("pCloudy_WildNet", prop.getProperty("pcloudy_wildnet"));
                capabilities.setCapability("pCloudy_EnableVideo", prop.getProperty("pcloudy_enablevideo"));
                capabilities.setCapability("pCloudy_EnablePerformanceData", prop.getProperty("pcloudy_enableperformancedata"));
                capabilities.setCapability("pCloudy_EnableDeviceLogs", prop.getProperty("pcloudy_enabledevicelogs"));
            }
            switch (platformName) {
                case "Android":
                    capabilities.setCapability("platformVersion", platformVersion);
                    capabilities.setCapability("automationName", prop.getProperty("android_automation_name"));
                    capabilities.setCapability("pCloudy_ApplicationName", "app-dev.apk");
                    capabilities.setCapability("appPackage", "sg.gov.hpb.pph.h365.dev");
                    capabilities.setCapability("appActivity", "com.hpb.H365AppContainer.SplashActivity");
                    if (virtualDevice.equalsIgnoreCase("true")) {
                        capabilities.setCapability("avd", deviceName);
                        capabilities.setCapability("avdLaunchTimeout", prop.getProperty("launchTimeout"));
                        capabilities.setCapability("app", prop.getProperty("android_app_path"));
                        driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
                    } else
                        driver = new AndroidDriver(new URL("https://hats.pcloudy.com/appiumcloud/wd/hub"), capabilities);
                    break;

                case "ios":
                    //capabilities.setCapability("acceptAlerts", true);
                    capabilities.setCapability("autoDissmissAlerts", "true");
                    capabilities.setCapability("automationName", prop.getProperty("XCUITest"));
                    capabilities.setCapability("pCloudy_ApplicationName", "H365AppContainer.ipa");
                    capabilities.setCapability("bundleId", prop.getProperty("bundle_id"));

                    if (virtualDevice.equalsIgnoreCase("true")) {
                        capabilities.setCapability("app", prop.getProperty("ios_app_path"));
                        capabilities.setCapability("simulatorStartupTimeout", prop.getProperty("launchTimeout"));
                        driver = new IOSDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
                    } else
                        driver = new IOSDriver(new URL("https://hats.pcloudy.com/appiumcloud/wd/hub"), capabilities);
                    break;

                default:
                    throw new RuntimeException("Invalid platform! - " + platformName);
            }
            setDriver(driver);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterTest(alwaysRun = true)
    void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
        }
    }
}
