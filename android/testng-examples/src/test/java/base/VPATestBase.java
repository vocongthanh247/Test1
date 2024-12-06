package base;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VPATestBase {
    protected AndroidDriver driver;
    protected ValidateHelpers validateHelpers;

    // Constants
    protected static final String SCREENSHOT_PATH = "test-output/screenshots/VPA/";
    protected static final String APP_PACKAGE = "com.vpa.daugia";

    @BeforeMethod
    public void setUp() throws Exception {
        try {
            createScreenshotDirectory();
            initializeAppium();
            initializeHelpers();
            launchApp();
            validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.vpa.daugia:id/btnGoTo\"]"));
        } catch (Exception e) {
            System.err.println("Setup failed: " + e.getMessage());
            throw e;
        }
    }

    private void initializeAppium() throws Exception {
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();

            // Thay vì cấu hình device cụ thể, các thiết bị sẽ được chọn tự động từ file cấu hình.
            capabilities.setCapability("browserstack.user", "<BROWSERSTACK_USERNAME>");
            capabilities.setCapability("browserstack.key", "<BROWSERSTACK_ACCESS_KEY>");
            capabilities.setCapability("app", "bs://<app_id>");  // App của bạn trên BrowserStack
            capabilities.setCapability("automationName", "UiAutomator2");
            capabilities.setCapability("noReset", true);
            capabilities.setCapability("autoGrantPermissions", true);
            capabilities.setCapability("autoDismissAlerts", true);

            // URL kết nối đến BrowserStack hub
            URL url = new URL("https://<BROWSERSTACK_USERNAME>:<BROWSERSTACK_ACCESS_KEY>@hub-cloud.browserstack.com/wd/hub");
            driver = new AndroidDriver(url, capabilities);
        } catch (Exception e) {
            throw new Exception("Failed to initialize Appium: " + e.getMessage());
        }
    }

    private void initializeHelpers() {
        if (driver != null) {
            validateHelpers = new ValidateHelpers(driver);
        } else {
            throw new IllegalStateException("Driver is null, cannot initialize helpers");
        }
    }

    protected void launchApp() {
        if (driver != null) {
            driver.activateApp(APP_PACKAGE);
        } else {
            throw new IllegalStateException("Driver is null, cannot launch app");
        }
    }

    @AfterMethod
    public void tearDown() {
        try {
            if (driver != null) {
                String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String screenshotPath = captureScreenshot("teardown_" + timestamp);
                driver.quit();
                driver = null;
            }
        } catch (Exception e) {
            System.err.println("Error during cleanup: " + e.getMessage());
        }
    }

    protected String captureScreenshot(String name) {
        if (driver == null) {
            return null;
        }

        try {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String fileName = SCREENSHOT_PATH + name + "_" + timestamp + ".png";
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destFile = new File(fileName);
            screenshot.renameTo(destFile);
            return destFile.getAbsolutePath();
        } catch (Exception e) {
            return null;
        }
    }

    private void createScreenshotDirectory() {
        File directory = new File(SCREENSHOT_PATH);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }
}
