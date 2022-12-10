package pages;

import helpers.BaseTest;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import org.testng.Assert;

public class HomePage extends BaseTest {

    public static By appHomeTitle = MobileBy.xpath("//android.widget.TextView[contains(@text, 'example')]");

    public void verifySomething() {
        helperMethods.waitForVisibilityOfElementLocated(appHomeTitle);
    }
}
