package base;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.MutableCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class VPATestBase {
    protected ValidateHelpers validateHelpers;
    public AndroidDriver driver;

    @BeforeMethod(alwaysRun=true)
    public void setUp() throws Exception {
        MutableCapabilities capabilities = new UiAutomator2Options();
        capabilities.setCapability("browserstack.user", "thanhvo_DtH7RP");
        capabilities.setCapability("browserstack.key", "AH576ScJ6B8qgD1DxLz6");
        capabilities.setCapability("app", "https://github.com/vocongthanh247/Test1/raw/main/android/testng-examples/Vpa-v(0.4.21)-241107-product-release%20(1).apk");
        capabilities.setCapability("device", "Samsung Galaxy S22 Ultra");
        capabilities.setCapability("os_version", "12.0");
        capabilities.setCapability("project", "BrowserStack Samples");
        capabilities.setCapability("build", "browserstack build");
        capabilities.setCapability("name", "TestNG Appium Test");

        driver = new AndroidDriver(new URL("http://hub-cloud.browserstack.com/wd/hub"), capabilities);
        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.vpa.daugia:id/btnGoTo\"]"));
    }

    @AfterMethod(alwaysRun=true)
    public void tearDown() throws Exception {
        if (driver != null) {
            driver.quit();
        }
    }
}