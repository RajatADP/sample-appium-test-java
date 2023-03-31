package workshop;

import com.aventstack.extentreports.Status;
import helpers.BaseTest;
import helpers.ExtentReport;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;

public class HomeDashboard2 extends BaseTest {


    @BeforeClass
    void setUp() {
        homePage = new HomePage();
    }

    @Test
    void verifyDashboardAndBottomNavigation() {
        homePage.navigateToGraphicsPage();
        homePage.navigateToVerticesPage();
        ExtentReport.getTest().log(Status.INFO, "Graphics Navigation successful");

    }
}