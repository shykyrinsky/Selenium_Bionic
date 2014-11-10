package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utils.Log4Test;

/**
 * Created by Bionic on 11/3/14.
 */
public class WelcomePage {

<<<<<<< Updated upstream
    protected WebDriver driver;

    public WelcomePage(WebDriver driver) {
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
=======

>>>>>>> Stashed changes
}
