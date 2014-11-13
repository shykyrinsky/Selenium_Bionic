package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import selenium.WebDriverWrapper;
import utils.Log4Test;

import java.util.List;

/**
 * Created by Illya on 12.11.2014.
 */
public class RefrigiratorsPage {

    protected WebDriverWrapper driver;

    @FindBy(xpath = "//*[@id='filters']//a[contains(text(), 'LG')]")
    private WebElement filterLG;

    @FindBy(xpath = "//*[@id='catalogue']//span[@class = 'ddopener']")
    private List<WebElement> sortList;

    @FindBy(xpath = "//*[@id='catalogue']//a[contains(@href, 'sort=0')]")
    private WebElement sortByPriceLink;

    @FindBy(xpath = "//*[@id='catalogue']/ul[contains(@class, 'catalog')]/li")
    private List<WebElement> refsList;

    private static final By priceRef = By.className("orng");


    public RefrigiratorsPage(WebDriverWrapper driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        Log4Test.info("Open RefrigiratorPage");
    }

    public RefrigiratorsPage filterLGclick() {
        filterLG.click();
        Log4Test.info("Filter Ref-s with LG");
        return this;
    }

    public RefrigiratorsPage sortByPrice() {
        sortList.get(0).click();
        sortByPriceLink.click();
        Log4Test.info("Sorting Ref-s by price");
        return this;
    }

    public float getPriceOfRef(int i) {
        String allPrice = refsList.get(i).findElement(priceRef).getText();
        allPrice = allPrice.replaceAll(" ", "");
        return Float.valueOf(allPrice.substring(0,allPrice.indexOf('г')));
    }

    public boolean areREFsSortedByPrice() {
        if (this.getPriceOfRef(0) < this.getPriceOfRef(1)) {
            Log4Test.info("Ref-s are SORTED by price desc (" + getPriceOfRef(0) +" < "+
                                                             + getPriceOfRef(1) + ")");
            return true;
        } else {
            return false;
        }
    }
}
