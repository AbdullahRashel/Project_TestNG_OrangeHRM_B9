import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class PIMTestRunner extends Setup {
    @BeforeTest
    public void doLogin(){
        LoginPage loginPage=new LoginPage(driver);
        loginPage.doLogin("Admin","admin123");
    }
    @Test(priority = 1)
    public void createUser() throws InterruptedException {
        PIMPage pimPage=new PIMPage(driver);
        pimPage.createUser("Jamal","Kudu","jamalkudu4","P@ssword123");
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
