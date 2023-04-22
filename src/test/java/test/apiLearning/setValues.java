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
        AppiumDriver<MobileElement> driver = AppiumDriverEx.getAppiumDriver("emulator-5554");
        //Login
        MobileElement loginLabel = driver.findElement(MobileBy.AccessibilityId("userIcon"));
        loginLabel.click();

        //2. Nhập username & password
        MobileElement usernameEle = driver.findElement(MobileBy.AccessibilityId("emailInput"));
        MobileElement passwordEle = driver.findElement(MobileBy.AccessibilityId("passwordInput"));
        usernameEle.sendKeys("iii@gmail.com");
//        passwordEle.sendKeys("1111111111");

        //3. Click btn Login
        driver.findElement(MobileBy.AccessibilityId("loginBtn")).click();
        Thread.sleep(3000);

        //Kiểm tra lấy được element thông báo fill: PASS
//        MobileElement alert_faild_2 = driver.findElement(MobileBy.xpath("//*[@text='Please fill in your credentials']"));
//        if(alert_faild_2.isDisplayed())
//            System.out.println("có lấy được element thông báo fill");

        //4. Kiểm tra element toast message
        //5. Kiểm tra errorMessage khi 1 trường null
        MobileElement alert_faild_1 = null;
//        MobileElement alert_faild_2 = null;
        System.out.println(passwordEle.getText());

        String usernameValue = usernameEle.getAttribute("text");
        String passwordValue = passwordEle.getAttribute("text");
        System.out.println(passwordValue.equals(""));
        System.out.println(usernameValue.isEmpty());
        System.out.println(usernameValue.equals("Enter Email") || passwordValue.equals("Enter Password"));


        if (usernameValue.equals("Enter Email") || passwordValue.equals("Enter Password")){
            MobileElement alert_faild_2 = driver.findElement(MobileBy.xpath("//*[@text='Please fill in your credentials']"));
            System.out.println("có láy được element");
            if (alert_faild_2.isDisplayed())
                System.out.println("Lấy được errorMessage");
        }
        else {
            alert_faild_1 = driver.findElement(MobileBy.xpath("//android.widget.TextView[@text='Please provide correct credentials']"));
            if (alert_faild_1.isDisplayed())
                System.out.println("Lấy được toast message");

        }
//        Thread.sleep(5000);

        //------------------ chụp màn hình done
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//        String destFile = dateFormat.format(new Date()) + ".png";
//        System.out.println(destFile);
//        String destFile2 = destFile.replaceAll("[:/]", "_");
//        System.out.println(destFile2);
//
//        File formScreen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//        String formScreenFilePath = System.getProperty("user.dir") + "/screenshot/" + destFile2;
//        System.out.println(formScreenFilePath);
//        try {
//            FileUtils.copyFile(formScreen, new File(formScreenFilePath));
//        } catch (Exception e){
//            e.printStackTrace();
//        }
    }
}
