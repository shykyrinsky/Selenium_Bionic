package pages;

import com.sun.org.apache.xpath.internal.XPath;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Illya on 03.11.2014.
 */
public class MainPage {

    private static String URL_MATCH = "http://hotline.ua";

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
