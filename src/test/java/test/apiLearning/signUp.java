package test.apiLearning;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import ultis.AppiumDriverEx;

import java.io.File;

public class signUp {
    public static void main(String[] args) throws InterruptedException {
        AppiumDriver<MobileElement> driver = AppiumDriverEx.getAppiumDriver();
        MobileElement loginELe = driver.findElement(MobileBy.AccessibilityId("userIcon"));
        loginELe.click();
        //Sign up
        MobileElement registerAccountEle = driver.findElement(MobileBy.AccessibilityId("registerAccountBtn"));
        registerAccountEle.click();

        //Điền các thông tin đăng ký
        MobileElement emailEle = driver.findElement(MobileBy.AccessibilityId("email"));
        MobileElement nameEle = driver.findElement(MobileBy.AccessibilityId("name"));;
        MobileElement phoneEle = driver.findElement(MobileBy.AccessibilityId("phone"));
        MobileElement passwordEle = driver.findElement(MobileBy.AccessibilityId("password"));
        emailEle.sendKeys("ly@gmail.com");
        nameEle.sendKeys("Hai Ly");
        phoneEle.sendKeys("0123465789");
        Thread.sleep(3000);

        passwordEle.sendKeys("123456");

        driver.findElement(MobileBy.AccessibilityId("registerBtn")).click();

        Thread.sleep(5000);

        File formScreen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String formScreenFilePath = System.getProperty("user.dir") + "/screenshot/" + "screenshot01.png";
        try {
            FileUtils.copyFile(formScreen, new File(formScreenFilePath));
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
