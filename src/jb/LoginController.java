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
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class LoginController implements Initializable {

    @FXML
    private TextField userIdField;
    @FXML
    private PasswordField passwordField;  
    @FXML
    private AnchorPane anchorDown;
   

    @FXML
    protected void processLogin() {
        if (!LoginUser.userLogging(userIdField.getText(), passwordField.getText())) {
            WelcomeApp.getInstance().showMessage("Login", 
            "Username/password combination is invalid.", "Are you new? Please use register option.");
        }
    }

    @FXML
    protected void processRegister() {
        WelcomeApp.getInstance().gotoRegister();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ScatterShapes();
        // ScatterLetters();

        userIdField.setPromptText("enter your user name");
        passwordField.setPromptText("enter your password");
    }

    public void ScatterShapes() {
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < 500; i++) {
            Circle circle = new Circle(random.nextInt(1920), random.nextInt(114), random.nextInt(8));
            circle.setFill(Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255), 0.5));
            anchorDown.getChildren().add(circle);
        }
    }

    public void ScatterLetters() {
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < 6; i++) {
            for (int k = 9; k < 32; k++) {

                Text text = new Text(random.nextInt(1920), random.nextInt(490), "Visual Data");
                text.setFont(Font.font("Verdana", random.nextInt(k)));
                text.setFill(Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255), 1));

                anchorDown.getChildren().add(text);
            }
        }
    }

}
/**
 *
 * @author Jacek Byzdra
 * email: jacekbwwa@gmail.com
 */