package selenium;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

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

        driverWrapper.manage().deleteAllCookies();
        driverWrapper.manage().window().maximize();
        java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF);
        java.util.logging.Logger.getLogger("org.apache.http").setLevel(Level.OFF);

        //System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");
        return driverWrapper;
    }
}
