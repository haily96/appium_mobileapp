package test.apiLearning;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import ultis.AppiumDriverEx;

public class clickOnElement {
    public static void main(String[] args) {
        AppiumDriver<MobileElement> driver = AppiumDriverEx.getAppiumDriver("emulator-5554");
        //Login
        MobileElement loginELe = driver.findElement(MobileBy.AccessibilityId("userIcon"));
        loginELe.click();
    }
}
