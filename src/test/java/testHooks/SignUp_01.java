package testHooks;
import org.testng.annotations.*;
public class SignUp_01 {
    @BeforeSuite
    public void signupBeforeSuite() {
        System.out.println("signup 01 BeforeSuite");
    }
    @BeforeTest
    public void signupBeforeTest() {
        System.out.println("-- signup 01 BeforeTest");
    }
    @BeforeClass
    public void signupBeforeClass() {
        System.out.println("---- signup 01 BeforeClass");
    }
    @AfterSuite
    public void signupAfterSuit(){
        System.out.println("signup 01 AfterSuite");
    }
    @AfterTest
    public void signupAfterTest(){
        System.out.println("--signup 01 AfterSuite");
    }
    @AfterClass
    public void signupAfterClass(){
        System.out.println("----signup 01 AfterSuite");
    }
    @Test
    public void signupSucess(){
        System.out.println("-----------------Đây là signup Test 01");
    }
}
