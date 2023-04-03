package helpers;

import com.aventstack.extentreports.Status;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.Properties;

public class HelperMethods {
    private AppiumDriver driver;
    public HelperMethods(AppiumDriver driver) {
        this.driver = driver;
    }

    //    public void waitForVisibilityOfElementLocated(By element) {
//        WebDriverWait wait = new WebDriverWait(driver, Constants.WAIT);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
//    }

    public void longClick(By element) {
        WebElement ele = driver.findElement(element);
        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) ele).getId(),
                "duration", 2000
        ));

        //ios
        /*((JavascriptExecutor)driver).executeScript("mobile: touchAndHold", ImmutableMap.of(
                "element", ((RemoteWebElement) ele).getId(),
                "duration", 2000
        ));*/

    }

    public void clickElement(By element) {
        //waitForVisibilityOfElementLocated(element);
        driver.findElement(element).click();
        ExtentReport.getTest().log(Status.INFO, element + " clicked successful");
    }

    public void enterValue(By element, String value) {
//        waitForVisibilityOfElementLocated(element);
        driver.findElement(element).sendKeys(value);
        ExtentReport.getTest().log(Status.INFO, value + " entered successful");
    }

    public WebElement getElement(By element) {
//        waitForVisibilityOfElementLocated(element);
        return driver.findElement(element);
    }

    public void scrollToElement(String textToScroll) {

        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"" + textToScroll + "\"))"));

        //ios
        /*WebElement ele = "somting";
        Map<String, Objects> map = new HashMap<>();
        map.put("element", ((RemoteWebElement) ele).getId());
        map.put("direction", "down");
        ((JavascriptExecutor)driver).executeScript("mobile: scroll", map
        ));*/

    }


    public JSONObject readJsonData(String path) {
        JSONObject jsonObject = null;
        try {
            String dataFile = path;
            String content = new String((Files.readAllBytes(Path.of(dataFile))));
            jsonObject = new JSONObject(content);

            if (jsonObject == null) {
                throw new NullPointerException("Cannot find resource file " + path);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static Properties readPropertiesFile(String fileName) throws IOException {
        FileInputStream fis = null;
        Properties prop = null;
        try {
            fis = new FileInputStream(fileName);
            prop = new Properties();
            prop.load(fis);
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            fis.close();
        }
        return prop;
    }


}
