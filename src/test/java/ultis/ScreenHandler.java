package ultis;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;

import java.awt.*;
import java.time.Duration;

public class ScreenHandler {
    public static void swipeUp(AppiumDriver<MobileElement> driver){
        Dimension windowSize = driver.manage().window().getSize();
        int screenHeight = windowSize.getHeight();
        int screenWidth = windowSize.getWidth();

        // Tính điểm chạm touch: bắt đầu ở giữa màn hình rồi kéo màn hình cuộn xuống
        int xStartPoint = 50 * screenWidth / 100;
        int xEndPoint = xStartPoint;
        int yStartPoint = 30 * screenHeight / 100;
        int yEndPoint = 50 * screenHeight / 100;

        // Convert to Tọa độ
        PointOption startPoint = new PointOption().withCoordinates(xStartPoint, yStartPoint);
        PointOption endPoint = new PointOption().withCoordinates(xEndPoint, yEndPoint);

        //Perform Touch actions
        TouchAction touchAction = new TouchAction(driver);

        touchAction
                .press(endPoint)
                .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(1))) //cho phép cuộn trang ở mỗi vị trí trong khoảng thời gian 2 giây.
                .moveTo(startPoint)
                .release()
                .perform(); // ko có cái này thì ko có hành động nào được thực hiện
    }

}
