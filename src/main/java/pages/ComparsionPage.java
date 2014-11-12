package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import selenium.WebDriverWrapper;
import utils.Log4Test;

import java.util.List;

/**
 * Created by Illya on 07.11.2014.
 */
public class ComparsionPage {

    protected WebDriverWrapper driver;

    @FindBy(xpath = "//div[@id='tab_2']//div[contains(@class, 'tx-price-line')]")
    private List<WebElement> listStores;

    public ComparsionPage(WebDriverWrapper driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
        //return true if there more than one store in storeList
    public boolean verifyStoresPresent() {
        if (listStores.size() > 1) {
            Log4Test.info("There are more than 1 offer for this product");
            return true;
        } else {
            return false;
        }
    }
}
