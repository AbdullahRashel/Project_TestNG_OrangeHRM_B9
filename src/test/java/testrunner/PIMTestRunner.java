package testrunner;

import com.github.javafaker.Faker;
import config.Setup;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.PIMPage;

public class PIMTestRunner extends Setup {
    @BeforeTest
    public void doLogin(){
        LoginPage loginPage=new LoginPage(driver);
        loginPage.doLogin("Admin","admin123");
    }
    @Test(priority = 1)
    public void createUser() throws InterruptedException {
        PIMPage pimPage=new PIMPage(driver);
        Faker faker=new Faker();
        String firstName=faker.name().firstName();
        String lastName=faker.name().lastName();
        String username=faker.name().username();
        String password="P@ssword123";
        pimPage.createUser(firstName,lastName,username,password);
        //pimPage.createUser("Jamal","Kudu","jamalkudu4","P@ssword123");
        //List<WebElement> headerElement=driver.findElements(By.className("orangehrm-main-title"));
        String headerElementTitleActual= driver.findElement(By.xpath("//h6[normalize-space()='Personal Details']")).getText();
        String loginHeaderExpected="Personal Details";
        Assert.assertEquals(headerElementTitleActual,loginHeaderExpected);
//        String textActual= headerElement.get(0).getText();
//        String textExpected="Personal Details";
//        Thread.sleep(3000);
//        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(30));
//        wait.until(ExpectedConditions.visibilityOf((WebElement) headerElement));
//        Assert.assertEquals(textActual,textExpected);

    }
}
