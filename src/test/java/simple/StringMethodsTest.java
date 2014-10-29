package simple;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


import static org.testng.Assert.*;

public class StringMethodsTest {

    private String originS, subS;

    // Clear file testPresenceOfStringDetails.txt
    @BeforeMethod
    public void setUp() throws Exception {
        try {
            Files.write( Paths.get(".\\src\\test\\resources\\testPresenceOfStringDetails.txt"), "".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Write input data to file testPresenceOfStringDetails.txt
    @AfterMethod
    public void tearDown() throws Exception {
        String res = originS + ", " + subS;
        try {
            Files.write( Paths.get(".\\src\\test\\resources\\testPresenceOfStringDetails.txt"), res.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Tests method "presenceOfString()" using different input data
    @Test( dataProvider = "stringsSet", dataProviderClass = StaticDataProvider.class )
    public void testPresenceOfString( String originStr, String subStr, boolean expected) throws Exception {
        Assert.assertEquals(expected, StringMethods.presenceOfString(originStr, subStr));
        System.out.println('"' + subStr + '"' + " is a part of " + '"' + originStr + "\" -> " + expected );
        originS = originStr;
        subS = subStr;
    }
}