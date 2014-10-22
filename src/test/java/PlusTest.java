import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import static org.testng.Assert.*;

public class PlusTest {

    @Test
    public void testPlus() {
        Assert.assertEquals(5, Plus.plus(2, 3));
        

    }
}