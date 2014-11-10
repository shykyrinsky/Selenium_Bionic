package selenium;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

/**
 * Created by Bionic on 11/10/14.
 */
public class WebDriverFactory {
    public static final String FIREFOX = "firefox";
    public static final String HTMLUNIT = "htmlunit";

    public static WebDriverWrapper initDriver (String driverName) {
        WebDriverWrapper driverWrapper = null;
        if (driverName.equalsIgnoreCase(FIREFOX)) {
            driverWrapper = new WebDriverWrapper(new FirefoxDriver());
        } else if (driverName.equalsIgnoreCase(HTMLUNIT)) {
            driverWrapper = new WebDriverWrapper(new HtmlUnitDriver());
        } else {
            Assert.fail("Invalid driver configuration");
        }

        driverWrapper.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driverWrapper.manage().window().maximize();
        driverWrapper.manage().deleteAllCookies();
        driverWrapper.manage().window().maximize();
        return driverWrapper;
    }
}
