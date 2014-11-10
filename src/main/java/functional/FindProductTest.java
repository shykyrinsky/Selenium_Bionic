package functional;


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