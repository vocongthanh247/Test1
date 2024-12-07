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
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browserstack.user", "thanhvo_DtH7RP");
        caps.setCapability("browserstack.key", "AH576ScJ6B8qgD1DxLz6");
        caps.setCapability("device", "Samsung Galaxy S20");
        caps.setCapability("os_version", "10.0");
        caps.setCapability("project", "MyAppiumProject");
        caps.setCapability("build", "build-1");
        caps.setCapability("name", "Test name");

        URL browserstackUrl = new URL("https://hub-cloud.browserstack.com/wd/hub");
        WebDriver driver = new RemoteWebDriver(browserstackUrl, caps);
        driver.get("https://www.google.com");
        System.out.println(driver.getTitle());
        driver.quit();




//        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.vpa.daugia:id/btnGoTo\"]"));
    }

    @AfterMethod(alwaysRun=true)
    public void tearDown() throws Exception {
        driver.quit();
    }
}
