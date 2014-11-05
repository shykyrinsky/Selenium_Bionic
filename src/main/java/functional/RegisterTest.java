package functional;

import actors.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.RegistrationPage;

/**
 * Created by Illya on 03.11.2014.
 */
public class RegisterTest {

   public WebDriver driver;

    @BeforeSuite
    public void setUP () {
        driver = new FirefoxDriver();
    }

    //positive test with all valid fields
    @Test
    public void allValidFieldsRegisterTest() {
        //MainPage mainPage = new MainPage();
        driver.get("http://hotline.ua");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("close"))));

        driver.findElement(By.className("close")).click();
        driver.findElement(By.className("blue-button")).click();

        //mainPage.openRegister();
        driver.findElement(By.className("reg")).click();

        driver.findElement(By.name("email")).sendKeys("email@email.com");
        driver.findElement(By.name("nick")).sendKeys("Emac");
        driver.findElement(By.name("password")).sendKeys("12321");
        driver.findElement(By.name("password2")).sendKeys("12321");
        driver.findElement(By.className("blue-button")).click();
        Assert.assertTrue(driver.findElement(By.name("first-name")).isDisplayed(), "Register failed");

        //driver as parameter constructora for dif pages
        //RegistrationPage regPage = new RegistrationPage();
        //User usr = new User("example@valid", "nik", "qwerty");

        //regPage.setFieldsWithUserData(usr);
        //regPage.submit();
        //Assert.assertTrue(!regPage.isError());
    }

    //negative test with empty string as email
    @Test
    public void notValidEmailRegisterTest() {
        MainPage mainPage = new MainPage();
        mainPage.openRegister();
        RegistrationPage regPage = new RegistrationPage();
        User usr = new User("", "nik1", "qwerty");
        regPage.setFieldsWithUserData(usr);
        regPage.submit();
        Assert.assertTrue(regPage.isError());

    }

    @AfterSuite
    public void tearDown () {
        driver.quit();
    }
}
