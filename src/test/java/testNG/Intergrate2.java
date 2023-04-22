package testNG;

import db.DBConnection;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ultis.AppiumDriverEx;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Intergrate2 {
    @BeforeClass
    public void setUpBeforeTest() {
        System.out.println("-------1. Kiểm tra điều kiện trước và các biến dùng chung chạy ở đây");
        Connection connection = null;
        Statement st = null;
        ResultSet resultSet = null;
        try {
            //1. Tạo kết nối DB
            connection = DBConnection.getConnectionDB();
            //2. Tạo đối tượng statement, resultSet
            String sql = "SELECT * FROM Login_null";
            st = connection.createStatement();
            resultSet = st.executeQuery(sql);

            //---------------App Mobile ------------

            //1. Lauch app
            AppiumDriver<MobileElement> driver = AppiumDriverEx.getAppiumDriver("emulator-5554");
        } catch (Exception e){

        }
    }

    @Test
    public void SignUp(){
        System.out.println("-------2. Đăng ký ở đây");
    }
    @Test
    public void Login(){
        System.out.println("-------3. Đăng nhập ở đây");
    }

    @AfterClass void cleanMemory(){
        System.out.println("-------4. Giải phóng bộ nhớ kết nối với DB ở đây");

    }
}
