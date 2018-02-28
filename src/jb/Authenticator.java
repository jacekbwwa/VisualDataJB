/**
 *
 * @author Jacek Byzdra
 * email: jacekbwwa@gmail.com
 */
package jb;


import java.util.HashMap;
import java.util.Map;

//Class for authenticate user without data base
public class Authenticator {
    private static final Map<String, String> USERS = new HashMap<String, String>();
    static {
        USERS.put("*****","*****");
    }
    public static boolean validate(String user, String password){
        String validUserPassword = USERS.get(user);
        return validUserPassword != null && validUserPassword.equals(password);
    }
}