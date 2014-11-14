package functional;

import org.testng.annotations.DataProvider;
import java.lang.reflect.Method;


/**
 * Created by Illya on 06.11.2014.
 */
public class StaticDataProviders {

    @DataProvider(name = "products")
    public static Object[][] createData(Method m) {
        if (m.getName() == "isSearchResultPresentTest") {
            return new Object[][]{
                    {"Nexus"},                      //data for positive search test
            };
        } else {
            return new Object[][]{
                    {"potomu 4to gladioulus"},       //data for negative search test
            };
        }
    }

    @DataProvider(name = "compareProdData")
    public static Object[][] createProducts() {
        return new Object[][] {
                {"braun shave"},                      //data for compare prices test
        };
    }
}
