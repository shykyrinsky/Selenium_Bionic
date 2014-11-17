package functional;

import actors.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.RegistrationPage;
import pages.WelcomePage;

/**
 * Created by Illya on 03.11.2014.
 */
public class RegisterTest extends FunctionalTest {

    private User sameUser;

    //positive register test with all valid fields
    @Test
    public void allValidFieldsRegisterTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.initPage();                                //close popups
        RegistrationPage regPage = mainPage.openRegister();

        User usr = new User();                           //new random User
        sameUser = usr;                                  //save particular user in global variable
        regPage.setFieldsWithUserData(usr);
        WelcomePage wlcPage = regPage.submitSuccess();
        Assert.assertTrue(wlcPage.isOnPage(), "Registration Failed!");
    }


    //negative register test with the same email
    @Test (dependsOnMethods = "allValidFieldsRegisterTest")
    public void sameEmailRegisterTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.initPage();
        RegistrationPage regPage = mainPage.openRegister();
        regPage.setFieldsWithUserData(sameUser).submitFailed();         //use already registered user
        Assert.assertTrue(regPage.isError(), "NO error message is shown");
    }

}
