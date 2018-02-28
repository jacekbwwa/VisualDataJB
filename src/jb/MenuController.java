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
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;

public class MenuController implements Initializable {

    @FXML
    private Button sortsBtnMain;
    @FXML
    private Button sortsBtnPanel;
    @FXML
    private Button quizBtn;
    @FXML
    private Button descriptionBtn;

    @FXML
    private Button notVisibleBtn1;
    @FXML
    private Button treeBSTBTN;
    @FXML
    private Button notVisibleBtn2;
    @FXML
    private Button graphsBtn;
    @FXML
    private Button notVisibleBtn3;
    @FXML
    private Button notVisibleBtn4;
    @FXML
    private Button notVisibleBtn5;
    @FXML
    private Separator separator1;
    @FXML
    private Separator separator2;
    @FXML
    private Separator separator3;
    @FXML
    private Separator separator4;
    @FXML
    private Separator separator5;
    @FXML
    private Separator separator6;
    @FXML
    private Separator separator7;
    @FXML
    private Separator separator8;
    @FXML
    private Separator separator9;
    @FXML
    private Separator separator10;
    @FXML
    private Separator separator11;
    @FXML
    private Separator separator12;
    @FXML
    private MenuItem userMenu;
    @FXML
    private TextArea textAreaLeft;
    @FXML
    private TextArea textAreaRight;
    @FXML
    private TextArea textAreaMiddle;

    @FXML
    private MenuItem adminBtnMenu;

    @FXML
    public void handleQuizBtn(ActionEvent event) {
        if (event.getSource() == quizBtn) {
            WelcomeApp.getInstance().gotoQuiz();
        }
    }

    @FXML
    public void handleDescriptionBtn(ActionEvent event) {
        if (event.getSource() == descriptionBtn) {
            WelcomeApp.getInstance().gotoDescription();
        }
    }

    @FXML
    public void handlesortsBtnBar(ActionEvent event) {
        if (event.getSource() == sortsBtnMain) {
            WelcomeApp.getInstance().gotoHome();
        }
    }

    @FXML
    public void handlesortsBtnPanel(ActionEvent event) {
        if (event.getSource() == sortsBtnPanel) {
            WelcomeApp.getInstance().gotoSort();
        }
    }

    @FXML
    public void handletreeBSTBtn(ActionEvent event) {
        if (event.getSource() == treeBSTBTN) {
            WelcomeApp.getInstance().gotoTreeBST();
        }
    }

    @FXML
    public void handlegraphsBtn(ActionEvent event) {
        if (event.getSource() == graphsBtn) {
            WelcomeApp.getInstance().gotoGraph();
        }
    }

    @FXML
    private void handleLogout() {
        LoginUser.userLogout();
    }

    @FXML
    private void handleAdministrationPanel() {
        WelcomeApp.getInstance().gotoAdminPanel();
    }

    @FXML
    private void handleBubbleSortMenuItem(ActionEvent event) {
        try {
            AlgorithmsDescription algDesc = new AlgorithmsDescription();            
            cleanTextAreas();           
            textAreaRight.appendText(algDesc.getBubbleSortCode());            
            textAreaLeft.appendText(algDesc.getBubbleSortDescription().toUpperCase());            
            algDesc.BubbleSortArea(textAreaMiddle);
        } catch (Exception e) {
            String m = e.getMessage();
            String n = m;
        }
    }
    
    private void cleanTextAreas(){
        textAreaRight.clear();
        textAreaLeft.clear();
        textAreaMiddle.clear();
        textAreaRight.setText("\n");        
        textAreaLeft.setText("\n");
    }
    
    @FXML
    private void hndleInsertionSortMenuItem(ActionEvent event) {
        try {
            AlgorithmsDescription algDesc = new AlgorithmsDescription();
            cleanTextAreas();
            textAreaRight.appendText(algDesc.getInsertionSortCode());  
            textAreaLeft.appendText(algDesc.getInsertionSortDescription().toUpperCase());  
            textAreaMiddle.appendText(algDesc.getInsertionSortSteps());
            
        } catch (Exception e) {
            String m = e.getMessage();
            String n = m;
        }
    }
    
