package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.WebDriverWrapper;
import utils.Log4Test;

import javax.swing.*;
import java.util.List;

/**
 * Created by Illya on 12.11.2014.
 */
public class RefrigiratorsPage {

    protected WebDriverWrapper driver;

    private static String filterXpath = "//*[@id='filters']//a[contains(text(), 'manufactor')]";



    @FindBy(xpath = "//*[@id='filters']//div[@class='jspPane']/p/a")
    private List<WebElement> listAllManufactors;

    @FindBy(xpath = ".//*[@id='filters']//div[@class='jspTrack']")
    private WebElement verticalBar;

    @FindBy(xpath = ".//*[@id='filters']//div[@class='jspDrag']")
    private WebElement verticalBarSlider;

    @FindBy(xpath = "//*[@id='filters']//a[contains(@class,'switcher')]")
    private WebElement expandAllLink;

    @FindBy(xpath = "//span[@class='title-span']/a[@class='hide']")
    private WebElement hideToolbarLink;

    @FindBy(xpath = "//*[@id='filters']/div[6]/div/div")
    private WebElement scrollArea;

    @FindBy(xpath = "//*[@id='catalogue']//span[@class = 'ddopener']")
    private List<WebElement> sortList;

    @CacheLookup
    @FindBy(xpath = "//*[@id='catalogue']//a[contains(@href, 'sort=0')]")
    private WebElement sortByPriceLink;

    @FindBy(xpath = "//*[@id='catalogue']/ul[contains(@class, 'catalog')]/li")
    private List<WebElement> refsList;

    private static final By priceRef = By.className("orng");

    private WebElement getFilterRefElement(String dynamicXpath, String var) {
        return driver.findElement(By.xpath(dynamicXpath.replace("manufactor", var)));
    }

    public RefrigiratorsPage(WebDriverWrapper driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        Log4Test.info("Open RefrigiratorPage");
    }

    //click on '@manufactor' in list of manufactors
    public RefrigiratorsPage filterRefClick(String manufacter) {
        hideToolbarLink.click();


        ((JavascriptExecutor) driver.getOriginalDriver()).
                executeScript("window.scrollTo(0,(Math.max(document.documentElement.scrollHeight," +
                        " document.body.scrollHeight,document.documentElement.clientHeight)/7));");
        expandAllLink.click();
        //driver.switchTo().frame(scrollArea);
        /*int width = verticalBar.getSize().width;
        WebElement filterElement = getFilterRefElement(filterXpath, manufacter);
        Actions sliderActions = new Actions(driver.getOriginalDriver());
        sliderActions.clickAndHold(verticalBarSlider);

        sliderActions.moveByOffset(0,40).build().perform();*/

        ((JavascriptExecutor) driver.getOriginalDriver()).
                executeScript("arguments[0].scrollTop = arguments[1];", scrollArea, 700);
        //scrollWithOffset(filterElement,0,-20);

        //listAllManufactors.get(56).click();
        /*try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        WebElement filterElement = getFilterRefElement(filterXpath, manufacter);
        //WebDriverWait wait = new WebDriverWait(driver, 5);
        //wait.until(ExpectedConditions.visibilityOf(filterElement));
        getNumberOfMan(manufacter);
        filterElement.click();
        Point p = ((Locatable) filterElement).getCoordinates().inViewPort();
        Log4Test.info(p.toString());
        Log4Test.info("Filter Ref-s with '"+ manufacter +"'");
        return this;
    }

    //click on 'Sort by' link and then choose 'By Price' from dropdown list
    public RefrigiratorsPage sortByPrice() {
        sortList.get(0).click();
        sortByPriceLink.click();
        Log4Test.info("Sorting Ref-s by price");
        return this;
    }

    //return price of refrigirator with number 'i' in list
    public float getPriceOfRef(int i) {
        String allPrice = refsList.get(i).findElement(priceRef).getText();
        allPrice = allPrice.replaceAll(" ", "");
        return Float.valueOf(allPrice.substring(0, allPrice.indexOf('\u0433')));
    }

    //return true if list of REFs is sorted by price desc (first's price < second's price)
    public boolean areREFsSortedByPrice() {
        if (this.getPriceOfRef(0) < this.getPriceOfRef(1)) {
            Log4Test.info("Ref-s are SORTED by price desc (" + getPriceOfRef(0) +" < "+
                                                             + getPriceOfRef(1) + ")");
            return true;
        } else {
            Log4Test.info("Ref-s are NOT SORTED -> 1: "  + getPriceOfRef(0) + " | 2: "+
                                                         + getPriceOfRef(1) + ")");
            return false;
        }
    }

    public void scrollWithOffset(WebElement webElement, int x, int y) {

        String code = "window.scroll(" + (webElement.getLocation().x + x) + ","
                + (webElement.getLocation().y + y) + ");";

        ((JavascriptExecutor)driver.getOriginalDriver()).executeScript(code, webElement, x, y);

    }

    private int getNumberOfMan(String manufactor){
        for(int i=listAllManufactors.size()-1; i>=0; i--) {
            Log4Test.info(listAllManufactors.get(i).getText());
            if (listAllManufactors.get(i).getText() == manufactor) {
                return i;
            }
        }
        return -1;
    }
}
