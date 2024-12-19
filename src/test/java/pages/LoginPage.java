package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LoginPage {
    @FindBy(name = "username")
    WebElement txtUsername;
    @FindBy(name = "password")
    WebElement txtPassword;
    @FindBy(className = "oxd-button")
    WebElement btnLogin;
    @FindBy(className = "oxd-userdropdown-name")
    WebElement lblUsername;
    @FindBy(xpath = "//a[normalize-space()='Logout']")
    WebElement cbUserMenu;
    @FindBy(className = "oxd-userdropdown-link")
    List<WebElement> dropDownBtn;
    //@FindBy(className = "oxd-userdropdown-link")
    //WebElement cbUserMenu;
    //@FindBy(css = "[role=\"menuitem\"]")
    //List<WebElement> cbUserMenu;

    public LoginPage(WebDriver driver){PageFactory.initElements(driver,this);
    }
    public void doLogin(String username, String password){
        txtUsername.sendKeys(username);
        txtPassword.sendKeys(password);
        btnLogin.click();

    }
    public void unableToLogOut(){
        lblUsername.click();
        dropDownBtn.get(2).click();
    }
    public void doLogout(){
        lblUsername.click();
//        cbUserMenu.click();
        dropDownBtn.get(3).click();
        //cbUserMenu.get(3).click();

    }
}
