package functional;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import selenium.WebDriverFactory;
import selenium.WebDriverWrapper;
import utils.Log4Test;
import utils.PropertyLoader;
import java.io.IOException;
import java.lang.reflect.Method;



/**
 * Created by Bionic on 11/5/14.
 */
public class FunctionalTest {

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
    public void logMethodName(Method m) {
        Log4Test.info("-------" + m.getName() + "-------");
        driver.get(BASE_URL);
    }

    @AfterSuite (alwaysRun = true)
    public void tearDown () {
        try {
            Runtime.getRuntime().exec("taskkill /F /IM plugin-container.exe");  //cause Firefox crashes on .quit();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (driver != null) {
            Log4Test.info("Close browser");
            driver.quit();
        }
        Log4Test.info("**********End_TestSuit**********");
    }


}
