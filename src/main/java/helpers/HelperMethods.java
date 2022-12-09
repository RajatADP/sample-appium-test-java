package helpers;

import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class HelperMethods extends BaseTest{

    public void waitForVisibilityOfElementLocated(By element) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Constants.WAIT);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public void clickElement(By element) {
        waitForVisibilityOfElementLocated(element);
        getDriver().findElement(element).click();
        ExtentReport.getTest().log(Status.INFO,element + " clicked successful");
    }

    public void enterValue(By element, String value) {
        waitForVisibilityOfElementLocated(element);
        getDriver().findElement(element).sendKeys(value);
        ExtentReport.getTest().log(Status.INFO,value + " entered successful");
    }

    public WebElement getElement(By element) {
        waitForVisibilityOfElementLocated(element);
        return getDriver().findElement(element);
    }

    public void scrollToElement(String textToScroll) {

        getDriver().findElement(MobileBy
                .AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"" + textToScroll + "\"))"));

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

    public Properties readPropertiesFile(String fileName) throws IOException {
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
