package functional;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.RefrigiratorsPage;


/**
 * Created by Illya on 12.11.2014.
 */
public class RefrigeratorTest extends FunctionalTest {

    //Test verifies that link "Sort by Price" sorts refrigerators by price desc
    @Test(dataProvider = "REFsManufactors", dataProviderClass = StaticDataProviders.class)
    public void areRefrigiratorsSortTest(String manufactor) {
        MainPage mainPage = new MainPage(driver);
        mainPage.initPage();
        RefrigiratorsPage refsPage = mainPage.selectSubMenuREFs();
        refsPage.filterRefClick(manufactor);
        refsPage.sortByPrice();
        Assert.assertTrue(refsPage.areREFsSortedByPrice(),
                                     "Ref-s are NOT SORTED by price desc!");
    }


}
