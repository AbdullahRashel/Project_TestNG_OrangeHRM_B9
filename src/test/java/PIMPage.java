import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PIMPage {
    //@FindBy(xpath = "//a[@class='oxd-main-menu-item active']")
    //WebElement pimMenu;
    @FindBy(className = "oxd-main-menu-item")
    List<WebElement> menuItems;
    @FindBy(className = "oxd-button")
    List<WebElement> button;
    @FindBy(name = "firstName")
    WebElement txtFirstName;
    @FindBy(name = "lastName")
    WebElement txtLastName;
    @FindBy(className = "oxd-switch-input")
    WebElement toggleButton;
    @FindBy(className = "oxd-input")
    //@FindBy (xpath = "//body/div[@id='app']/div[@class='oxd-layout orangehrm-upgrade-layout']/div[@class='oxd-layout-container']/div[@class='oxd-layout-context']/div[@class='orangehrm-background-container']/div[@class='orangehrm-card-container']/form[@class='oxd-form']/div[@class='orangehrm-employee-container']/div[@class='orangehrm-employee-form']/div[@class='oxd-form-row']/div[1]/div[1]/div[1]/div[2]/input[1]")
    List<WebElement> txtFields;

    public PIMPage(WebDriver driver){
        PageFactory.initElements(driver,this);

    }

    public void createUser(String firstName, String lastName, String username, String password) throws InterruptedException {
        menuItems.get(1).click(); //click PIM menu
        Thread.sleep(2000);
        button.get(2).click(); //click add button
        Thread.sleep(2000);
        txtFirstName.sendKeys(firstName);
        txtLastName.sendKeys(lastName);
        toggleButton.click();
        txtFields.get(5).sendKeys(username); //insert username
        txtFields.get(6).sendKeys(password); //insert password
        txtFields.get(7).sendKeys(password); //insert confirm password
        button.get(1).click(); //click submit button

    }

}
