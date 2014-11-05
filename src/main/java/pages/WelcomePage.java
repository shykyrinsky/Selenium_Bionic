package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Bionic on 11/3/14.
 */
public class WelcomePage {

    protected WebDriver driver;

    public WelcomePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isOnPage() {
        if (driver.getCurrentUrl().contains("final")) {
            return true;
        } else {
            return false;
        }
    }
}
