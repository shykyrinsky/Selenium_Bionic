package actors;

/**
 * Created by Illya on 03.11.2014.
 */
public class User {
    public String email;
    public String nick;
    public String passw;

    private static final String BASE_EMAIL_NAME = "user";
    private static final String BASE_EMAIL_DOMAIN = "@mail.ua";
    private static final String BASE_NICK = "nick";
    private static final String BASE_PASS = "123";

<<<<<<< Updated upstream
    //constructor that every time returns diff user
    public User () {
        String rand = "" + Math.round(Math.random()*10000);
        this.email = BASE_EMAIL_NAME + rand + BASE_EMAIL_DOMAIN ;
        this.nick = BASE_NICK + rand;
        this.passw = BASE_PASS + rand;
    }
=======
>>>>>>> Stashed changes

}
