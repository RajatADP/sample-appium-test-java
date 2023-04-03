package pages;

import helpers.HelperMethods;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class HomePage extends HelperMethods{

//    private HelperMethods helperMethods = new HelperMethods();
    private AppiumDriver driver;

//    public static By appHomeTitle = MobileBy.xpath("//android.widget.TextView[contains(@text, 'example')]");

    public static  By preferenceLink = AppiumBy.accessibilityId("Preference");
    public static By graphicsLink = AppiumBy.accessibilityId("Graphics");

    public static By verticesLink = AppiumBy.accessibilityId("Vertices");

    public static By viewLink = AppiumBy.accessibilityId("Views");
    public static By alertViewsLink = AppiumBy.accessibilityId("Alert Views");

    public static  By textEntryLink = AppiumBy.iOSClassChain("**/XCUIElementTypeStaticText[`label == 'Text Entry'`]");

    public HomePage(AppiumDriver driver) {
        super(driver);
        this.driver = driver;
    }


    //type == 'smoething' AND value == 'something else'

    //type == 'smoething' AND value BEGINSWITH 'something else'
    //https://github.com/appium/appium-xcuitest-driver/blob/master/docs/ios-predicate.md

    public void navigateToAlertViewPage() {
        clickElement(alertViewsLink);
        clickElement(textEntryLink);
    }

    public void navigateToPreferencePage() {
//        waitForVisibilityOfElementLocated(preferenceLink);
        clickElement(preferenceLink);
    }
    public void navigateToGraphicsPage() {
        clickElement(graphicsLink);
    }

    public void navigateToViewsPage() {
        clickElement(viewLink);
    }

    public void navigateToVerticesPage() {
        scrollToElement("Vertices");
        clickElement(verticesLink);
    }
}
