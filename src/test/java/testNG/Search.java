package testNG;

import db.DBConnection;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.*;
import ultis.AppiumDriverEx;

import java.io.File;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Search {

    private Object[][] data = null;
    private AppiumDriver<MobileElement> driver;
    Connection connection = null;
    Statement st = null;
    ResultSet resultSet = null;
    @BeforeClass
    public void setUp() {
        driver = AppiumDriverEx.getAppiumDriver("emulator-5554");
    }
    @DataProvider(name="SearchDataProvider")
    public Object [][] getData() throws SQLException {
        connection = DBConnection.getConnectionDB();
        st = connection.createStatement();
        String sql = "SELECT * FROM Search";
        resultSet = st.executeQuery(sql);

//        Object[][] searchData = new Object[rowCount][3];
        List<Object[]> searchDataList = new ArrayList<>();
        while (resultSet.next()) {
            Object[] rowData = new Object[4];
            rowData[0] = resultSet.getInt("id");
            rowData[1] = resultSet.getString("keyword");
            rowData[2] = resultSet.getString("expected_result");
            rowData[3] = resultSet.getString("expected_result_2");

            // Thêm 1 hàng vào List
            searchDataList.add(rowData);
            System.out.println("tc_id_" + rowData[0] + " | " + rowData[2]);

//            int tc_id = resultSet.getInt("id");
//            String keyword = resultSet.getString("keyword");
//            String expectedResult = resultSet.getString("expected_result");
//            String expectedResult2 = resultSet.getString("expected_result_2");

//           // Thêm 1 hàng vào List<> searchData
//            searchData.add(new Object[]{keyword, expectedResult});
//            System.out.println("tc_id_" + tc_id + " | " + expectedResult);
//        }
        }
            // Chuyển đổi danh sách thành mảng 2 chiều và trả về
        Object[][] searchData = new Object[searchDataList.size()][4];
        for (int i = 0; i < searchDataList.size(); i++) {
            searchData[i] = searchDataList.get(i);
        }

        data = searchData;
        return data;
    }

    @Test(dataProvider = "SearchDataProvider")
    public void searchTest (int tc_id, String keyword, String expectedResult, String expectedResult2) throws SQLException {

        MobileElement searchInputEle = driver.findElement(MobileBy.AccessibilityId("searchInput"));
        searchInputEle.clear();
        searchInputEle.click();
        searchInputEle.sendKeys(keyword);

//        boolean isPassed;
//        List<MobileElement> results = driver.findElements(MobileBy.xpath("(//android.view.ViewGroup[@content-desc=\"matchedItem\"])"));
//        MobileElement yesResult = null;
//        MobileElement noResult = null;
//
//        if (results.size() > 0) {
//            yesResult = results.get(0);
//            Assert.assertEquals(expectedResult2, "PASS");
//            isPassed = true;
//        } else {
//            noResult = driver.findElement(MobileBy.AccessibilityId("noMatchedProductMsg"));
//            Assert.assertEquals(expectedResult2, "FAILED");
//            isPassed = false;
//        }

        // So sánh kết quả
        boolean isPassed = false;
//        for (int i = 0; i < data.length; i++) {
//            if (i >= 0 && i < 6) {
//                MobileElement noResult = driver.findElement(MobileBy.AccessibilityId("noMatchedProductMsg"));
//                Assert.assertTrue(noResult != null);
//                System.out.println(expectedResult);
//            } else {
//                List<MobileElement> yesResult = driver.findElements(MobileBy.xpath("(//android.view.ViewGroup[@content-desc=\"matchedItem\"])"));
//                Assert.assertTrue(yesResult != null);
//                System.out.println(expectedResult);
//            }
//        }

        if(expectedResult2.equals("PASS")){
            MobileElement noResult = driver.findElement(MobileBy.AccessibilityId("noMatchedProductMsg"));
            System.out.println(tc_id + " Kiểm tra xuất hiện noResult" + noResult);
            isPassed = (noResult != null);
            System.out.println("isPassed: " + isPassed);
        } else if (expectedResult2.equals("FAILED")){
            List<MobileElement> yesResult = driver.findElements(MobileBy.xpath("(//android.view.ViewGroup[@content-desc=\"matchedItem\"])"));
            System.out.println("Kiểm tra xuất hiện noResult" + yesResult);
            isPassed = (yesResult != null);
            System.out.println(isPassed);
        }

        //0.1 Ghi FAILED vao cot actual_result
        PreparedStatement statement = connection.prepareStatement("UPDATE Search SET actual_result = ?, tester = ?, datetime = ? WHERE id = ?");
        // Thiết lập giá trị cho các tham số trong câu truy vấn
        statement.setString(1, isPassed ? "PASS" : "FAILED"); // thiết lập giá trị cho tham số
        statement.setString(2, "Đặng Lý");
        statement.setString(3, dateCurrent());
        statement.setInt(4, tc_id);
        statement.executeUpdate();
    }
    public String dateCurrent(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}

