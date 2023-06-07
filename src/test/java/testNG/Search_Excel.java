package testNG;

import db.XLUtility;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ultis.AppiumDriverEx;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.util.List;

public class Search_Excel {

    private Object[][] data;
    private AppiumDriver<MobileElement> driver;

    @BeforeClass
    public void setUp() {

        driver = AppiumDriverEx.getAppiumDriver("emulator-5554");
    }
    @DataProvider(name = "SearchDataProvider")
    public Object[][] searchData() {
//        data = new Object[][]{
//                {"    ", "Nhập vào chuỗi = space", 0},
//                {" co ", "Trimspace đầu cuối", 1},
//                {"^&%*&^*", "Nhập toàn ký tự đặc biệt", 0},
//                {"Nếu chổ thiết dataprovider mà mình không chỉ rõ gì thêm thì nó sẽ hiểu là chạy tuần " +
//                        "tự từng dòng dữ liệu. Và trong TestNG nó cung cấp cho mình thêm một tham số là para" +
//                        "llel=true giúp chúng ta có thể chạy được các dòng dữ liệu song song với nhau.","Nhập chuỗi đặc biệt dài quá maxlength cho phép", 0},
//                {"abc","Nhập chuỗi sao cho không có dữ liệu trả về", 0},
//                {"123123", "Nhập chuỗi toàn số", 0},
//                {"clea123","Nhập chuỗi cả số và chữ", 0},
//                {"cod", "Tìm kiếm tương đối", 1},
//                {"Clean code", "Tìm kiếm tuyệt đối",1 },
//                {"CODe", "Tìm kiếm không phân biệt chữ hoa chữ thường", 1}};

        // ---Get data from excel---
        System.out.println("run vào @DataProvider");
        try {
//            String path = XLUtility.class.getClassLoader().getResource("Search.xlsx").getPath();
            String path = "D:\\KLTN\\kltn\\src\\test\\java\\db\\Search.xlsx";
            XLUtility xlutil = new XLUtility(path);

            int totalrows = xlutil.getRowCount("Sheet1");
            int totalcols = xlutil.getCellCount("Sheet1",1);
            data = new Object[totalrows][totalcols];

            System.out.println("số hàng: " + totalrows + "\nsố cột: " +  totalcols);

            for(int i=1;i<=totalrows;i++) //1
            {
                for(int j=0;j<totalcols;j++) //0
                {
                    data[i-1][j]=xlutil.getCellData("Sheet1", i, j);
                }

            }
            System.out.println("Dữ liệu hàng 2x2: " +data[2][2]);

            return data;
        } catch (IOException e) {
            throw new RuntimeException("Lỗi lấy file excel",e);
        }
    }


    @Test(dataProvider = "SearchDataProvider")
    public void searchTest(String keyword, String descriptionTestcase, String codeTCs) throws InterruptedException {
        System.out.println("Có chạy vào searchTest");
        MobileElement searchInputEle = driver.findElement(MobileBy.AccessibilityId("searchInput"));
        System.out.println("Có lấy được searchInput"+ searchInputEle.isDisplayed());
        searchInputEle.clear();
        searchInputEle.click();
        searchInputEle.sendKeys(keyword);
        Thread.sleep(2000);

        System.out.println("Running testcase " + descriptionTestcase);
        boolean isPassed = false;

        if (codeTCs.equals("0")) {
            MobileElement noResult = driver.findElement(MobileBy.AccessibilityId("noMatchedProductMsg"));
            System.out.println(" Kiểm tra xuất hiện noResult" + noResult.isDisplayed());
            isPassed = (noResult != null);
            System.out.println(isPassed);
            if (!isPassed) {
                Assert.fail("Không xuất hiện gì cả. Testcase failed");
            }
        } else if (codeTCs.equals("1")) {
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

