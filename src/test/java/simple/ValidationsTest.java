package simple;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ValidationsTest {

    //Using @Parameters to pass data in test method
    @Test
    @Parameters ({ "expResult", "emailString" })
    public void testValidateEmail( boolean expResult, String emailString ) {
        Assert.assertEquals( expResult, Validations.validateEmail( emailString ));
        System.out.println('"' + emailString  + "\" is valid - " + expResult );
    }

    //create data in dataProvider
    @DataProvider ( name = "setOfEmails" )
    public Object[][] createEmails() {
        return new Object[][] {
                    { new Boolean("True"), "hot@hotmail.com" },
                    { new Boolean("False"), "hothotmail.com" }
                    };
    }

    //Using @DataProvider to pass data in test method
    @Test( dataProvider = "setOfEmails")
    public void testValidEmailwithProvider( boolean expResult, String emailString ) {
       testValidateEmail( expResult, emailString );
    }

    //Using @DataProvider from another class
    @Test ( dataProvider = "emailsSet", dataProviderClass = StaticEmailsProvider.class)
    public void testValidEmailwithStProvider( boolean expResult, String emailString ) {
        testValidateEmail(expResult, emailString);
    }
}