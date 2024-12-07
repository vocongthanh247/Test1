package base;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.net.URL;

public class VPATestBase {
    protected ValidateHelpers validateHelpers;
    public AndroidDriver driver;  // Đảm bảo driver là một thuộc tính của lớp

    @BeforeMethod(alwaysRun=true)
    public void setUp() throws Exception {
        // Thiết lập các capabilities cho Android Emulator
        MutableCapabilities caps = new MutableCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("deviceName", "emulator-5554");  // Thiết bị giả lập
        caps.setCapability("platformVersion", "10.0");
        caps.setCapability("automationName", "UiAutomator2"); // Sử dụng UiAutomator2 cho Android

        // URL của Appium server (local trong trường hợp này)
        URL appiumServerUrl = new URL("http://localhost:4723/wd/hub");

        // Khởi tạo AndroidDriver với các capabilities đã thiết lập
        driver = new AndroidDriver(appiumServerUrl, caps);

        // Bạn có thể bắt đầu tương tác với ứng dụng của mình ở đây
        // driver.get("https://www.google.com"); // Nếu làm việc với web
        System.out.println("Successfully started the Android Emulator session.");
    }

    @AfterMethod(alwaysRun=true)
    public void tearDown() throws Exception {
        // Đảm bảo driver được tắt sau khi thực hiện xong
        if (driver != null) {
            driver.quit();
            System.out.println("Driver session ended.");
        }
    }
}
