package hp365;

import com.aventstack.extentreports.Status;
import helpers.BaseTest;
import helpers.ExtentReport;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;

public class HomeDashboard extends BaseTest {


    @BeforeClass
    void setUp() {
        homePage = new HomePage();
    }

    @Test
    void verifyDashboardAndBottomNavigation() {
        homePage.verifySomething();
        ExtentReport.getTest().log(Status.INFO, "Dashboard and Bottom Navigation verifications successful");
    }
}
