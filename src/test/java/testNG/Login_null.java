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
import ultis.Screenshot;

public class Login_null {
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
            st = connection.createStatement();
            String sql = "SELECT * FROM Login_null";
            resultSet = st.executeQuery(sql);

            //---------------App Mobile ------------

            //1. Lauch app
            AppiumDriver<MobileElement> driver = AppiumDriverEx.getAppiumDriver("emulator-5554");

            //2.Login
            MobileElement loginLabel = driver.findElement(MobileBy.AccessibilityId("userIcon"));
            loginLabel.click();

            MobileElement usernameEle = driver.findElement(MobileBy.AccessibilityId("emailInput"));
            MobileElement passwordEle = driver.findElement(MobileBy.AccessibilityId("passwordInput"));

            //2. Nhập username & password
            while (resultSet.next())
            {
                int tc_id = resultSet.getInt("id");
                String TCs_description = resultSet.getString("TCs_decripton");
                String email = resultSet.getString("username");
                String password = resultSet.getString("password");

                if (email != null) {
                    usernameEle.sendKeys(email);
                } else {
                    usernameEle.clear();
                }
                if (password != null) {
                    passwordEle.sendKeys(password);
                } else {
                    passwordEle.clear();
                }

                driver.findElement(MobileBy.AccessibilityId("loginBtn")).click();

                //So sánh kết quả
                try {
                    System.out.println(usernameEle.getText().equals("Enter Email") || passwordEle.getText().equals("Enter Password"));
                    MobileElement alertFailedEl1 = null;
                    if (!usernameEle.getText().equals("Enter Email") && !passwordEle.getText().equals("Enter Password")){
                        System.out.println("nếu có dữ liệu nhập vào mới run câu hàm if trong đây");
                        alertFailedEl1 = driver.findElement(MobileBy.xpath("//*[@text='Please provide correct credentials']"));
                    }
                    if (usernameEle.getText().equals("Enter Email") || passwordEle.getText().equals("Enter Password")) {
                        System.out.println("tc_id_" + tc_id + " | "+ TCs_description + " | FAILED");
                    } else if (alertFailedEl1.isDisplayed()){
                        System.out.println("tc_id_" + tc_id + " | "+ TCs_description + " | FAILED");
                    } else { // Nếu 2 đk trên sai thì vào đây
                        throw new Exception("Lỗi giả");
                    }

                    //0.1 Ghi FAILED vao cot actual_result
                    PreparedStatement statement = connection.prepareStatement("UPDATE Login_null SET actual_result = ?, tester = ?, datetime = ? WHERE id = ?");
                    // Thiết lập giá trị cho các tham số trong câu truy vấn
                    statement.setString(1, "FAILED"); // thiết lập giá trị cho tham số
                    statement.setString(2, "Đặng Lý");
                    statement.setString(3, dateCurrent());
                    statement.setInt(4, tc_id);
                    statement.executeUpdate();
                } catch (Exception e) {
                    //TODO: handle exception
//                    MobileElement toastMessage=null;
//                    if (!usernameEle.getText().isEmpty() & !passwordEle.getText().isEmpty()){
//                        toastMessage = driver.findElement(MobileBy.xpath("//*[@text='Please provide correct credentials']"));
//                    }
//                    toastMessage.isDisplayed();
                    System.out.println("tc_id_" + tc_id + " | "+ TCs_description + " | PASS");
//                    e.printStackTrace();
                    PreparedStatement statement = connection.prepareStatement("UPDATE Login_null SET actual_result = ?, tester = ?, datetime = ? WHERE id = ?");
                    // Thiết lập giá trị cho các tham số trong câu truy vấn
                    statement.setString(1, "PASS"); // thiết lập giá trị cho tham số
                    statement.setString(2, "Đặng Lý");
                    statement.setString(3, dateCurrent());
                    statement.setInt(4, tc_id);
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
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}

