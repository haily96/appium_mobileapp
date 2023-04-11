package testHooks;
import org.testng.annotations.*;
public class Login_01 {
    @BeforeSuite
    public void loginBeforeSuite() {
        System.out.println("Login 01 BeforeSuite");
    }
    @BeforeTest
    public void loginBeforeTest() {
        System.out.println("-- Login 01 BeforeTest");
    }
    @BeforeClass
    public void loginBeforeClass() {
        System.out.println("---- Login 01 BeforeClass");
    }
    @AfterSuite
    public void loginAfterSuit(){
        System.out.println("Login 01 AfterSuite");
    }
    @AfterTest
    public void loginAfterTest(){
        System.out.println("--Login 01 AfterSuite");
    }
    @AfterClass
    public void loginAfterClass(){
        System.out.println("----Login 01 AfterSuite");
    }
    @BeforeMethod
    public void loginBeforeMethod(){
        System.out.println("--------Login 01 BeforeMethod");
    }
    @AfterMethod
    public void loginAfterMethod(){
        System.out.println("--------Login 01 AfterMethod");
    }
    @Test
    public void loginSucess(){
        System.out.println("-----------------SUCCESSS - Đây là login Test 01");
    }
    @Test
    public void loginFailed(){
        System.out.println("-----------------FAILED - Đây là login Test 01");
    }
}
