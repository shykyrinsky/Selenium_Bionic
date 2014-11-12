package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import selenium.WebDriverWrapper;
import utils.Log4Test;

/**
 * Created by Illya on 12.11.2014.
 */
public class RefrigiratorsPage {

    protected WebDriverWrapper driver;

    @FindBy(xpath = ".//*[@id='filters']//a[contains(text(), 'LG')]")
    private WebElement linkLG;

    public RefrigiratorsPage(WebDriverWrapper driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        Log4Test.info("Open RefrigiratorPage");
    }

    public RefrigiratorsPage filterLG() {
        linkLG.click();
        return this;
    }

}
