package pages;

import actors.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Log4Test;


/**
 * Created by Illya on 03.11.2014.
 */
public class RegistrationPage {

  protected WebDriver driver;

    @FindBy(name = "email")
    private WebElement emailField;

    @FindBy(name = "nick")
    private WebElement nickField;

    @FindBy(name = "password")
    private WebElement passwField;

    @FindBy(name = "password2")
    private WebElement passwRepField;

    @FindBy(css = "input.blue-button")
    private WebElement submitBtn;

    @FindBy(xpath = ".//div[contains(@class,'region-doubtfulness-popup')]/span[@class='close']")
    private WebElement regPopupClose;

    @FindBy(xpath = ".//form[@id='reg-form']//span[@class='errors']")
    private WebElement emailError;

    public RegistrationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        if(regPopupClose.isDisplayed()) {        //close popup "City"
            regPopupClose.click();
        }
        Log4Test.info("Open Registration form");
    }

    // fill all fields with user's data
    public RegistrationPage setFieldsWithUserData(User user) {
        Log4Test.info("Registering user with " + user.email);
        emailField.sendKeys(user.email);
        nickField.clear();
        nickField.sendKeys(user.nick);
        passwField.sendKeys(user.passw);
        passwRepField.sendKeys(user.passw);
        return this;
    }

    //click "Register" Button for Success registration
    public WelcomePage submitSuccess() {
        submitBtn.click();
        return new WelcomePage(driver);
    }

    //click "Register" Button for Failed registration
    public RegistrationPage submitFailed() {
        submitBtn.click();
        return this;
    }

    //return True if any field is highlighted in red colour
    public boolean isError() {
        if (emailError.isDisplayed()) {
            Log4Test.info("Registration failed");
            return true;
        } else {
            return false;
        }
    }

}
