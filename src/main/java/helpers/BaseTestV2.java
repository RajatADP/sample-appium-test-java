package helpers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.ios.options.wda.SupportsXcodeCertificateOptions;
import io.appium.java_client.ios.options.wda.XcodeCertificate;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import pages.HomePage;
import pages.ViewsPage;

import java.io.File;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import static helpers.HelperMethods.readPropertiesFile;


public class BaseTestV2 {

    protected static AppiumDriver driver;
    protected static Properties prop;
    protected static ThreadLocal<String> deviceName = new ThreadLocal<>();
    protected static ThreadLocal<String> platformName = new ThreadLocal<>();
    protected HomePage homePage;

    protected ViewsPage viewsPage;

    protected AppiumDriverLocalService service;

    public static AppiumDriver getDriver() {
        return driver;
    }

    public static void setDriver(AppiumDriver driver) {
        BaseTestV2.driver = driver;
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
        setPlatformName(platformName);
        setDeviceName(deviceName);
        try {
//            service = startAppiumServer();
//            service.start();

            prop = readPropertiesFile("src/main/resources/config.properties");

            switch (platformName) {
                case "Android":
                    UiAutomator2Options options = new UiAutomator2Options();
//                    options.setApp(prop.getProperty("android_app_path_cloud"));
//                    options.setPlatformVersion(platformVersion);
//                    options.setAppPackage(prop.getProperty("android_package"));
//                    options.setAppActivity(prop.getProperty("android_activity"));
                    //real android device connect to laptop
                    if (virtualDevice.equalsIgnoreCase("false")) {
//                        options.setDeviceName("Android Device");
//                        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
//                        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

                        options.setCapability("pCloudy_Username", "rajat.mishra@thoughtworks.com");
                        options.setCapability("pCloudy_ApiKey", "fnqhxwjsvsrzkdsmgzc7r4x9");
                        options.setCapability("pCloudy_DurationInMinutes", 5);
                        options.setCapability("newCommandTimeout", 600);
                        options.setCapability("launchTimeout", 90000);
                        options.setCapability("pCloudy_DeviceFullName", "SAMSUNG_GalaxyNote20_Android_13.0.0_765c8");
                        options.setCapability("platformVersion", "13.0.0");
                        options.setCapability("platformName", "Android");
                        options.setCapability("automationName", "uiautomator2");
                        options.setCapability("pCloudy_ApplicationName", "ApiDemos-debug.apk");
                        options.setCapability("appPackage", prop.getProperty("android_package"));
                        options.setCapability("appActivity", prop.getProperty("android_activity"));
                        options.setCapability("pCloudy_WildNet", "false");
                        options.setCapability("pCloudy_EnableVideo", "true");
                        options.setCapability("pCloudy_EnablePerformanceData", "true");
                        options.setCapability("pCloudy_EnableDeviceLogs", "false");
                    }

                    if (virtualDevice.equalsIgnoreCase("true")) {
                        options.setAvd(deviceName);
                        options.setAvdLaunchTimeout(Duration.ofSeconds(20));
                        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
                        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                    } else
                        driver = new AndroidDriver(new URL("https://device.pcloudy.com/appiumcloud/wd/hub"), options);
                    break;
                case "ios":
                    //for ios cloud device
                    /*brew install libimobiledevice
                    brew install ios-deploy

                    capabilities.setCapability("updateWDABundleId", prop.getProperty("bundle_id"));
                    capabilities.setCapability("udid", prop.getProperty("bundle_id"));
                    capabilities.setCapability("xcodeOrgId", prop.getProperty("bundle_id"));
                    capabilities.setCapability("xcodeCertificate", prop.getProperty("iphone Develper"));
                     * */

                    XCUITestOptions xcuiTestOptions = new XCUITestOptions();
                    xcuiTestOptions.setApp(prop.getProperty("ios_app_path"));
                    xcuiTestOptions.setPlatformVersion(platformVersion);
                    //real ios device connect to laptop
                    if (virtualDevice.equalsIgnoreCase("false")) {
                        xcuiTestOptions.setDeviceName("iOS Device");
                        xcuiTestOptions.setXcodeCertificate(new XcodeCertificate("", SupportsXcodeCertificateOptions.DEFAULT_XCODE_SIGNING_ID));
                        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), xcuiTestOptions);
                        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                    }
                    if (virtualDevice.equalsIgnoreCase("true")) {
                        xcuiTestOptions.setDeviceName(deviceName);
                        xcuiTestOptions.setWdaLaunchTimeout(Duration.ofSeconds(20000)); // webdriver agent therefroe takes time
                        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), xcuiTestOptions);
                        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                    }

                    else
                        driver = new IOSDriver(new URL("https://device.pcloudy.com/appiumcloud/wd/hub"), xcuiTestOptions);
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
//            service.stop();
        }
    }

    public AppiumDriverLocalService startAppiumServer() {
        AppiumServiceBuilder builder = new AppiumServiceBuilder();
        builder
                .withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
                .usingDriverExecutable(new File("/usr/local/opt/node@16/bin/node"))
                .withIPAddress("127.0.0.1")
                .usingPort(4723)
                .withLogFile(new File("appiumLogs.txt"))
                .withTimeout(Duration.ofSeconds(10000))
                .withArgument(GeneralServerFlag.LOG_LEVEL, "debug");

        return AppiumDriverLocalService.buildService(builder);
    }
}
