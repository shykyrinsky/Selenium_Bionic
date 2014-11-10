package functional;

import actors.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.RegistrationPage;
import pages.WelcomePage;



import java.util.concurrent.TimeUnit;

/**
 * Created by Illya on 03.11.2014.
 */
public class RegisterTest extends FunctionalTest {

<<<<<<< Updated upstream

    private User sameUser;
=======
    protected WebDriver driver;


    @BeforeSuite
    public void setUP () {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }
>>>>>>> Stashed changes

    //positive register test with all valid fields
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
        Assert.assertTrue(wlcPage.isOnPage(), "Registration Failed");
    }

<<<<<<< Updated upstream
    //negative register test with the same email
    @Test (dependsOnMethods = "allValidFieldsRegisterTest")
    public void sameEmailRegisterTest() {
        driver.get(BASE_URL);
        MainPage mainPage = new MainPage(driver);
        mainPage.initPage();
        RegistrationPage regPage = mainPage.openRegister();
        regPage.setFieldsWithUserData(sameUser).submitFailed();         //use already registered user
        Assert.assertTrue(regPage.isError(), "NO error message is shown");
=======
    //negative test with empty string as email
    @Test
    public void notValidEmailRegisterTest() {

>>>>>>> Stashed changes

    }

}
