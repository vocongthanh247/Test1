package base;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;


public class VPATestBase {
    protected ValidateHelpers validateHelpers;

    public AndroidDriver driver;

    @BeforeMethod(alwaysRun=true)
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserstack.user", "your_browserstack_username");
        capabilities.setCapability("browserstack.key", "your_browserstack_access_key");
        capabilities.setCapability("device", "Samsung Galaxy S20");
        capabilities.setCapability("os_version", "10.0");

        URL url = new URL("http://hub-cloud.browserstack.com/wd/hub");
        WebDriver driver = new RemoteWebDriver(url, capabilities);
        driver.get("https://www.google.com");
        System.out.println(driver.getTitle());
        driver.quit();



        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.vpa.daugia:id/btnGoTo\"]"));
    }

    @AfterMethod(alwaysRun=true)
    public void tearDown() throws Exception {
        driver.quit();
    }
}
