package test.apiLearning;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import ultis.AppiumDriverEx;

import java.io.File;

public class Screenshot {
    public static void main(String[] args) throws InterruptedException {
        AppiumDriver<MobileElement> driver = AppiumDriverEx.getAppiumDriver();
        Thread.sleep(1000);
        File formScreen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String formScreenFilePath = System.getProperty("user.dir" + "/screenshot/" + "screenshot01.png");
        try {
            FileUtils.copyFile(formScreen, new File(formScreenFilePath));
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
