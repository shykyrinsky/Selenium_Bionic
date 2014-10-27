package simple;

import org.testng.Assert;
import org.testng.annotations.Test;


public class PlusTest {

    @Test
    public void testSum() {
        Assert.assertEquals(7, Plus.sum(2, 5));
    }
}