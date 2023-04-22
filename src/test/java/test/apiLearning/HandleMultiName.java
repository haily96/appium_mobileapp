package test.apiLearning;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import ultis.AppiumDriverEx;

import java.util.List;

public class HandleMultiName {
    public static void main(String[] args) {
        AppiumDriver<MobileElement> driver = AppiumDriverEx.getAppiumDriver("emulator-5554");

        //1. Click login screen
        MobileElement loginLabel = driver.findElement(MobileBy.AccessibilityId("userIcon"));
        loginLabel.click();
        //2. Find element text="Login"
        List<MobileElement> loginTexts = driver.findElements(MobileBy.xpath("//*[@text='Login']"));
        System.out.println("Có " + loginTexts.size()+ " elements matched");

        final int login_text = 0;
        final int login_button = 1;
        System.out.println(loginTexts.get(login_text).getText());;
        loginTexts.get(login_button).click();
    }
}
