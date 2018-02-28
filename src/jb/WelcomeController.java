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
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.PathTransition;
import javafx.animation.StrokeTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import static jb.WelcomeApp.stage;
import javafx.scene.canvas.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;

public class WelcomeController implements Initializable {

    GraphicsContext gc;

    @FXML
    Button welcomeBtn;

    @FXML
    AnchorPane anchorDown;

    @FXML
    StackPane stackPaneMiddle;

    @FXML
    StackPane stackPane2;

    @FXML
    StackPane stackPaneDown;

    Stage primaryStage;
    WelcomeApp application;

    public void setApp(WelcomeApp application) {
        this.application = application;
    }

    @FXML
    public void handleButtonAction(ActionEvent event) throws IOException {
        if (event.getSource() == welcomeBtn) {
            WelcomeApp.getInstance().gotoLogin();
        }
    }

    @FXML
    public void fullscreen(ActionEvent event) {
        stage.setFullScreen(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        WelcomeLetters();
        WelcomeTittle();
        ScatterShapes();
    }

    private void WelcomeTittle() {
        stackPane2.getChildren().clear();

        final Text txt = new Text(10, 50, "Welcome In Visual Data");
        txt.setFont(Font.font("Verdana", 48));
        txt.setFill(Color.RED);
        stackPane2.getChildren().add(txt);

        StrokeTransition st = new StrokeTransition();
        st.setDuration(Duration.seconds(20));
        //st.setDelay(Duration.seconds(.5));
        st.setShape(txt);
        st.setFromValue(Color.BLUE);
        st.setToValue(Color.VIOLET);
        st.setCycleCount(Timeline.INDEFINITE);
        st.setAutoReverse(true);
        st.setRate(10);
        st.play();
    }

    private void ScatterShapes() {
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < 500; i++) {
            Circle circle = new Circle(random.nextInt(1920), random.nextInt(250), random.nextInt(25));
            circle.setFill(Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255), 0.5));
            anchorDown.getChildren().add(circle);
        }
    }

    private void WelcomeLetters() {
        stackPaneDown.getChildren().clear();

        final Text txt1 = new Text(10, 50, "Welcome");
        //final Text txt2 = new Text(10, 50,"Welcome");
        final Text txt3 = new Text(10, 50, "In     ");
        // final Text txt4 = new Text(10, 50,"Welcome");
        final Text txt5 = new Text(10, 50, "Visual ");
        // final Text txt6 = new Text(10, 50,"Welcome");
        final Text txt7 = new Text(10, 50, "Data   ");

        txt1.setFont(Font.font("Rockwell", 48));
        // txt2.setFont(Font.font ("Verdana", 48));
        txt3.setFont(Font.font("Verdana", 48));
        // txt4.setFont(Font.font ("Verdana", 48));
        txt5.setFont(Font.font("Rockwell", 48));
        // txt6.setFont(Font.font ("Verdana", 48));
        txt7.setFont(Font.font("Verdana", 48));

        txt1.setFill(Color.RED);
        // txt2.setFill(Color.AQUAMARINE);
        txt3.setFill(Color.BLUEVIOLET);
        // txt4.setFill(Color.CHARTREUSE);
        txt5.setFill(Color.CORAL);
        // txt6.setFill(Color.DARKGREEN);
        txt7.setFill(Color.GOLD);

        final Path path = new Path();
        path.getElements().add(new MoveTo(200, 0));
        path.getElements().add(new LineTo(880, 0));

        path.setOpacity(0.0);

        stackPaneDown.getChildren().add(path);
        stackPaneDown.getChildren().add(txt1);
        final PathTransition pathTransition = new PathTransition();

        pathTransition.setDuration(Duration.seconds(8.0));
        pathTransition.setDelay(Duration.seconds(.5));
        pathTransition.setRate(0.3);
        pathTransition.setPath(path);
        pathTransition.setNode(txt1);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(Timeline.INDEFINITE);
        pathTransition.setAutoReverse(true);
        pathTransition.play();

        final Path path3 = new Path();
        path3.getElements().add(new MoveTo(200, 40));
        path3.getElements().add(new LineTo(880, 40));

        path3.setOpacity(0.0);

        stackPaneDown.getChildren().add(path3);
        stackPaneDown.getChildren().add(txt3);
        final PathTransition pathTransition3 = new PathTransition();

        pathTransition3.setDuration(Duration.seconds(8.0));
        pathTransition3.setDelay(Duration.seconds(1));
        pathTransition3.setRate(0.3);
        pathTransition3.setPath(path3);
        pathTransition3.setNode(txt3);
        pathTransition3.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition3.setCycleCount(Timeline.INDEFINITE);
        pathTransition3.setAutoReverse(true);
        pathTransition3.play();

        final Path path5 = new Path();
        path5.getElements().add(new MoveTo(200, 80));
        path5.getElements().add(new LineTo(880, 80));

        path5.setOpacity(0.0);

        stackPaneDown.getChildren().add(path5);
        stackPaneDown.getChildren().add(txt5);
        final PathTransition pathTransition5 = new PathTransition();

        pathTransition5.setDuration(Duration.seconds(8.0));
        pathTransition5.setDelay(Duration.seconds(1.5));
        pathTransition5.setRate(0.3);
        pathTransition5.setPath(path5);
        pathTransition5.setNode(txt5);
        pathTransition5.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition5.setCycleCount(Timeline.INDEFINITE);
        pathTransition5.setAutoReverse(true);
        pathTransition5.play();
/**
 *
 * @author Jacek Byzdra
 * email: jacekbwwa@gmail.com
 */
        final Path path7 = new Path();
        path7.getElements().add(new MoveTo(200, 120));
        path7.getElements().add(new LineTo(880, 120));

        path7.setOpacity(0.0);

        stackPaneDown.getChildren().add(path7);
        stackPaneDown.getChildren().add(txt7);
        final PathTransition pathTransition7 = new PathTransition();

        pathTransition7.setDuration(Duration.seconds(8.0));
        pathTransition7.setDelay(Duration.seconds(2));
        pathTransition7.setRate(0.3);
        pathTransition7.setPath(path7);
        pathTransition7.setNode(txt7);
        pathTransition7.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition7.setCycleCount(Timeline.INDEFINITE);
        pathTransition7.setAutoReverse(true);
        pathTransition7.play();
    }
}



/**
 *
 * @author Jacek Byzdra
 * email: jacekbwwa@gmail.com
 */