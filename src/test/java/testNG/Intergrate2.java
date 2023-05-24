package testNG;

import db.DBConnection;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ultis.AppiumDriverEx;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Intergrate2 {
    Connection connection = null;
    Statement st = null;
    ResultSet resultSet = null;
    AppiumDriver<MobileElement> driver = AppiumDriverEx.getAppiumDriver("emulator-5554");
    @BeforeClass
    public void setUpBeforeTest() {
        System.out.println("-------1. Kiểm tra điều kiện trước và các biến dùng chung chạy ở đây");
        try {
            connection = DBConnection.getConnectionDB();
            st = connection.createStatement();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @BeforeMethod
    public void ClickUserIcon(){
        //---------------App Mobile ------------
        MobileElement userIconEl = driver.findElement(MobileBy.AccessibilityId("userIcon"));
        userIconEl.click();
    }
    @Test
    public void SignUp() throws InterruptedException {
        System.out.println("\n-------2. Đăng ký ở đây---------------");
        //Lay du lieu tu bang SignUp
        try {
            String sql = "SELECT * FROM SignUp";
            resultSet = st.executeQuery(sql);

            //----------------App Mobile ------------------
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
        }
    }

    //Có điều kiện để run trước, nếu ko có sẽ run theo bảng chữ cái abc
    @Test(dependsOnMethods = {"SignUp"})
    public void Login(){
        System.out.println("\n-------3. Đăng nhập ở đây------------");
        try {
            String sql = "SELECT * FROM Login_null";
            resultSet = st.executeQuery(sql);
            //---------------App Mobile ------------
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
        }
    }

    @AfterClass void cleanMemory(){
        System.out.println("\n-------4. Giải phóng bộ nhớ kết nối với DB ở đây--------------");
        try {
            st.close();
            connection.close();
        } catch (Exception e) {}
    }
    public String dateCurrent(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
