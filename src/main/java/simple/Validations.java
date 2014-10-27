package simple;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Illya on 24.10.2014.
 */
public class Validations {

    //Returns TRUE if email is valid, and FALSE if email isn't valid
    public static boolean validateEmail (String email) {
        Pattern p = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");
        Matcher m = p.matcher(email);
        return m.matches();
    }

}