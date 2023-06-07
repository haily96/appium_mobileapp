package testNG;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ultis.AppiumDriverEx;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.util.List;

public class testcase_row {
//    public static void main(String[] args) throws InterruptedException {
//        AppiumDriver<MobileElement> driver = AppiumDriverEx.getAppiumDriver("emulator-5554");
//
//        MobileElement searchInputEle = driver.findElement(MobileBy.AccessibilityId("searchInput"));
//        searchInputEle.clear();
//        searchInputEle.click();
//        searchInputEle.sendKeys("     ");
//        // Kiểm tra kết quả tìm kiếm
//        MobileElement noResult = driver.findElement(MobileBy.AccessibilityId("noMatchedProductMsg"));
//        if (noResult != null) {
//            System.out.println(noResult.getText());
//        }
//        searchInputEle.clear();
//        searchInputEle.sendKeys("code");
//        Thread.sleep(2000);
//        MobileElement yesResult = driver.findElement(MobileBy.xpath("(//android.view.ViewGroup[@content-desc=\"matchedItem\"])"));
//        System.out.println(yesResult);
//        if (yesResult != null) {
//            System.out.println("Tìm kiếm có trả về kết quả");
//        } else {
//            System.out.println("Failed");
//        }
//    }

    private Object[][] data;
    private AppiumDriver<MobileElement> driver;

    @DataProvider(name = "SearchDataProvider")
    public Object[][] searchData() {
        data = new Object[][]{
                {"    ", "Nhập vào chuỗi = space", 0},
                {" co ", "Trimspace đầu cuối", 1},
                {"^&%*&^*", "Nhập toàn ký tự đặc biệt", 0},
                {"Nếu chổ thiết dataprovider mà mình không chỉ rõ gì thêm thì nó sẽ hiểu là chạy tuần " +
                        "tự từng dòng dữ liệu. Và trong TestNG nó cung cấp cho mình thêm một tham số là para" +
                        "llel=true giúp chúng ta có thể chạy được các dòng dữ liệu song song với nhau.","Nhập chuỗi đặc biệt dài quá maxlength cho phép", 0},
                {"abc","Nhập chuỗi sao cho không có dữ liệu trả về", 0},
                {"123123", "Nhập chuỗi toàn số", 0},
                {"clea123","Nhập chuỗi cả số và chữ", 0},
                {"cod", "Tìm kiếm tương đối", 1},
                {"Clean code", "Tìm kiếm tuyệt đối",1 },
                {"CODe", "Tìm kiếm không phân biệt chữ hoa chữ thường", 1}};
        return data;
    }

    @BeforeClass
    public void setUp() {

        driver = AppiumDriverEx.getAppiumDriver("emulator-5554");
    }

    @Test(dataProvider = "SearchDataProvider")
    public void searchTest(String keyword, String descriptionTestcase, int codeTCs) throws InterruptedException {
        MobileElement searchInputEle = driver.findElement(MobileBy.AccessibilityId("searchInput"));
        searchInputEle.clear();
        searchInputEle.click();
        searchInputEle.sendKeys(keyword);
        Thread.sleep(2000);
        // Kiểm tra kết quả tìm kiếm
        //TODO: Đang khai báo cả có và không có kết quả, khi tìm kiếm chỉ trả về 1 in 2 >> lỗi mất tg 15s chờ để tìm kiếm phần tử
//        for (int i =0 ; i<7; i++){
//            Object dataNoResult = data[i][0];
//            List<MobileElement> noResult = driver.findElements(MobileBy.AccessibilityId("noMatchedProductMsg"));
//            if (noResult != null){
//                System.out.println("Testcase " + i + ": "+ descriptionTestcase);
//            }
//        }
//        for (int i =7 ; i< data.length; i++){
//            Object dataYesResult = data[i][0];
//            List<MobileElement> yesResult = driver.findElements(MobileBy.xpath("(//android.view.ViewGroup[@content-desc=\"matchedItem\"])"));
//            if (yesResult != null){
//                System.out.println("Testcase " + i + ": "+ descriptionTestcase);
//            }
        //===============24 testcases --------------
//
//        List<MobileElement> noResult = driver.findElements(MobileBy.AccessibilityId("noMatchedProductMsg"));
//        List<MobileElement> yesResult = driver.findElements(MobileBy.xpath("(//android.view.ViewGroup[@content-desc=\"matchedItem\"])"));
//        if (!noResult.isEmpty()) {
//            // In kết quả cho các testcase không trả về kết quả
//            System.out.println("Testcase: " + descriptionTestcase + " - Không có kết quả");
//        } else if (!yesResult.isEmpty()) {
//            // In kết quả cho các testcase có kết quả
//            System.out.println("Testcase: " + descriptionTestcase + " - Có kết quả");
//        } else {
//            // In kết quả cho các trường hợp khác (có thể thêm xử lý theo yêu cầu của bạn)
//            System.out.println("Testcase: " + descriptionTestcase + " - Failed");
//        }

        //==========Vòng lặp for-------------
        System.out.println("Running testcase " + descriptionTestcase);
        boolean isPassed = false;

        if (codeTCs == 0) {
            MobileElement noResult = driver.findElement(MobileBy.AccessibilityId("noMatchedProductMsg"));
            System.out.println(" Kiểm tra xuất hiện noResult" + noResult.isDisplayed());
            isPassed = (noResult != null);
            System.out.println(isPassed);
            if (!isPassed) {
                Assert.fail("Không xuất hiện gì cả. Testcase failed");
            }
        } else if (codeTCs == 1) {
            List<MobileElement> yesResult = driver.findElements(MobileBy.xpath("(//android.view.ViewGroup[@content-desc=\"matchedItem\"])"));
            System.out.println("Kiểm tra xuất hiện yesResult" + yesResult.isEmpty());
            isPassed = (!yesResult.isEmpty());
            System.out.println(isPassed);
            if (!isPassed) {
                Assert.fail("Không trả về kết quả như mong đợi. Testcase failed");
            }

        }
    }
}

