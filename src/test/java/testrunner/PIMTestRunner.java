package testrunner;

import com.github.javafaker.Faker;
import config.EmployeeModel;
import config.Setup;
import io.opentelemetry.api.internal.Utils;
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
    @BeforeTest
    public void doLogin(){
        LoginPage loginPage=new LoginPage(driver);
        loginPage.doLogin("Admin","admin123");
    }
    @Test(priority = 1)
    public void createUser() throws InterruptedException, IOException, ParseException {
        PIMPage pimPage=new PIMPage(driver);
        Faker faker=new Faker();
        String firstName=faker.name().firstName();
        String lastName=faker.name().lastName();
        String username=faker.name().username();
        String password="P@ssword123";
        pimPage.createUser(firstName,lastName,username,password);
        //pimPage.createUser("Jamal","Kudu","jamalkudu4","P@ssword123");
        WebElement headerElement=driver.findElement(By.xpath("//h6[normalize-space()='Personal Details']"));
//        String headerElementTitleActual= driver.findElement(By.xpath("//h6[normalize-space()='Personal Details']")).getText();
//        String loginHeaderExpected="Personal Details";
//        Assert.assertEquals(headerElementTitleActual,loginHeaderExpected);
//        String textActual= headerElement.get(0).getText();
//        String textExpected="Personal Details";
//        Thread.sleep(3000);
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.visibilityOf(headerElement));

        String textActual= headerElement.getText();
        String textExpected="Personal Details";
        Assert.assertEquals(textActual,textExpected);

        EmployeeModel employeeModel=new EmployeeModel();
        employeeModel.setFirstName(firstName);
        employeeModel.setLastName(lastName);
        employeeModel.setUsername(username);
        employeeModel.setPassword(password);
//        utils.Utils.saveUsers(firstName,lastName,username,password);
        utils.Utils.saveUsers(employeeModel);

    }
}
