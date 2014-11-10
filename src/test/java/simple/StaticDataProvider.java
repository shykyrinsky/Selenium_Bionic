package simple;

import org.testng.annotations.DataProvider;

public class StaticDataProvider {

   @DataProvider( name = "emailsSet")
    public static Object[][] createData() {
        return new Object[][] {
                { new Boolean("true"), "yahoo@yahoo.com"},
                { new Boolean("false"), "yahoo@ya.hoo.com"}
                };
    }

    @DataProvider( name = "stringsSet")
    public static Object[][] createStrings() {
        return new Object[][] {
                { "Ababagalamaga", "baba", new Boolean ("true") },
                { "Bueno Chan Chan", "Chan", new Boolean ("true") },
                { "Nothing Really Matters", "Chan", new Boolean ("false") },
        };
    }
}
