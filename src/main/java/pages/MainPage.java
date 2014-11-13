package pages;


import org.openqa.selenium.By;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import org.testng.Assert;
import selenium.WebDriverWrapper;
import utils.Log4Test;

import java.util.List;


/**
 * Created by Illya on 03.11.2014.
 */
public class MainPage {


    private static final String URL_MATCH = "http://hotline.ua";

    protected WebDriverWrapper driver;

    @FindBy(className = "close")
    private WebElement popupClose;

    @FindBy(xpath = ".//div[contains(@class,'region-doubtfulness-popup')]/span[@class='close']")
    private WebElement regPopupClose;

    @FindBy(className = "reg")
    private WebElement registerLink;

    @FindBy(id = "searchbox")
    private WebElement searchBox;

    @FindBy(xpath = "//*[@id='test']//ul[contains(@class,'catalog')]/li")       //one type of search results
    private List<WebElement> searchResults;

    @FindBy(xpath = "//*[@id='test']//div[@class = 'tx-price list result']")     //another type of search results
    private List<WebElement> searchResultsOther;

    @FindBy(xpath = "//*[@id='test']//div[@class='search-result-page no']")
    private WebElement noResults;

    private final By compareBtn = By.xpath(".//a[contains(text(),'Сравнить цены')]");

    @FindBy(xpath = "//a[@href='/bt/']")
    private WebElement menuItemBT;

    @FindBy(xpath = "//a[@href='/bt/holodilniki/']")
    private WebElement subMenuItemRefs;


    public MainPage(WebDriverWrapper driver) {
        if (!driver.getCurrentUrl().contains(URL_MATCH)) {
            Assert.fail("This is not the page you are expected");
        }
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

        //close popups if they displayed
    public MainPage initPage() {
        if(popupClose.isDisplayed()) {
            popupClose.click();
        }
        if(regPopupClose.isDisplayed()) {
            regPopupClose.click();
        }
        return this;
    }

        //open RegisterPage
    public RegistrationPage openRegister() {
        registerLink.click();
        return new RegistrationPage(driver);
    }

        //enter @product in searchBox and submit
    public MainPage searchProduct(String product) {
        searchBox.clear();
        searchBox.sendKeys(product);
        searchBox.submit();
        Log4Test.info("Searching for '" + product + "'");
        return this;
    }
        //return true if there is any result of search is displayed on page
    public boolean verifySearchResultsPresent() {
        if (!searchResults.isEmpty() || !searchResultsOther.isEmpty()) {
            Log4Test.info("This product IS found in search results");
            return true;
        } else {
            Log4Test.error("This product is NOT found in search results");
            return false;
        }
    }
        //return true if "No results of search" message is displayed
    public boolean verifyNoSearchResults() {
        if (noResults.isDisplayed()) {
            Log4Test.info("This product is NOT found in search results");
            return true;
        } else {
            Log4Test.error("There is no message about NO SUCH PRODUCT");
            return false;
        }
    }

        //click on "Compare Price" Button
    public ComparsionPage comparePrices() {
        searchResults.get(0).findElement(compareBtn).click();
        Log4Test.info("click on 'Compare Price' button");
        return new ComparsionPage(driver);
    }
        //select submenu "Refrigirators" from menu "Household Technicks"
    public RefrigiratorsPage selectSubMenuREFs() {
        Actions actions = new Actions(driver.getOriginalDriver());
        actions.moveToElement(menuItemBT).build().perform();
        subMenuItemRefs.click();
        return new RefrigiratorsPage(driver);
    }

}
