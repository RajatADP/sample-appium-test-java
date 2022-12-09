package pages;

import helpers.BaseTest;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import org.testng.Assert;

public class HomePage extends BaseTest {

    public static By appHomeTitle = MobileBy.xpath("//android.widget.TextView[contains(@text, 'Hello')]");
    public static By myHealthPoints = MobileBy.xpath("//android.widget.TextView[contains(@text, 'My Healthpoints')]");
    public static By myRewards = MobileBy.xpath("//android.widget.TextView[contains(@text, 'My Rewards')]");
    public static By myRecommendations = MobileBy.xpath("//android.widget.TextView[contains(@text, 'RECOMMENDATION')]");
    public static By activitySummary = MobileBy.xpath("//android.widget.TextView[contains(@text, 'ACTIVITY SUMMARY')]");
    public static By bookedEvents = MobileBy.xpath("//android.widget.TextView[contains(@text, 'BOOKED EVENTS')]");
    public static By challengeProgress = MobileBy.xpath("//android.widget.TextView[contains(@text, 'CHALLENGE PROGRESS')]");
    public static By homeNavigation = MobileBy.xpath("//android.widget.TextView[contains(@text, 'Home')]");
    public static By exploreNavigation = MobileBy.xpath("//android.widget.TextView[contains(@text, 'Explore')]");
    public static By scanNavigation = MobileBy.xpath("//android.widget.TextView[contains(@text, 'Scan')]");
    public static By rewardsNavigation = MobileBy.xpath("//android.widget.TextView[contains(@text, 'Rewards')]");
    public static By profileNavigation = MobileBy.xpath("//android.widget.TextView[contains(@text, 'Profile')]");

    public void verifyDashboardSections() {
        helperMethods.waitForVisibilityOfElementLocated(appHomeTitle);
        Assert.assertEquals(helperMethods.getElement(myHealthPoints).isDisplayed(), true);
        Assert.assertEquals(helperMethods.getElement(myRewards).isDisplayed(), true);

        Assert.assertEquals(helperMethods.getElement(myRecommendations).isDisplayed(), true);
        helperMethods.scrollToElement("ACTIVITY SUMMARY");
        Assert.assertEquals(helperMethods.getElement(activitySummary).isDisplayed(), true);
        helperMethods.scrollToElement("CHALLENGE PROGRESS");
        Assert.assertEquals(helperMethods.getElement(challengeProgress).isDisplayed(), true);
        helperMethods.scrollToElement("BOOKED EVENTS");
        Assert.assertEquals(helperMethods.getElement(bookedEvents).isDisplayed(), true);
    }

    public void verifyBottomNavigationSections() {
        Assert.assertEquals(helperMethods.getElement(homeNavigation).isDisplayed(), true);
        Assert.assertEquals(helperMethods.getElement(exploreNavigation).isDisplayed(), true);
        Assert.assertEquals(helperMethods.getElement(rewardsNavigation).isDisplayed(), true);
        Assert.assertEquals(helperMethods.getElement(scanNavigation).isDisplayed(), true);
        Assert.assertEquals(helperMethods.getElement(profileNavigation).isDisplayed(), true);
    }
}
