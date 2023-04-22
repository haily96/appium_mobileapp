package test;

import ultis.AppiumDriverEx;
import io.appium.java_client.*;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileBrowserType;
import io.appium.java_client.remote.MobilePlatform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.FluentWait;
import java.time.Duration;
import java.util.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ultis.Screenshot;
import ultis.SwipeUp;
import ultis.ScreenHandler;

import java.io.IOException;
import java.net.URL;
import java.net.MalformedURLException;
//import java.net.HttpURL;
import java.security.SecureRandom;
import java.util.List;
import java.util.concurrent.TimeUnit;

// NOTE: dependencies trong file pom.xml Java ver 7 nen su dung cac attribute cũ. Nếu sd java >= v8, chu ý các attribute được replace
public class EndToEndApp {
    public static void main(String[] args){
        testEmulator("emulator-5554");
//        testEmulator("R3CM804NEGE");
    }

    public static void testEmulator(String uuid){
        //Tạo 1 instance của class test1
        EndToEndApp automationInstance = new EndToEndApp();
        //0. Trước khi khởi tạo session => có appiumDriver để quản lý session
        AppiumDriver<MobileElement> driver = AppiumDriverEx.getAppiumDriver(uuid);

        //---1. LOGIN: Click on userIcon => vào ngay tab thông tin người dùng để login
        MobileElement userIconEle = driver.findElement(MobileBy.AccessibilityId("userIcon"));
        userIconEle.click();

        MobileElement emailInputEle = driver.findElement(MobileBy.AccessibilityId("emailInput"));
        MobileElement passwordInputEle = driver.findElement(MobileBy.AccessibilityId("passwordInput"));
        MobileElement loginBtnEle = driver.findElement(MobileBy.AccessibilityId("loginBtn"));
        emailInputEle.sendKeys("haily@gmail.com");
        passwordInputEle.sendKeys("12345678");
        // Sau khi click btn Login => chờ 10s rồi mới click sang Trang chủ
        automationInstance.doActionAndWait(() ->{
            loginBtnEle.click();
        }, 3000);
        driver.findElement(MobileBy.AccessibilityId("homeIcon")).click();

        //---2. Chon random 1 item từ list item
        List<MobileElement> productEle = driver.findElements(MobileBy.AccessibilityId("productItem"));
        while (productEle.size() == 0) {
            // Nếu danh sách trống, chờ 1 giây và tìm kiếm lại
            try {
                Thread.sleep(3000);
            } catch (Exception ignored){}
            productEle = driver.findElements(MobileBy.AccessibilityId("productItem"));
        }
        MobileElement randomProductionItem = productEle.get(new SecureRandom().nextInt(productEle.size())); // lấy item trong list theo index, trước đó lấy size- số lượng của list
        randomProductionItem.click();

        // Print ra hết toàn bộ item details
        // Chờ tới khi element xuất hiện mới thực hiện hành động
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AccessibilityId("addToCartBtn")));
        SwipeUp.swipeUp(driver);
        MobileElement productBrandEle = driver.findElement(MobileBy.AccessibilityId("productBrand"));
        MobileElement productNameEle = driver.findElement(MobileBy.AccessibilityId("productName"));
        MobileElement productAvailabilityEle = driver.findElement(MobileBy.AccessibilityId("productAvailability"));
        MobileElement productDescEle = driver.findElement(MobileBy.AccessibilityId("productDesc"));
        MobileElement productPriceEle = driver.findElement(MobileBy.AccessibilityId("productPrice"));
        MobileElement addToCartBtnEle = driver.findElement(MobileBy.AccessibilityId("addToCartBtn"));

        System.out.println("Brand: " + productBrandEle.getText());
        System.out.println("Name: " + productNameEle.getText());
        System.out.println("Availability: " + productAvailabilityEle.getText().split(":")[1].trim());
        System.out.println("Description: " + productDescEle.getText());
        System.out.println("Price: " + Double.parseDouble(productPriceEle.getText().replace("$", "").trim()));
        addToCartBtnEle.click();

        //---3.Go to cart & click đặt hàng
        driver.findElement(MobileBy.AccessibilityId("cartIcon")).click();
        driver.findElement(MobileBy.AccessibilityId("checkoutBtn")).click();

