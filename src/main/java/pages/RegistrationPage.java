package pages;

import actors.User;

/**
 * Created by Illya on 03.11.2014.
 */
public class RegistrationPage {

  /*  public RegistrationPage setEmail(String email) {

        return this;
    }

    public RegistrationPage setNik(String nik) {

        return this;
    }

    public RegistrationPage setPassw(String passw) {

        return this;
    }
  */
    // fill all fields with user's data
   public RegistrationPage setFieldsWithUserData( User user) {

    return this;
   }

    //click "Register" Button
   public RegistrationPage submit() {

        return this;
   }

    //return True if any field is highlighted in red colour
   public boolean isError() {

       return false;
   }

    //check "Show Password" checkbox
   public RegistrationPage showPassw() {

       return this;
   }
}
