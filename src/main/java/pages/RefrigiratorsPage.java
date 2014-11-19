package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import selenium.WebDriverWrapper;
import utils.Log4Test;

import javax.swing.*;
import java.util.List;

/**
 * Created by Illya on 12.11.2014.
 */
public class RefrigiratorsPage {

    protected WebDriverWrapper driver;

    private final static String URL_MATCH = "holodilniki";

    @FindBy(xpath = "//*[@id='filters']//div[@class='jspPane']/p/a")
    private List<WebElement> listAllManufactors;

    @FindBy(xpath = ".//*[@id='filters']//div[@class='jspTrack']")
    private WebElement verticalBar;

    @FindBy(xpath = ".//*[@id='filters']//*[contains(@class, 'jspDrag')]")
    private WebElement verticalBarSlider;

    @FindBy(xpath = "//*[@id='filters']//a[contains(@class,'op_all')]")
    private WebElement expandAllManLink;

    @FindBy(xpath = "//*[@id='filters']//a[contains(@class,'cl_all')]")
    private WebElement closeAllManLink;

    @FindBy(xpath = "//span[@class='title-span']/a[@class='hide']")
    private WebElement hideToolbarLink;

    @FindBy(xpath = "//*[@id='filters']/div[6]/div/div")
    private WebElement scrollArea;

    @FindBy(xpath = "//*[@id='catalogue']//span[@class = 'ddopener']")
    private List<WebElement> sortList;

    //@CacheLookup
    @FindBy(xpath = "//*[@id='catalogue']//a[contains(@href, 'sort=0')]")
    private WebElement sortByPriceLink;

    @FindBy(xpath = "//*[@id='catalogue']/ul[contains(@class, 'catalog')]/li")
    private List<WebElement> refsList;

    private static final By priceRef = By.className("orng");


    public RefrigiratorsPage(WebDriverWrapper driver) {
        if (!driver.getCurrentUrl().contains(URL_MATCH)) {
            Assert.fail("This is not the page you are expected");
        }
        PageFactory.initElements(driver, this);
        this.driver = driver;
        Log4Test.info("Open RefrigiratorPage");
    }

    //click on '@manufactor' in list of manufactors
    public RefrigiratorsPage filterRefClick(String manufacter) {
        hideToolbarLink.click();
        expandAllManLink.click();
        Actions sliderActions = new Actions(driver.getOriginalDriver());
        sliderActions.moveToElement(closeAllManLink);
        sliderActions.clickAndHold(verticalBarSlider);
        Log4Test.info("Move slider in Ref-s list");
        int numOfMan = listAllManufactors.size();
        int scrollStep = calculateScrollStep(verticalBar, verticalBarSlider, numOfMan);
        boolean foundFlag = false;
        for(int i=0; i < numOfMan; i++) {
            try {
                if(listAllManufactors.get(i).getText().equalsIgnoreCase(manufacter)) {
                    foundFlag = true;
                    sliderActions.release().perform();
                    listAllManufactors.get(i).click();
                    break;
                } else {
                    sliderActions.moveByOffset(0, scrollStep).build().perform();
                }
            } catch (Exception e) {
                Log4Test.error("Exception during Scrolling: " + e.getMessage());
            }
        }
        if(!foundFlag) {
            sliderActions.release().perform();
            Log4Test.error("Not found '" + manufacter + "' in list of manufacters");
        } else {
            Log4Test.info("Ref-s are filtered with '"+ manufacter +"'");
        }
        return this;
    }

    //click on 'Sort by' link and then choose 'By Price' from dropdown list
    public RefrigiratorsPage sortByPrice() {
        sortList.get(0).click();
        sortByPriceLink.click();
        Log4Test.info("Sorting Ref-s by price");
        return this;
    }

    //check if there more than 2 Ref-s in List with price
    private boolean isMoreThanOneRefInList() {
        if ((refsList.size() < 2) ||
            (refsList.get(1).findElements(priceRef).size() < 1) ||
            (refsList.get(0).findElements(priceRef).size() < 1)) {
            return false;
        } else {
            return true;
        }
    }

    //return price of refrigirator with number 'i' in list
    public float getPriceOfRef(int i) {
        String allPrice = refsList.get(i).findElement(priceRef).getText();
        allPrice = allPrice.replaceAll(" ", "");
        return Float.valueOf(allPrice.substring(0, allPrice.indexOf('\u0433')));
    }

    //return true if list of REFs is sorted by price desc (first's price < second's price)
    public boolean areREFsSortedByPrice() {
        if(isMoreThanOneRefInList()) {
            if (this.getPriceOfRef(0) <= this.getPriceOfRef(1)) {
                Log4Test.info("Ref-s are SORTED by price desc (" + getPriceOfRef(0) + " < " +
                        +getPriceOfRef(1) + ")");
                return true;
            } else {
                Log4Test.info("Ref-s are NOT SORTED -> 1: " + getPriceOfRef(0) + " | 2: " +
                        +getPriceOfRef(1) + ")");
                return false;
            }
        } else {
            Log4Test.info("Nothing to SORT: < 2 products of this manufacter");
            return true;
        }
    }

    public int calculateScrollStep(WebElement bar, WebElement slider, int numberOfElements ) {
        return Math.round((float)(bar.getSize().height - slider.getSize().height)/numberOfElements);
    }
}
