package pages;

import helpers.HelperMethods;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class ViewsPage extends HelperMethods{

    private AppiumDriver driver;

    public ViewsPage(AppiumDriver driver) {
        super(driver);
        this.driver = driver;
    }


    public static By expandableListsLink = AppiumBy.accessibilityId("Expandable Lists");
    public static By customAdapterLink = AppiumBy.accessibilityId("1. Custom Adapter");
    public static By peopleNames = AppiumBy.xpath("//android.widget.TextView[@text='People Names']");


    public void navigateToExpandableLists() {
        clickElement(expandableListsLink);
        clickElement(customAdapterLink);
        longClick(peopleNames);
    }

}
