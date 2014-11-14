package functional;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.RefrigiratorsPage;

import java.util.concurrent.TimeUnit;

/**
 * Created by Illya on 12.11.2014.
 */
public class RefrigeratorTest extends FunctionalTest {


    @Test
    public void AreRefrigiratorsLGSortTest() {
        driver.get(BASE_URL);
        MainPage mainPage = new MainPage(driver);
        mainPage.initPage();
        RefrigiratorsPage refsPage = mainPage.selectSubMenuREFs();
        refsPage.filterLGclick();
        refsPage.sortByPrice();
        Assert.assertTrue(refsPage.areREFsSortedByPrice(),
                                     "Ref-s are NOT SORTED by price desc!");
    }


}
