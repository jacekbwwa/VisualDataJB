/**
 *
 * @author Jacek Byzdra
 * email: jacekbwwa@gmail.com
 */
package jb;

/**
 *
 * @author Jacek Byzdra
 * email: jacekbwwa@gmail.com
 */
public class LoginUser {

    private static User loggedUser;

    public LoginUser() {

    }

    public static User getLoggedUser() {
        return loggedUser;
    }

    public static boolean userLogging(String userId, String password) {
        /*if (Authenticator.validate(userId, password)) {
            loggedUser = new User(userId);// User.of(userId);
            //WelcomeApp.getInstance().setLoggedUser(loggedUser);
            WelcomeApp.getInstance().gotoMenu();
            return true;
        } else {
            return false;
        }*/
        if (DBConnection.validateUser(userId, password)) {
            loggedUser = new User(userId);
            loggedUser.setAdmin(DBConnection.isAdmin(userId));
            WelcomeApp.getInstance().gotoMenu();
            return true;
        } else {
            return false;
        }
    }
/**
 *
 * @author Jacek Byzdra
 * email: jacekbwwa@gmail.com
 */
    public static void userLogout() {
        loggedUser = null;
        WelcomeApp.getInstance().gotoLogin();
    }

}
