package pages;

import helpers.BaseTest;
import helpers.HelperMethods;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import org.testng.Assert;

public class HomePage {

    private HelperMethods helperMethods = new HelperMethods();

//    public static By appHomeTitle = MobileBy.xpath("//android.widget.TextView[contains(@text, 'example')]");

    public static  By preferenceLink = AppiumBy.accessibilityId("Preference");
    public static By graphicsLink = AppiumBy.accessibilityId("Graphics");

    public static By verticesLink = AppiumBy.accessibilityId("Vertices");

//    public void verifySomething() {
//        helperMethods.waitForVisibilityOfElementLocated(appHomeTitle);
//    }

    public void navigateToPreferencePage() {
//        helperMethods.waitForVisibilityOfElementLocated(preferenceLink);
        helperMethods.clickElement(preferenceLink);
    }
    public void navigateToGraphicsPage() {
//        helperMethods.waitForVisibilityOfElementLocated(preferenceLink);
        helperMethods.clickElement(graphicsLink);
    }

    public void navigateToVerticesPage() {
        helperMethods.scrollToElement("Vertices");
        helperMethods.clickElement(verticesLink);
    }
}
