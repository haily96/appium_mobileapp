package testNG;

import db.DBConnection;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.testng.annotations.Test;
import ultis.AppiumDriverEx;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SignUp {
    @Test
    public void TestSignUp() throws InterruptedException {
        Connection connection = null;
        Statement st = null;
        ResultSet resultSet = null;

        //Lay du lieu tu bang SignUp
        try {
            connection = DBConnection.getConnectionDB();
            st = connection.createStatement();
            String sql = "SELECT * FROM SignUp";
            resultSet = st.executeQuery(sql);

            //----------------App Mobile ------------------
            AppiumDriver<MobileElement> driver = AppiumDriverEx.getAppiumDriver("emulator-5554");
            MobileElement userIconELe = driver.findElement(MobileBy.AccessibilityId("userIcon"));
            userIconELe.click();

            MobileElement registerAccountEle = driver.findElement(MobileBy.AccessibilityId("registerAccountBtn"));
            registerAccountEle.click();


            MobileElement emailEle = driver.findElement(MobileBy.AccessibilityId("email"));
            MobileElement nameEle = driver.findElement(MobileBy.AccessibilityId("name"));;
            MobileElement phoneEle = driver.findElement(MobileBy.AccessibilityId("phone"));
            MobileElement passwordEle = driver.findElement(MobileBy.AccessibilityId("password"));

            //Nhap du lieu tu DB
            while (resultSet.next())
            {
                int tc_id = resultSet.getInt("id");
                String TCs_description = resultSet.getString("testcase");
                String email = resultSet.getString("email");
                String name = resultSet.getString("name");
                String phone_number = resultSet.getString("phone_number");
                String password = resultSet.getString("password");

                if (email != null) {
                    emailEle.sendKeys(email);
                } else {
                    emailEle.clear();
                }
                if (name != null) {
                    nameEle.sendKeys(name);
                } else {
                    nameEle.clear();
                }
                if (phone_number != null) {
                    phoneEle.sendKeys(phone_number);
                } else {
                    phoneEle.clear();
                }
                if (password != null) {
                    passwordEle.sendKeys(password);
                } else {
                    passwordEle.clear();
                }
                driver.findElement(MobileBy.AccessibilityId("registerBtn")).click();

                //So sanh kq
                try {

                    if (emailEle.getText().equals("Email") || nameEle.getText().equals("Name") || phoneEle.getText().equals("Phone Number") || passwordEle.getText().equals("Password")) {
                        System.out.println("tc_id_" + tc_id + " | "+ TCs_description + " | FAILED");
                    } else { // Nếu 2 đk trên sai thì vào đây
                        throw new Exception("Lỗi giả");
                    }

                    //0.1 Ghi FAILED vao cot actual_result
                    PreparedStatement statement = connection.prepareStatement("UPDATE SignUp SET actual_result = ?, tester = ?, datetime = ? WHERE id = ?");
                    // Thiết lập giá trị cho các tham số trong câu truy vấn
                    statement.setString(1, "FAILED"); // thiết lập giá trị cho tham số
                    statement.setString(2, "Đặng Lý");
                    statement.setString(3, dateCurrent());
                    statement.setInt(4, tc_id);
                    statement.executeUpdate();
                } catch (Exception e){
                    System.out.println("tc_id_" + tc_id + " | "+ TCs_description + " | PASS");
//                    e.printStackTrace();
                    PreparedStatement statement = connection.prepareStatement("UPDATE SignUP SET actual_result = ?, tester = ?, datetime = ? WHERE id = ?");
                    // Thiết lập giá trị cho các tham số trong câu truy vấn
                    statement.setString(1, "PASS"); // thiết lập giá trị cho tham số
                    statement.setString(2, "Đặng Lý");
                    statement.setString(3, dateCurrent());
                    statement.setInt(4, tc_id);
                    statement.executeUpdate();
                }

                // Wait 5s cho moi TCs sau
                Thread.sleep(4000);

            }

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            try {
                st.close();
                connection.close();
            } catch (Exception e){}
        }
    }
    public String dateCurrent(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date date = new Date();
        return dateFormat.format(date);
    }

}
