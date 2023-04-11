package test.apiLearning;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import ultis.AppiumDriverEx;

public class setValues {
    public static void main(String[] args) {
        //1. Lauch app
        AppiumDriver<MobileElement> driver = AppiumDriverEx.getAppiumDriver();
        //Login
        MobileElement loginLabel = driver.findElement(MobileBy.AccessibilityId("userIcon"));
        loginLabel.click();

        //2. Nhập username & password
        MobileElement usernameEle = driver.findElement(MobileBy.AccessibilityId("emailInput"));
        MobileElement passwordEle = driver.findElement(MobileBy.AccessibilityId("passwordInput"));
        usernameEle.sendKeys("iii@gmail.com");
        passwordEle.sendKeys("12345678");

        //3. Click btn Login
        driver.findElement(MobileBy.AccessibilityId("loginBtn")).click();
    }
}