    @FXML
    private void handleSelectionMenuItem(ActionEvent event) {
        try {
            AlgorithmsDescription algDesc = new AlgorithmsDescription();
            cleanTextAreas();
            textAreaRight.appendText(algDesc.getSelectionSortCode());  
            textAreaLeft.appendText(algDesc.getSelectionSortDescription().toUpperCase());
            textAreaMiddle.appendText(algDesc.getSelectionSortSteps());            
            
        } catch (Exception e) {
            String m = e.getMessage();
            String n = m;
        }
    }
    
    @FXML
    private void hndleMergeSortMenuItem(ActionEvent event) {
        try {
            AlgorithmsDescription algDesc = new AlgorithmsDescription();
            cleanTextAreas();
            textAreaRight.appendText(algDesc.getMergeSortCode());  
            textAreaLeft.appendText(algDesc.getMergeSortDescription().toUpperCase());            
            textAreaMiddle.appendText(algDesc.getMergeSortSteps());
        } catch (Exception e) {
            String m = e.getMessage();
            String n = m;
        }
    }
    
    @FXML
    private void hndleQuickSortMenuItem(ActionEvent event) {
        try {
            AlgorithmsDescription algDesc = new AlgorithmsDescription();
            cleanTextAreas();
            textAreaRight.appendText(algDesc.getQuickSortCode());  
            textAreaLeft.appendText(algDesc.getQuickSortDescription().toUpperCase());  
            algDesc.showQuickSortSteps(textAreaMiddle);
            
        } catch (Exception e) {
            String m = e.getMessage();
            String n = m;
        }
    }
    
    @FXML
    private void hndleBSTMenuItem(ActionEvent event) {
        try {
            AlgorithmsDescription algDesc = new AlgorithmsDescription();
            cleanTextAreas();
            textAreaRight.appendText(algDesc.getBSTCode());  
            textAreaLeft.appendText(algDesc.getBSTDescription().toUpperCase());            
            textAreaMiddle.appendText(algDesc.getBSTAddDescription());
        } catch (Exception e) {
            String m = e.getMessage();
            String n = m;
        }
    }
    
    @FXML
    private void hndleGraphMenuItem(ActionEvent event) {
        try {
            AlgorithmsDescription algDesc = new AlgorithmsDescription();
            cleanTextAreas();
            textAreaRight.appendText(algDesc.getGraphCode());  
            textAreaLeft.appendText(algDesc.getGraphDescription().toUpperCase());            
            textAreaMiddle.appendText(algDesc.getGraphAddDescription());
        } catch (Exception e) {
            String m = e.getMessage();
            String n = m;
        }
    }
/**
 *
 * @author Jacek Byzdra
 * email: jacekbwwa@gmail.com
 */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        notVisibleBtn1.setVisible(false);
        notVisibleBtn2.setVisible(false);
        notVisibleBtn3.setVisible(false);
        notVisibleBtn4.setVisible(false);
        notVisibleBtn5.setVisible(false);
        separator1.setVisible(false);
        separator2.setVisible(false);
        separator3.setVisible(false);
        separator4.setVisible(false);
        separator5.setVisible(false);
        separator6.setVisible(false);
        separator7.setVisible(false);
        separator8.setVisible(false);
        separator9.setVisible(false);
        separator10.setVisible(false);
        separator11.setVisible(false);
        separator12.setVisible(false);

        userMenu.setText(LoginUser.getLoggedUser().getUserName());
        adminBtnMenu.setVisible(false);
        if (LoginUser.getLoggedUser().getAdmin()) {
            adminBtnMenu.setVisible(true);
        }
    }
}
