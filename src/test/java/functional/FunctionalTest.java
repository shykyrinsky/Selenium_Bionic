package functional;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import selenium.WebDriverFactory;
import selenium.WebDriverWrapper;
import utils.FunctionalTestRule;
import utils.Log4Test;
import utils.PropertyLoader;

/**
 * Created by Bionic on 11/5/14.
 */

@Listeners({FunctionalTestRule.class})
public abstract class FunctionalTest {

    public static WebDriverWrapper driver;

    public static final String BASE_URL = PropertyLoader.loadProperty("site.url");

    @BeforeSuite
    public void setUp() {
        String browser = PropertyLoader.loadProperty("browser.name");
        driver = WebDriverFactory.initDriver(browser);
        Log4Test.info("**********Starting_TestSuit**********");
        Log4Test.info("Start browser " + browser);
    }

    @BeforeMethod
    public void logMethodName() {
        driver.get(BASE_URL);
    }

    @AfterSuite (alwaysRun = true)
    public void tearDown () {
        if (driver != null) {
            Log4Test.info("Close browser");
            driver.quit();
        }
        Log4Test.info("**********End_TestSuit**********");
    }


}
