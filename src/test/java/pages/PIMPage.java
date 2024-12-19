package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PIMPage {
    //@FindBy(xpath = "//a[@class='oxd-main-menu-item active']")
    //WebElement pimMenu;
    @FindBy(className = "oxd-main-menu-item")
    List<WebElement> menuItem;
    @FindBy(className = "oxd-button")
    List<WebElement> addBtn;
    @FindBy(name = "firstName")
    WebElement txtFirstName;
    @FindBy(name = "lastName")
    WebElement txtLastName;
    @FindBy(className = "oxd-switch-input")
    WebElement toggleButton;
//    @FindBy(className = "oxd-input")
//    //@FindBy (xpath = "//body/div[@id='app']/div[@class='oxd-layout orangehrm-upgrade-layout']/div[@class='oxd-layout-container']/div[@class='oxd-layout-context']/div[@class='orangehrm-background-container']/div[@class='orangehrm-card-container']/form[@class='oxd-form']/div[@class='orangehrm-employee-container']/div[@class='orangehrm-employee-form']/div[@class='oxd-form-row']/div[1]/div[1]/div[1]/div[2]/input[1]")
//    List<WebElement> txtFields;
    @FindBy(className = "oxd-input")
    List<WebElement> toggleEnableAndUserPassField;
    @FindBy(className = "orangehrm-left-space")
    WebElement submitBtn;
    @FindBy(className = "oxd-input")
    List<WebElement> employeeIDText;
    @FindBy(xpath = "//input[@placeholder=\"Type for hints...\"]")
    WebElement searchBoxWithHint;
    @FindBy(xpath = "//button[@type=\"submit\"]")
    WebElement searchButton;

    public PIMPage(WebDriver driver){PageFactory.initElements(driver,this);}

    public void notCreatingUser() throws InterruptedException {
        menuItem.get(1).click();
        addBtn.get(2).click();
        Thread.sleep(1000);
        submitBtn.click();
    }

    public void createUser(String firstName, String lastName, String username, String password) throws InterruptedException {
        menuItem.get(1).click(); //click PIM menu
//        Thread.sleep(2000);
        addBtn.get(2).click(); //click add button
//        Thread.sleep(2000);
        txtFirstName.sendKeys(firstName);
        txtLastName.sendKeys(lastName);
        toggleButton.click();
        toggleEnableAndUserPassField.get(5).sendKeys(username);
        toggleEnableAndUserPassField.get(6).sendKeys(password);
        toggleEnableAndUserPassField.get(7).sendKeys(password);
        submitBtn.click();
//        txtFields.get(5).sendKeys(username); //insert username
//        txtFields.get(6).sendKeys(password); //insert password
//        txtFields.get(7).sendKeys(password); //insert confirm password
//        button.get(1).click(); //click submit button
    }
    public String getEmployeeID() {
        return employeeIDText.get(4).getAttribute("value");
    }
    public void searchEmployeeById(String employeeIdJson) throws InterruptedException {
        menuItem.get(1).click();
        employeeIDText.get(1).sendKeys(employeeIdJson);
        Thread.sleep(1000);
        employeeIDText.get(1).sendKeys(Keys.ENTER);
    }
    public void searchEmployeeByName(String firstName) throws InterruptedException {
        menuItem.get(8).click();
        searchBoxWithHint.click();
        searchBoxWithHint.sendKeys(firstName);
        Thread.sleep(5000);
        searchBoxWithHint.sendKeys(Keys.ARROW_DOWN);
        searchBoxWithHint.sendKeys(Keys.ENTER);
        searchButton.click();
        Thread.sleep(3000);
    }
}
