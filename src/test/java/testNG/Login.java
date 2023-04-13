package testNG;

import db.DBConnection;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ultis.AppiumDriverEx;

import java.io.File;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import db.DBConnection;

public class Login {
//    @BeforeMethod
//    public void signUpSuccessful(){
//        AppiumDriver<MobileElement> driver = AppiumDriverEx.getAppiumDriver();
//        MobileElement loginELe = driver.findElement(MobileBy.AccessibilityId("userIcon"));
//        loginELe.click();
//        //Sign up
//        MobileElement registerAccountEle = driver.findElement(MobileBy.AccessibilityId("registerAccountBtn"));
//        registerAccountEle.click();
//
//        //Điền các thông tin đăng ký
//        MobileElement emailEle = driver.findElement(MobileBy.AccessibilityId("email"));
//        MobileElement nameEle = driver.findElement(MobileBy.AccessibilityId("name"));;
//        MobileElement phoneEle = driver.findElement(MobileBy.AccessibilityId("phone"));
//        MobileElement passwordEle = driver.findElement(MobileBy.AccessibilityId("password"));
//        emailEle.sendKeys("ly@gmail.com");
//        nameEle.sendKeys("Hai Ly");
//        phoneEle.sendKeys("0123465789");
//        passwordEle.sendKeys("123456");
//
//        driver.findElement(MobileBy.AccessibilityId("registerBtn")).click();
//
//
//        File formScreen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//        String formScreenFilePath = System.getProperty("user.dir") + "/screenshot/" + "screenshot01.png";
//        try {
//            FileUtils.copyFile(formScreen, new File(formScreenFilePath));
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//        System.out.println("Sẽ đăng ký tài khoản trước khi đăng nhập ở bước này");
//
//    }
    @Test
    public void loginSuccessful() {

        Connection connection = null;
        Statement st = null;
        ResultSet resultSet = null;

        //Lấy dữ liệu từ bảng Login
        try {
            //1. Tạo kết nối DB
            connection = DBConnection.getConnectionDB();
            //2. Tạo đối tượng statement, resultSet
            String sql = "SELECT * FROM Login";
            st = connection.createStatement();
            resultSet = st.executeQuery(sql);

            //---------------App Mobile ------------

            //1. Lauch app
            AppiumDriver<MobileElement> driver = AppiumDriverEx.getAppiumDriver();

            //2.Login
            MobileElement loginLabel = driver.findElement(MobileBy.AccessibilityId("userIcon"));
            loginLabel.click();

            MobileElement usernameEle = driver.findElement(MobileBy.AccessibilityId("emailInput"));
            MobileElement passwordEle = driver.findElement(MobileBy.AccessibilityId("passwordInput"));

            //2. Nhập username & password
            while (resultSet.next())
            {
                int tc_id = resultSet.getInt("id");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
//                String expected_result = resultSet.getString("expected result");
                usernameEle.sendKeys(email);
                passwordEle.sendKeys(password);
                driver.findElement(MobileBy.AccessibilityId("loginBtn")).click();



                //So sánh kết quả
//                System.out.println("tc_id: " + tc_id);
                try {
                    MobileElement alert_faild = driver.findElement(MobileBy.xpath("//*[@text='Please provide correct credentials']"));
                    Assert.assertEquals(alert_faild.getText(), "Please provide correct credentials");
                    System.out.println("tc_id: " + tc_id + " FAILED");
                    //0.1 Ghi FAILED vao cot actual_result
//                    resultSet.updateString("actual_result", "FAILED");
//                    resultSet.updateString("tester", "Hai Ly");
//                    resultSet.updateRow(); // luu nhung thay đổi trên vào csdl

                    PreparedStatement statement = connection.prepareStatement("UPDATE Login SET actual_result = ?, tester = ? WHERE id = ?");
                    // Thiết lập giá trị cho các tham số trong câu truy vấn
                    statement.setString(1, "FAILED"); // thiết lập giá trị cho tham số
                    statement.setString(2, "Hải Lý");
                    statement.setString(3, dateCurrent());
                    statement.setInt(4, tc_id);
                    statement.executeUpdate();
                } catch (Exception e) {
                    //TODO: handle exception
//                    resultSet.updateString("actual_result", "PASS");
//                    resultSet.updateString("tester", "Hai Ly");
//                    resultSet.updateRow(); // luu nhung thay đổi trên vào csdl
                    System.out.println("tc_id: " + tc_id + " PASS");
//                    e.printStackTrace();
                    PreparedStatement statement = connection.prepareStatement("UPDATE Login SET actual_result = ?, tester = ?, datetime = ?, file_log = ? WHERE id = ?");
                    // Thiết lập giá trị cho các tham số trong câu truy vấn
                    statement.setString(1, "PASS"); // thiết lập giá trị cho tham số
                    statement.setString(2, "Hải Lý");
                    statement.setString(3, dateCurrent());
                    statement.setString(4, takeScreenshot());
                    statement.setInt(5, tc_id);
                    statement.executeUpdate();
                }
                // Đợi 5s cho mỗi lần nhập username-password
                try {
                    Thread.sleep(5000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                st.close();
                connection.close();
            } catch (Exception e){}
        }
    }

    //Update thời gian kiểm thử và lưu hình ảnh lỗi vào db
    public String dateCurrent(){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
    public String takeScreenshot(){
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
