package functional;

import actors.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.RegistrationPage;

/**
 * Created by Illya on 03.11.2014.
 */
public class RegisterTest {

    //positive test with all valid fields
    @Test
    public void allValidFieldsRegisterTest() {
        MainPage mainPage = new MainPage();
        mainPage.openRegister();
        RegistrationPage regPage = new RegistrationPage();
        User usr = new User("example@valid", "nik", "qwerty");
        regPage.setFieldsWithUserData(usr);
        regPage.submit();
        Assert.assertTrue(!regPage.isError());
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
}
