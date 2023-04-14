package ultis;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Screenshot {
    public static String takeScreenshot (){
        AppiumDriver<MobileElement> driver = AppiumDriverEx.getAppiumDriver();
        String picture = "";
        //Lấy datetime để làm tên file ảnh
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String destFile = dateFormat.format(new Date()) + ".png";
        String destFile2 = destFile.replaceAll("[:/]", "_");

        //Capture Screenshot. Đối tượng screenFile lưu ảnh chụp màn hình
        File formScreen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        picture = System.getProperty("user.dir") + "/screenshot/" + destFile2;
        try {
            FileUtils.copyFile(formScreen, new File(picture));
        } catch (Exception e){
            e.printStackTrace();
        }
        return picture;
    }
}
