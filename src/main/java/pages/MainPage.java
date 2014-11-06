package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Illya on 03.11.2014.
 */
public class MainPage {

    private static final String URL_MATCH = "http://hotline.ua";

    protected WebDriver driver;

    @FindBy(className = "close")
    private WebElement popupClose;

    @FindBy(xpath = ".//div[contains(@class,'region-doubtfulness-popup')]/span[@class='close']")
    private WebElement regPopupClose;

    @FindBy(className = "reg")
    private WebElement registerLink;

    public MainPage(WebDriver driver) {
        if (!driver.getCurrentUrl().contains(URL_MATCH)) {
            throw new IllegalStateException(
                    "This is not the page you are expected"
            );
        }
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

        //close popups if they displayed
    public MainPage initPage() {
        if(popupClose.isDisplayed()) {
            popupClose.click();
        }
        if(regPopupClose.isDisplayed()) {
            regPopupClose.click();
        }
        return this;
    }

    //open RegisterPage
    public RegistrationPage openRegister() {
        registerLink.click();
        return new RegistrationPage(driver);
    }


}
