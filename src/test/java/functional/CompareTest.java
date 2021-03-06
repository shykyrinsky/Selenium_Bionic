package functional;


import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ComparsionPage;
import pages.MainPage;

/**
 * Created by Illya on 07.11.2014.
 */
public class CompareTest extends FunctionalTest {


    @Test( dataProvider = "compareProdData", dataProviderClass = StaticDataProviders.class)
    public void comparePricesTest(String product) {
        MainPage mainPage = new MainPage(driver);
        mainPage.initPage();
        mainPage.searchProduct(product);
        Assert.assertTrue(mainPage.verifySearchResultsPresent(),
                                            "Product " + product + " +  is NOT found");
        ComparsionPage comparePage = mainPage.comparePrices();
        Assert.assertTrue(comparePage.verifyStoresPresent(),
                                      "There are NOT several offers for this product");
}
}
