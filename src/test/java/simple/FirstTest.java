package simple;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Bionic on 10/22/14.
 */
public class FirstTest {

    @Test
    public void firstTest() {
        System.out.print("new print");
        Assert.assertTrue(true);
    }
}
