package testGroups;

import org.testng.annotations.Test;

public class testGroupExample {

    @Test(groups = {"UI"})
    public void checkElementUI(){
        System.out.println(this.getClass().getSimpleName() + " | checkUI");
    }
    @Test(groups = {"UI"})
    public void checkZoomInUI(){
        System.out.println(this.getClass().getSimpleName() + " | checkUI");
    }
    @Test(groups = {"UI", "Function"})
    public void checkZoomOutUI(){
        System.out.println(this.getClass().getSimpleName() + " | checkUI + checkFunctionPassword");
    }
     @Test(groups = {"Function"})
     public void checkFunctionPassword(){
            System.out.println(this.getClass().getSimpleName() + " | checkFunctionPassword");
        }
}
