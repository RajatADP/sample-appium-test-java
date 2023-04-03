package workshop;

import com.aventstack.extentreports.Status;
import helpers.BaseTestV2;
import helpers.ExtentReport;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.DeviceRotation;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ViewsPage;

public class HomeDashboard extends BaseTestV2 {


    @BeforeClass
    void setUp() {
        homePage = new HomePage(getDriver());
        viewsPage = new ViewsPage(getDriver());
    }

    @Test
    void scrollNavigation() {
        homePage.navigateToGraphicsPage();
        homePage.navigateToVerticesPage();
        ExtentReport.getTest().log(Status.INFO, "Graphics Navigation successful");
    }

    /*@Test
    void longPress() throws InterruptedException {
        homePage.navigateToViewsPage();
        viewsPage.navigateToExpandableLists();
        Thread.sleep(2000);

        ExtentReport.getTest().log(Status.INFO, "Long Press successful");
    }

    @Test
    void deviceRotate() throws InterruptedException {
        homePage.navigateToGraphicsPage();
        DeviceRotation rotation = new DeviceRotation(0, 0, 90);
        ((AndroidDriver) getDriver()).rotate(rotation);
        Thread.sleep(2000);

        ExtentReport.getTest().log(Status.INFO, "Long Press successful");
    }

    //activity and package
    @Test
    void activityStart() {
        Activity activity = new Activity("io.appium.android.apis", "io.appium.android.apis.preference.PreferenceDependencies");
        ((AndroidDriver) getDriver()).startActivity(activity);
    }*/

}
