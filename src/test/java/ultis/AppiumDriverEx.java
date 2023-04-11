package ultis;

import io.appium.java_client.MobileElement;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AppiumDriverEx {
    public static AppiumDriver<MobileElement> getAppiumDriver(){
        AppiumDriver<MobileElement> driver = null;
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("automationName", "uiautomator2");
        cap.setCapability("platformName", "android");
        cap.setCapability("uuid", "emulator-5554");
        cap.setCapability("appPackage", "com.tuhuynh.sdetproecommerce");
        cap.setCapability("appActivity", "host.exp.exponent.MainActivity");

        try {
            URL url = new URL("http://localhost:4723/wd/hub");
            driver = new AndroidDriver<>(url, cap);
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi ko thể tạo Appium session! Haizzz!!!!!!!!!");
        }

        return driver;
    }
}
