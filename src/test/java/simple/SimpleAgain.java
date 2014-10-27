package simple;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Objects;

/**
 * Created by Bionic on 10/27/14.
 */
public class SimpleAgain {
    public static WebDriver driver;

    @DataProvider
    public Object[][] filters() {
        return new Object[][] {
          new Object[] {"http://www.ya.ru", "Поиск"},
          new Object[] {"http://www.google.com", "Google"},
          new Object[] {"http://www.mail.ru", "Почта"},
        };
    }

    @BeforeSuite
    public void initEnv() {
       driver = new HtmlUnitDriver();

    }

    @Test (dataProvider = "filters")
     public void test( String siteUrl, String text) {
        driver.get(siteUrl);
        Assert.assertTrue(driver.findElements(By.linkText(text)).size() > 0);

    }

    @AfterSuite
    public void quitDriver() {
        if (driver != null)
            driver.quit();
    }
}
