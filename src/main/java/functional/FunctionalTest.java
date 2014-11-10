package functional;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import selenium.WebDriverFactory;
import selenium.WebDriverWrapper;
import utils.PropertyLoader;


import java.io.IOException;
import java.util.concurrent.TimeUnit;



/**
 * Created by Bionic on 11/5/14.
>>>>>>> Stashed changes
 */
public class FunctionalTest {

    public static WebDriverWrapper driver;

    public static final String BASE_URL = "http://hotline.ua";

    @BeforeSuite
    public void setUp() {
        driver = WebDriverFactory.initDriver(PropertyLoader.loadProperty("browser.name"));

    }

    @AfterSuite
    public void tearDown () {
        try {
            Runtime.getRuntime().exec("taskkill /F /IM plugin-container.exe");  //cause Firefox crashes on .quit();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (driver != null) {
            driver.quit();
        }
    }


}
