package functional;

import actors.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.RegistrationPage;
import pages.WelcomePage;

import java.util.concurrent.TimeUnit;

/**
 * Created by Illya on 03.11.2014.
 */
public class RegisterTest {

    protected WebDriver driver;
    private static final String BASE_URL = "http://hotline.ua";
    private User sameUser;

    @BeforeSuite
    public void setUp () {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    //positive test with all valid fields
    @Test
    public void allValidFieldsRegisterTest() {
        driver.get(BASE_URL);
        MainPage mainPage = new MainPage(driver);
        mainPage.initPage();                                //close popups
        RegistrationPage regPage = mainPage.openRegister();

        User usr = new User();                           //new random User
        sameUser = usr;                                  //save particular user in global variable
        regPage.setFieldsWithUserData(usr);
        WelcomePage wlcPage = regPage.submitSuccess();
        Assert.assertTrue(wlcPage.isOnPage(), "Registration Successfull");
    }

    //negative test with the same email
    @Test (dependsOnMethods = "allValidFieldsRegisterTest")
    public void sameEmailRegisterTest() {
        driver.get(BASE_URL);
        MainPage mainPage = new MainPage(driver);
        mainPage.initPage();
        RegistrationPage regPage = mainPage.openRegister();
        regPage.setFieldsWithUserData(sameUser).submitFailed();         //use already registered user
        Assert.assertTrue(regPage.isError(), "Registration Failed");

    }

    @AfterSuite
    public void tearDown () {
       driver.quit();
    }
}
