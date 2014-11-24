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
    public void isSearchResultPresentTest(String product) {
        MainPage mainPage = new MainPage(driver);
        mainPage.initPage();
        mainPage.searchProduct(product);
        Assert.assertTrue(mainPage.verifySearchResultsPresent(),
                                    "Product " + product + " +  is NOT found");

    }

    // Negative test that verifies @product isNot found in price lists
    @Test(dataProvider = "products", dataProviderClass = StaticDataProviders.class, priority = 1)
    public void isNotSearchResultPresentTest(String product) {
        MainPage mainPage = new MainPage(driver);
        mainPage.initPage();
        mainPage.searchProduct(product);
        Assert.assertTrue(mainPage.verifyNoSearchResults(),
                                    "Product " + product + " IS found");
    }

}
