/**
 *
 * @author Jacek Byzdra
 * email: jacekbwwa@gmail.com
 */
package jb;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author Jacek Byzdra
 * email: jacekbwwa@gmail.com
 */
public class RegisterController implements Initializable {

    @FXML
    private TextField userName;
    @FXML
    private PasswordField userPassword;
    @FXML
    private PasswordField userPasswordConfirm;
    @FXML
    private TextField userEmail;
    @FXML
    private TextField userEmailConfirm;

    private User registeredUser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        registeredUser = null;
    }

    @FXML
    protected void btnCancelClick() {
        WelcomeApp.getInstance().closeRegister();
    }

    @FXML
    protected void btnSignUpClick() {
        if (CreateUser()) {
            WelcomeApp.getInstance().closeRegister();
        }
    }

    private boolean CreateUser() {
        if (!validateUser()) {
            return false;
        }
        registeredUser = new User(userName.getText().trim());
        registeredUser.setEmail(userEmail.getText());
        registeredUser.setPassword(userPassword.getText());

        // send to database:
        if (DBConnection.registerUser(registeredUser)) {
            // if success:
            WelcomeApp.getInstance().showMessage("Congratulation!", "You are registered in this application", "Please log in now");
            return true;
        }
        // if unsuccess:
        WelcomeApp.getInstance().showMessage("Warrning", "Registration error", "Please try again");
        return false;
    }
/**
 *
 * @author Jacek Byzdra
 * email: jacekbwwa@gmail.com
 */
    private boolean validateUser() {
        String str = userName.getText().trim();
        if (str.isEmpty()) {
            WelcomeApp.getInstance().showMessage("Warning", "User name is incorrect", "Please enter correct user name for register");
            return false;
        }
        String password = userPassword.getText();
        if (password.isEmpty()) {
            WelcomeApp.getInstance().showMessage("Warning", "Password is empty", "Please enter password for register");
            return false;
        }
        String passwordConfirm = userPasswordConfirm.getText();
        if (!passwordConfirm.equals(password)) {
            WelcomeApp.getInstance().showMessage("Warning", "Incorrect password confirmation", "Please enter correct password confirmation for register");
            return false;
        }
        String email = userEmail.getText().trim();
        if (email.isEmpty()) {
            WelcomeApp.getInstance().showMessage("Warning", "Email address is empty", "Please enter your email address for register");
            return false;
        }
        if (!email.contains("@")) {
            WelcomeApp.getInstance().showMessage("Warning", "Incorrect email address", "Please enter correct your email address for register");
            return false;
        }
        String emailConfirm = userEmailConfirm.getText();
        if (!emailConfirm.equals(email)) {
            WelcomeApp.getInstance().showMessage("Warning", "We need confirm your email address", "Please enter correct email confirmation for register");
            return false;
        }
        return true;
    }
}
