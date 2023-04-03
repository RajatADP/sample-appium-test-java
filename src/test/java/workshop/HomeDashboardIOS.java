package workshop;

import com.aventstack.extentreports.Status;
import helpers.BaseTestV2;
import helpers.ExtentReport;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ViewsPage;

public class HomeDashboardIOS extends BaseTestV2 {


    @BeforeClass
    void setUp() {
        homePage = new HomePage(getDriver());
        viewsPage = new ViewsPage(getDriver());
    }

    @Test
    void verifyDashboardAndBottomNavigation() {
        homePage.navigateToAlertViewPage();
        ExtentReport.getTest().log(Status.INFO, "Preference Navigation successful");

    }
}
