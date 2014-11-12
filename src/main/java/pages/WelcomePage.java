package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import selenium.WebDriverWrapper;
import utils.Log4Test;

/**
 * Created by Bionic on 11/3/14.
 */
public class WelcomePage {


    protected WebDriverWrapper driver;

    public WelcomePage(WebDriverWrapper driver) {
        this.driver = driver;
    }

    public boolean isOnPage() {
        if (driver.getCurrentUrl().contains("final")) {
            Log4Test.info("Registration complete successfull");
            return true;
        } else {
            return false;
        }
    }

}
