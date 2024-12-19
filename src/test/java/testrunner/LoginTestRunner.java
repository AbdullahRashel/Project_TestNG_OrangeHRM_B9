package testrunner;

import config.Setup;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.io.FileReader;
import java.io.IOException;

public class LoginTestRunner extends Setup {
    LoginPage loginPage;
    @Test(priority = 1, description="can not log in with wrong user name")
    public void doLoginWithWrongUser(){
        loginPage=new LoginPage(driver);
        loginPage.doLogin("Admim","admin123");
        String textActual= driver.findElement(By.className("oxd-alert-content-text")).getText();
        String textExpected="Invalid credentials";
        Assert.assertTrue(textActual.contains(textExpected));
    }
    @Test(priority = 2, description="can not log in with wrong password")
    public void doLoginWithWrongPass(){
        loginPage=new LoginPage(driver);
        loginPage.doLogin("Admin","wrongpass");
        String textActual= driver.findElement(By.className("oxd-alert-content-text")).getText();
        String textExpected="Invalid credentials";
        Assert.assertTrue(textActual.contains(textExpected));
    }
//    @Test(priority = 3,description="can log in with valid username and password")
//    public void doLoginWithValidCreds() throws ParseException, IOException {
//        loginPage = new LoginPage(driver);
//        String fileLocation="./src/test/resources/employees.json";
//        JSONParser parser=new JSONParser();
//        JSONArray empArray= (JSONArray) parser.parse(new FileReader(fileLocation));
//        JSONObject adminCredObj= (JSONObject) empArray.get(0);
//        if (System.getProperty("username") != null && System.getProperty("password") != null) {
//            loginPage.doLogin(System.getProperty("username"), System.getProperty("password"));
//        } else {
//            loginPage.doLogin(adminCredObj.get("username").toString(), adminCredObj.get("password").toString());
//        }
//        Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"));
//        String textActual = driver.findElement(By.className("oxd-topbar-header-breadcrumb-module")).getText();
//        String textExpected = "Dashboard";
//        Assert.assertEquals(textActual, textExpected);
//    }

    @Test(priority = 3, groups = "smoke", description="can log in with valid username and password")
    public void doLoginWithvalidCreds(){
        loginPage=new LoginPage(driver);
        loginPage.doLogin("Admin","admin123");
//        String adminUser=System.getProperty("username");
//        String adminPass=System.getProperty("password");
//        loginPage.doLogin(adminUser,adminPass);
        Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"));
        boolean isImageExists= driver.findElement(By.className("oxd-userdropdown-img")).isDisplayed();
        Assert.assertTrue(isImageExists);
    }
    @Test(priority = 4, description="log out Successfully")
    public void doLogout(){
        loginPage=new LoginPage(driver);
        loginPage.doLogout();
        //String loginHeaderTitleActual= driver.findElement(By.className("oxd-text oxd-text--h5 orangehrm-login-title")).getText();
        String loginHeaderTitleActual= driver.findElement(By.xpath("//h5[normalize-space()='Login']")).getText();
        String loginHeaderExpected="Login";
        Assert.assertEquals(loginHeaderTitleActual,loginHeaderExpected);
    }

}
