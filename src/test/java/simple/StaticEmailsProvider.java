package simple;

import org.testng.annotations.DataProvider;

public class StaticEmailsProvider {

   @DataProvider( name = "emailsSet")
    public static Object[][] createData() {
        return new Object[][] {
                { new Boolean("true"), "yahoo@yahoo.com"},
                { new Boolean("false"), "yahoo@ya.hoo.com"}
                };
    }
}
