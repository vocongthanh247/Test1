package base;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.DesiredCapabilities;
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
        caps.setCapability("platformName", "Android");
        caps.setCapability("deviceName", "Samsung Galaxy S20");
        caps.setCapability("browserName", "Chrome");
        caps.setCapability("browserstack.user", "thanhvo_DtH7RP");
        caps.setCapability("browserstack.key", "AH576ScJ6B8qgD1DxLz6");

        URL url = new URL("https://hub-cloud.browserstack.com/wd/hub");
        AndroidDriver driver = new AndroidDriver(url, caps);

        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.vpa.daugia:id/btnGoTo\"]"));
    }

    @AfterMethod(alwaysRun=true)
    public void tearDown() throws Exception {
        driver.quit();
    }
}
