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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author Jacek Byzdra
 * email: jacekbwwa@gmail.com
 */
public class DescriptionController implements Initializable {
    
    @FXML
    private Label labelDescription;
    @FXML
    private Label labelAuthor;
    /**
     * Initializes the controller class.
     */
 @Override
    public void initialize(URL url, ResourceBundle rb) {        
        labelDescription.setFont(Font.font("Verdana", FontWeight.NORMAL, 14));
        String info = "This program has been prepared for underground students of the Computer Science " +
            "to help them understand the basic functionality of  algorithms and data structures.";
        labelDescription.setText(info);
        labelAuthor.setFont(Font.font("Verdana", FontWeight.NORMAL, 14));
        String author = "National College of Ireland, 2016, Jacek Byzdra";
        labelAuthor.setText(author);
    }     
           
    @FXML protected void btnCloseClick() {        
        WelcomeApp.getInstance().closeDescription();
    }
            
}
