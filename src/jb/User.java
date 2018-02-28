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
public class User {
    //private static final Map<String, User> USERS = new HashMap<String, User>();

    private String userName;
    private String email;
    private String password;
    private boolean admin;
    private int userId;

    public User(String userName) {
        this.userName = userName;
        admin = false;
    }

    public String getUserName() {
        return userName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the email to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean getAdmin() {
        return this.admin;
    }

    public void setUserId(int id) {
        this.userId = id;
    }

    public int getUserId() {
        return this.userId;
    }
}



/**
 *
 * @author Jacek Byzdra
 * email: jacekbwwa@gmail.com
 */