package functional;

<<<<<<< Updated upstream

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MainPage;


/**
 * Created by Illya on 06.11.2014.
 */
public class FindProductTest extends FunctionalTest {


    // Positive test that verifies @product is found in price lists
    @Test(dataProvider = "products", dataProviderClass = StaticDataProviders.class)
    public void IsSearchResultPresentTest(String product) {
        driver.get(BASE_URL);
        MainPage mainPage = new MainPage(driver);
        mainPage.initPage();
        mainPage.searchProduct(product);
        Assert.assertTrue(mainPage.verifySearchResultsPresent(),
                                    "Product " + product + " +  is NOT found"); //log4test.error() always run :(

    }

    // Negative test that verifies @product isNot found in price lists
    @Test(dataProvider = "products", dataProviderClass = StaticDataProviders.class)
    public void IsNotSearchResultPresentTest(String product) {
        driver.get(BASE_URL);
        MainPage mainPage = new MainPage(driver);
        mainPage.initPage();
        mainPage.searchProduct(product);
        Assert.assertFalse(mainPage.verifySearchResultsPresent(),
                                    "Product " + product + " IS found");  // log4test.error() always run :(
    }

}
=======
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import pages.MainPage;

import java.util.concurrent.TimeUnit;

public class FindProductTest {

    private static final String BASE_URL = "http://hotline.ua";
    private static final String SEARCH = "Iphone";
    protected WebDriver driver;


    @BeforeSuite
    public void setUP () {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Test
    public void findProduct() {
    driver.get(BASE_URL);
    MainPage mainPage = new MainPage(driver);
    mainPage.initPage();
    mainPage.findProduct(SEARCH);
    Assert.assertTrue(mainPage.textIsOnPage());
    }



    @AfterSuite
    public void tearDown () {
        driver.quit();
    }

}
>>>>>>> Stashed changes
