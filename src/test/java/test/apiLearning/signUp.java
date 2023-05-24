package test.apiLearning;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import ultis.AppiumDriverEx;
import ultis.Screenshot;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class signUp {
    public static void main(String[] args) throws InterruptedException {
        AppiumDriver<MobileElement> driver = AppiumDriverEx.getAppiumDriver("emulator-5554");
        MobileElement userIconELe = driver.findElement(MobileBy.AccessibilityId("userIcon"));
        userIconELe.click();
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
        passwordEle.sendKeys("123456");


        driver.findElement(MobileBy.AccessibilityId("registerBtn")).click();
//        MobileElement alertFailedEl1 = driver.findElement(MobileBy.xpath("//*[@text='Please fill in the form correctly']"));
//        if(alertFailedEl1.isDisplayed()){
//            System.out.println("Có lấy dược thông báo");
//        } else {
//            System.out.println("Ko lấy được thông báo lỗi");
//        }
        Screenshot.takeScreenshot();
        Thread.sleep(3000);

        //------ Screenshot tên file tự đặt
//        File formScreen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//        String formScreenFilePath = System.getProperty("user.dir") + "/screenshot/" + "screenshot06.png";
//        try {
//            FileUtils.copyFile(formScreen, new File(formScreenFilePath));
//        } catch (Exception e){
//            e.printStackTrace();
//        }

//        ------ Screenshot tên file theo real time
//        File screenFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//        //Set time thành tên file screenshot
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//        String destFile = "signUp"+dateFormat.format(new Date()) + ".png";
//        //Copy file sang folder
//        String picture = "D:\\kltn_11\\screenshot" + "/" + destFile;
//        try {
//            FileUtils.copyFile(screenFile, new File(picture));
//        } catch (Exception e){
//            e.printStackTrace();
//        }
    }
}
