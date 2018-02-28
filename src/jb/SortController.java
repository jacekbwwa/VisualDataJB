/**
 *
 * @author Jacek Byzdra
 * email: jacekbwwa@gmail.com
 */
package jb;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.animation.SequentialTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

/**
 *
 * @author Jacek Byzdra
 * email: jacekbwwa@gmail.com
 */
public class SortController implements Initializable {

    @FXML
    SplitPane drawPane;
    @FXML
    BarChart barChart;
    @FXML
    TextArea textArea;
    @FXML
    Button bubbleSortBtn;
    @FXML
    Button generateBtn;
    @FXML
    Button insertionSortBtn;
    @FXML
    Button selectionSortBtn;
    @FXML
    Button quickSortBtn;
    @FXML
    Button heapSortBtn;
    @FXML
    Button notVisibleBtn;
    @FXML
    Separator separator1;
    @FXML
    Separator separator2;
    @FXML
    Separator separator3;
    @FXML
    Separator separator4;
    @FXML
    Separator separator5;
    @FXML
    Separator separator6;
    @FXML
    AnchorPane dataPane;
    @FXML
    AnchorPane anchorPaneAlg;
    @FXML
    Slider speedSlider;
    @FXML
    Button btnGoToHome;
    @FXML
    private MenuItem userMenu;  
    @FXML
    private TextField textFieldSortInteger;

    private double speed = 400; // do ustawani czasu z suwaka
    private final Sort sort = new Sort(); // klasa z algorytmami sortowania
    ArrayList<StackPane> dataList = new ArrayList<>(); // lista do sortowania
    private int userNum;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        barChart.setVisible(false);
        bubbleSortBtn.setVisible(false);
        insertionSortBtn.setVisible(false);
        selectionSortBtn.setVisible(false);
        quickSortBtn.setVisible(false);
        heapSortBtn.setVisible(false);
        notVisibleBtn.setVisible(false);
        separator1.setVisible(false);
        separator2.setVisible(false);
        separator3.setVisible(false);
        separator4.setVisible(false);
        separator5.setVisible(false);
        separator6.setVisible(false);

        userMenu.setText(LoginUser.getLoggedUser().getUserName());
    }

    @FXML
    private void handleLogout() {
        LoginUser.userLogout();
    }

    @FXML
    public void goToHome(ActionEvent event) {
        if (event.getSource() == btnGoToHome) {
            WelcomeApp.getInstance().gotoMenu();
        }
    }

    private void setVisibleSortButtons(boolean visible) {
        bubbleSortBtn.setVisible(visible);
        insertionSortBtn.setVisible(visible);
        selectionSortBtn.setVisible(visible);
        quickSortBtn.setVisible(visible);
        heapSortBtn.setVisible(visible);
    }

    private void disableSpeedSlider() {
        speed = speedSlider.valueProperty().doubleValue();
        speedSlider.setDisable(true);
    }

    private void enableSpeedSlider() {
        speed = speedSlider.valueProperty().doubleValue();
        speedSlider.setDisable(false);
    }

    private int userSortNodeNumber() { // method return number of data to generate, which user entered in text field
        String s = textFieldSortInteger.getText();
        return Integer.parseInt(s);
    }

    private boolean validateUserNumber() { // method validate if user typed a valid number (range, no letter etc)
        //this.userNum = userNum;
        if ((textFieldSortInteger.getText() == null)) {
            WelcomeApp.getInstance().showMessage("VisualDataJB", "No number entered", 
            "Please provide integer number");
            return false;
        }
        try {
            userNum = userSortNodeNumber();
        } catch (Exception ex) {
            WelcomeApp.getInstance().showMessage("VisualDataJB", "Incorrect sort number", 
            "If you want generate sort number  please enter correct value");
            return false;
        }

        if (userNum <= 0 || userNum > 46) {
            WelcomeApp.getInstance().showMessage("VisualDataJB", "Incorrect sort number", 
            "If you want generate sort number  please enter correct value");
            return false;
        }
        return true;

    }
/**
 *
 * @author Jacek Byzdra
 * email: jacekbwwa@gmail.com
 */
    @FXML
    private void handleButtonGenerate(ActionEvent event) {
        if (validateUserNumber() == false) {
            return;
        } else {

            double d = dataPane.heightProperty().doubleValue();
            //this.userNum = userNum;

            dataList = sort.generateDataList(userNum, d - 30);

            HBox hBox = new HBox(sort.getSpaceEl());

            dataPane.getChildren().clear();
            dataPane.getChildren().add(hBox);
            hBox.prefWidthProperty().bind(dataPane.widthProperty());
            hBox.prefHeightProperty().bind(dataPane.heightProperty());

            hBox.setPadding(new Insets(15, 12, 15, 12));
            hBox.setSpacing(sort.getSpaceEl());
            hBox.setStyle("-fx-background-color: #336699;");

            hBox.setAlignment(Pos.BASELINE_LEFT);

            hBox.getChildren().addAll(dataList);

            setVisibleSortButtons(true);
        }
    }

    @FXML
    private void bubbleSorting(ActionEvent event) {
        disableSpeedSlider();
        SequentialTransition st = sort.bubbleSort(dataList, speed);
        st.onFinishedProperty().set(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                enableSpeedSlider();
            }
        });
        st.play();
    }

    @FXML
    private void insertionSorting(ActionEvent event) {
        disableSpeedSlider();
        SequentialTransition st = sort.insertionSort(dataList, speed);
        st.onFinishedProperty().set(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                enableSpeedSlider();
            }
        });
        st.play();
    }

    @FXML
    private void selectionSorting(ActionEvent event) {
        disableSpeedSlider();
        SequentialTransition st = sort.selectionSort(dataList, speed);
        st.onFinishedProperty().set(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                enableSpeedSlider();
            }
        });
        st.play();
    }

    @FXML
    private void quickSortingData(ActionEvent event) {
        disableSpeedSlider();
        SequentialTransition st = sort.quickSort(dataList, speed);
        st.onFinishedProperty().set(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                enableSpeedSlider();
            }
        });
        st.play();
    }

    @FXML
    private void heapSortingData(ActionEvent event) {
        //WelcomeApp.getInstance().showMessage("","", "Metoda merge sort ciagle jeszcze w opracowaniu : (");
        disableSpeedSlider();
        SequentialTransition st = sort.heapSort(dataList, speed);
        int tab[] = sort.dataListToArray(dataList);
        st.onFinishedProperty().set(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                enableSpeedSlider();
            }
        });
        st.play();
    }
}
