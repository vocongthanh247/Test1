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
        // Khởi động Appium Server (nếu chưa được chạy)
        ProcessBuilder builder = new ProcessBuilder("appium");
        builder.inheritIO().start();

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

        // Đảm bảo emulator đã sẵn sàng trước khi thực hiện test
        Thread.sleep(30000);  // Đợi 30 giây (có thể điều chỉnh thêm nếu cần)

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
