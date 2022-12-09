package pages;

import helpers.BaseTest;
import helpers.HelperMethods;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPage extends BaseTest{

    public static By diveInBtn = MobileBy.xpath("//android.widget.TextView[contains(@text, 'dive in!')]");
    public static By NRIC = MobileBy.xpath("//android.widget.EditText[contains(@content-desc,'nricInput')]");
    public static By MOBILE = MobileBy.xpath("//android.widget.EditText[contains(@content-desc,'mobileNumberInput')]");
    public static By signInBtn = MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[3]");
    public static By otpField = MobileBy.xpath("//android.view.ViewGroup[contains(@content-desc,'OTPInputBox_')]");
    public static By otpVerificationTitle = MobileBy.xpath("//android.widget.TextView[contains(@text, 'OTP Verification')]");
    public static By deviceLocationOnlyThisTime = MobileBy.id("com.android.permissioncontroller:id/permission_allow_one_time_button");
    public void loginViaNRICAndMobile() {
        JSONObject jsonObject = helperMethods.readJsonData("src/main/java/data/example.json");

        helperMethods.clickElement(LoginPage.diveInBtn);
        helperMethods.enterValue(LoginPage.NRIC, jsonObject.getJSONObject("nricDetails").getString("username"));
        helperMethods.enterValue(LoginPage.MOBILE, jsonObject.getJSONObject("nricDetails").getString("mobile"));
        helperMethods.clickElement(LoginPage.signInBtn);
        enterOTP(getDriver());
    }

    public void enterOTP(AppiumDriver<WebElement> driver) {
        helperMethods.waitForVisibilityOfElementLocated(otpVerificationTitle);
        driver.getKeyboard().pressKey("1111");
    }

    public void acceptDeviceLocation() {
        helperMethods.clickElement(deviceLocationOnlyThisTime);
    }
}
