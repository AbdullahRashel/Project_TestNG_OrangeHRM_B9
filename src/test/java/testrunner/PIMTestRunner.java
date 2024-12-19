package testrunner;

import com.github.javafaker.Faker;
import config.EmployeeModel;
import config.Setup;
import io.opentelemetry.api.internal.Utils;
import org.apache.commons.lang3.RandomStringUtils;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.PIMPage;

import java.io.IOException;
import java.time.Duration;

import static io.opentelemetry.api.internal.Utils.*;

public class PIMTestRunner extends Setup {
    PIMPage pimModule;
    LoginPage loginPage;
    @BeforeTest (groups = "smoke",description = "log in as admin")
    public void doLoginWithValidCreds(){
        LoginPage loginPage=new LoginPage(driver);
        loginPage.doLogin("Admin","admin123");
    }
    public String generateRandomPassword() {
        String upperCaseChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseChars = "abcdefghijklmnopqrstuvwxyz";
        String numericChars = "0123456789";
        String specialChars = "!@#$%^&*()-_=+[]{}|;:'\",.<>/?";
        String allChars = upperCaseChars + lowerCaseChars + numericChars + specialChars + numericChars;
        return RandomStringUtils.random(12, allChars);
    }
    @Test(priority = 1,description="Can not create User without firstname and lastname")
    public void canNotCreateUser() throws InterruptedException {
        pimModule = new PIMPage(driver);
        pimModule.notCreatingUser();
        String actualRequiredField = "Required";
        String expectedRequiredField = driver.findElement(By.xpath("//div[@class='oxd-input-group']//div[1]//span[1]")).getText();
        Assert.assertEquals(actualRequiredField,expectedRequiredField);
    }
    @Test(priority = 2, description = "Admin creating User successfully")
    public void createUser() throws InterruptedException, IOException, ParseException {
        PIMPage pimPage = new PIMPage(driver);
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String username = faker.name().username();
//        String password="P@ssword123";
        String password = generateRandomPassword();
        pimPage.createUser(firstName, lastName, username, password);
        String employeeId = pimModule.getEmployeeID();
        String titleTextActual = driver.findElement(By.xpath("//h6[text()=\"Personal Details\"]")).getText();
        String titleTextExpected = "Personal Details";
        Assert.assertEquals(titleTextActual, titleTextExpected);
        Thread.sleep(3000);

        EmployeeModel empModel = new EmployeeModel();
        empModel.setFirstName(firstName);
        empModel.setLastName(lastName);
        empModel.setUsername(username);
        empModel.setPassword(password);
        empModel.setEmployeeId(employeeId);
        utils.Utils.saveUsers(empModel);
    }
    @Test(priority = 3, groups = "smoke", description="After creating user, admin searching a user by employee ID")
    public void searchEmployeeById() throws IOException, ParseException, InterruptedException {
        pimModule = new PIMPage(driver);
        String employeeIdJson = utils.Utils.getUserFromJsonArray().get("employeeId").toString();
        pimModule.searchEmployeeById(employeeIdJson);
        utils.Utils.scroll(driver,0,80);
        Thread.sleep(1000);
        String titleTextExpected=driver.findElements(By.className("oxd-padding-cell")).get(10).getText();
        Assert.assertEquals(employeeIdJson,titleTextExpected);
        Thread.sleep(2000);
    }
    @Test(priority = 4,description="Searching a user by Wrong employee ID")
    public void searchEmployeeByWrongId() throws IOException, ParseException, InterruptedException {
        pimModule = new PIMPage(driver);
        String employeeIdJson = "16615";
        pimModule.searchEmployeeById(employeeIdJson);
        utils.Utils.scroll(driver, 0, 80);
        Thread.sleep(1000);
        String actualTittle = "No Records Found";
        String titleTextExpected = driver.findElements(By.className("oxd-text")).get(14).getText();
        Assert.assertEquals(actualTittle, titleTextExpected);
        Thread.sleep(2000);
    }
    @Test(priority = 5, description="After creating user, admin searching a user by username")
    public void searchEmployeeByName() throws IOException, ParseException, InterruptedException {
        pimModule = new PIMPage(driver);
        String firstNameActualUser=utils.Utils.getUserFromJsonArray().get("firstName").toString();
        pimModule.searchEmployeeByName(firstNameActualUser);
        Thread.sleep(2000);
        String nameTitleExpected=driver.findElement(By.className("orangehrm-directory-card-header")).getText();
        Assert.assertTrue(nameTitleExpected.startsWith(firstNameActualUser));
    }
    @Test(priority = 6,description="Admin searching a user by Wrong username")
    public void searchEmployeeByWrongName() throws IOException, ParseException, InterruptedException {
        pimModule = new PIMPage(driver);
        String firstWrongName="aedwfrsaewfrs";
        pimModule.searchEmployeeByName(firstWrongName);
        String actualTitle="Invalid";
        Thread.sleep(2000);
        String nameTitleExpected=driver.findElements(By.className("oxd-text")).get(14).getText();
        Assert.assertTrue(nameTitleExpected.startsWith(actualTitle));
    }
    @Test (priority = 7, description = "Admin successfully log out")
    public void logOut(){
        loginPage = new LoginPage(driver);
        loginPage.doLogout();
        String textActual = driver.findElement(By.className("orangehrm-login-title")).getText();
        String textExpected = "Login";
        Assert.assertEquals(textActual, textExpected);
    }

        //pimPage.createUser("Jamal","Kudu","jamalkudu4","P@ssword123");
//        WebElement headerElement=driver.findElement(By.xpath("//h6[normalize-space()='Personal Details']"));

//String headerElementTitleActual= driver.findElement(By.xpath("//h6[normalize-space()='Personal Details']")).getText();
//        String loginHeaderExpected="Personal Details";
//        Assert.assertEquals(headerElementTitleActual,loginHeaderExpected);
//        String textActual= headerElement.get(0).getText();
//        String textExpected="Personal Details";
//        Thread.sleep(3000);

//        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(50));
//        wait.until(ExpectedConditions.visibilityOf(headerElement));
//
//        String textActual= headerElement.getText();
//        String textExpected="Personal Details";
//        Assert.assertEquals(textActual,textExpected);
//
//        EmployeeModel employeeModel=new EmployeeModel();
//        employeeModel.setFirstName(firstName);
//        employeeModel.setLastName(lastName);
//        employeeModel.setUsername(username);
//        employeeModel.setPassword(password);
////        utils.Utils.saveUsers(firstName,lastName,username,password);
//        utils.Utils.saveUsers(employeeModel);

}
