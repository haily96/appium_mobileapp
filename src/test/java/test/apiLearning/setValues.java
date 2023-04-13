package test.apiLearning;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import ultis.AppiumDriverEx;

import java.io.File;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class setValues {
    public static void main(String[] args) throws InterruptedException {
        //1. Lauch app
        AppiumDriver<MobileElement> driver = AppiumDriverEx.getAppiumDriver();
        //Login
        MobileElement loginLabel = driver.findElement(MobileBy.AccessibilityId("userIcon"));
        loginLabel.click();

        //2. Nhập username & password
        MobileElement usernameEle = driver.findElement(MobileBy.AccessibilityId("emailInput"));
        MobileElement passwordEle = driver.findElement(MobileBy.AccessibilityId("passwordInput"));
        usernameEle.sendKeys("iii@gmail.com");
        passwordEle.sendKeys("1111111111");

        //3. Click btn Login
        driver.findElement(MobileBy.AccessibilityId("loginBtn")).click();

        //4. Kiểm tra element toast message
        MobileElement alert_faild = driver.findElement(MobileBy.xpath("//*[@text='Please provide correct credentials']"));
        if (alert_faild.isDisplayed())
        {
            System.out.println("Lấy được toast message");
        }

        Thread.sleep(5000);
//        File screenFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//        //Set time thành tên file screenshot
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//        String destFile = dateFormat.format(new Date()) + ".png";
//        //Copy file sang folder
//        String picture = System.getProperty("user.dir" + "/screenshot/" + destFile);
//        try {
//            FileUtils.copyFile(screenFile, new File(picture));
//        } catch (Exception e){
//            e.printStackTrace();
//            System.out.println("Lỗi không sreenshot");
//        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String destFile = dateFormat.format(new Date()) + ".png";
        System.out.println(destFile);
        String destFile2 = destFile.replaceAll("[:/]", "_");
        System.out.println(destFile2);

        File formScreen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String formScreenFilePath = System.getProperty("user.dir") + "/screenshot/" + destFile2;
        System.out.println(formScreenFilePath);
        try {
            FileUtils.copyFile(formScreen, new File(formScreenFilePath));
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
