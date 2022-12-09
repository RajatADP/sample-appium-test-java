package hp365;

import com.aventstack.extentreports.Status;
import helpers.BaseTest;
import helpers.ExtentReport;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class HomeDashboard extends BaseTest {


    @BeforeClass
    void setUp() {
        loginPage = new LoginPage();
        homePage = new HomePage();
    }

    @Test(priority = 0)
    void loginViaNRICAndMobile() {
        loginPage.loginViaNRICAndMobile();
    }

    @Test
    void verifyDashboardAndBottomNavigation() {
        homePage.verifyDashboardSections();
        homePage.verifyBottomNavigationSections();
        ExtentReport.getTest().log(Status.INFO, "Dashboard and Bottom Navigation verifications successful");
    }
}