//        automationInstance.doActionAndWait(() ->{
//            driver.findElement(MobileBy.AccessibilityId("checkoutBtn")).click();
//        }, 3000);


        //Input thông tin
        MobileElement phoneNumEle = driver.findElement(MobileBy.AccessibilityId("phoneNum"));
        MobileElement shippingAdd1Ele = driver.findElement(MobileBy.AccessibilityId("shippingAdd1"));
        MobileElement shippingAdd2Ele = driver.findElement(MobileBy.AccessibilityId("shippingAdd2"));
        MobileElement shippingCityEle = driver.findElement(MobileBy.AccessibilityId("shippingCity"));
        phoneNumEle.sendKeys("0123456789");
        shippingAdd1Ele.sendKeys("Trau Quy");
        shippingAdd2Ele.sendKeys("Gia Lam");
        shippingCityEle.sendKeys("HN");

        ScreenHandler.swipeUp(driver);
        MobileElement shippingZIPCodeEle = driver.findElement(MobileBy.AccessibilityId("shippingZIPCode"));
        MobileElement selectCountryTriggerEle = driver.findElement(MobileBy.AccessibilityId("selectCountryTrigger"));
        shippingZIPCodeEle.sendKeys("9999");
        ScreenHandler.swipeUp(driver);
        selectCountryTriggerEle.click();

        // While: Tìm kiếm đến text="Viet Nam"
//        List<MobileElement> listCountries;
//        MobileElement countryNamEle = null;
//        String targetText = "Belarus";
//        boolean flag = false;
//        while (countryNamEle == null & !flag) {
//            listCountries = driver.findElements(By.xpath("\t\n" +
//                    "//android.widget.Spinner[@content-desc=\"selectCountryTrigger\"]"));
//            for (MobileElement element : listCountries) {
//                if (element.getText().equals(targetText)) {
//                    flag = true;
//                    countryNamEle = element;
//                    countryNamEle.click();
//                    break;
//                }
//            }
//            if (countryNamEle == null & !flag) {
//                SwipeUp80.swipeUp(driver);
//            }
//        }
        MobileElement countryNamEle =  driver.findElement(MobileBy.xpath("//*[@text=\"Angola\"]"));
        countryNamEle.click();
        MobileElement confirmShippingFormBtn = driver.findElement(MobileBy.AccessibilityId("confirmShippingFormBtn"));
        confirmShippingFormBtn.click();

        //---4. Chọn phương thức thanh toán Payment
        driver.findElement(MobileBy.AccessibilityId("Cash on Delivery")).click();
        driver.findElement(MobileBy.AccessibilityId("confirmBtn")).click();

        // Confirm hoàn tất đặt hàng
        MobileElement confirmShippingAdd1Ele = driver.findElement(MobileBy.AccessibilityId("confirmShippingAdd1"));
        MobileElement confirmShippingAdd2Ele = driver.findElement(MobileBy.AccessibilityId("confirmShippingAdd2"));
        MobileElement confirmCityEle = driver.findElement(MobileBy.AccessibilityId("confirmCity"));
        MobileElement confirmZIPCodeEle = driver.findElement(MobileBy.AccessibilityId("confirmZIPCode"));
        MobileElement confirmCountryEle = driver.findElement(MobileBy.AccessibilityId("confirmCountry"));
        MobileElement itemNameEle = driver.findElement(MobileBy.AccessibilityId("itemName"));
        MobileElement itemPriceEle = driver.findElement(MobileBy.AccessibilityId("itemPrice"));
        System.out.println("confirmShippingAdd1Ele: " + confirmShippingAdd1Ele.getText());
        System.out.println("confirmShippingAdd2Ele: " + confirmShippingAdd2Ele.getText());
        System.out.println("confirmCityEle: " + confirmCityEle.getText());
        System.out.println("confirmZIPCodeEle: " + confirmZIPCodeEle.getText());
        System.out.println("confirmCountryEle: " + confirmCountryEle.getText());
        System.out.println("itemNameEle: " + itemNameEle.getText());
        System.out.println("itemPriceEle: " + itemPriceEle.getText());

        ScreenHandler.swipeUp(driver);
        driver.findElement(MobileBy.AccessibilityId("placeOrderBtn")).click();


        //Điều chỉnh tốc độ sau mỗi bước. Phương thức này cho phép bạn tạm dừng thực thi mã trong một khoảng thời gian cụ thể (theo mili giây).
        try {
            Thread.sleep(5000);
        } catch (Exception ignored){}
//        driver.quit();
    }
    public void doActionAndWait(Runnable action, int waitTime){
        action.run();
        try {
            Thread.sleep(waitTime);
        } catch (Exception ignored){}
    }
}
