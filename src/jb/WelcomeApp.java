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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class WelcomeApp extends Application {

    public static Stage stage;
    private Stage registerStage;
    private Stage descriptionStage;
    private Stage quizStage;
    private Stage adminStage;
    private static WelcomeApp instance;

    public WelcomeApp() {
        instance = this;
    }

    public static WelcomeApp getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            stage = primaryStage;

            Image icon = new Image(getClass().getResourceAsStream("vdIcon.png"));
            stage.getIcons().add(icon);
            stage.setTitle("Visual data");
            stage.setMaximized(true);

            gotoWelcome();

            primaryStage.show();

        } catch (Exception ex) {
            Logger.getLogger(WelcomeApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void gotoWelcome() {
        try {
            replaceSceneContent("welcomejb.fxml");
            stage.setTitle("Visual data");
        } catch (Exception ex) {
            Logger.getLogger(WelcomeApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void gotoHome() {
        try {
            replaceSceneContent("home.fxml");
            stage.setTitle("Visual data - Sort (Bars)");
        } catch (Exception ex) {
            Logger.getLogger(WelcomeApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void gotoSort() {
        try {
            replaceSceneContent("sort.fxml");
            stage.setTitle("Visual data - Sort (Panels)");
        } catch (Exception ex) {
            Logger.getLogger(WelcomeApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void gotoMenu() {
        try {
            replaceSceneContent("menu.fxml");
            stage.setTitle("Visual data");
        } catch (Exception ex) {
            Logger.getLogger(WelcomeApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void gotoLogin() {
        try {
            replaceSceneContent("login.fxml");
            stage.setTitle("Visual data - Login");
        } catch (Exception ex) {
            Logger.getLogger(WelcomeApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void gotoTreeBST() {
        try {
            replaceSceneContent("TreeBSTView.fxml");
            stage.setTitle("Visual data - Trees");
        } catch (Exception ex) {
            Logger.getLogger(WelcomeApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void gotoGraph() {
        try {
            replaceSceneContent("graph.fxml");
            stage.setTitle("Visual data - Graphs");
        } catch (Exception ex) {
            Logger.getLogger(WelcomeApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void gotoRegister() {
        try {

            registerStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Parent page = (Parent) FXMLLoader.load(WelcomeApp.class.getResource("register.fxml"), null, new JavaFXBuilderFactory());
            Scene scene = new Scene(page);
            registerStage.setScene(scene);
            Image icon = new Image(getClass().getResourceAsStream("vdIcon.png"));
            registerStage.getIcons().add(icon);
            registerStage.setTitle("Visual data - Register");
            registerStage.initModality(Modality.WINDOW_MODAL);
            registerStage.setResizable(false);
            registerStage.initOwner(stage);

            registerStage.sizeToScene();
            registerStage.show();
        } catch (Exception ex) {
            Logger.getLogger(WelcomeApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void closeRegister() {
        if (registerStage != null) {
            registerStage.close();
        }
    }

    public void gotoDescription() {
        try {

            descriptionStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Parent page = (Parent) FXMLLoader.load(WelcomeApp.class.getResource("description.fxml"), null, new JavaFXBuilderFactory());
            Scene scene = new Scene(page);
            descriptionStage.setScene(scene);
            Image icon = new Image(getClass().getResourceAsStream("vdIcon.png"));
            descriptionStage.getIcons().add(icon);
            descriptionStage.setTitle("Visual data - Description");
            descriptionStage.initModality(Modality.WINDOW_MODAL);
            descriptionStage.initOwner(stage);
            descriptionStage.setResizable(false);
            descriptionStage.sizeToScene();
            descriptionStage.show();
        } catch (Exception ex) {
            Logger.getLogger(WelcomeApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void closeDescription() {
        if (descriptionStage != null) {
            descriptionStage.close();
        }
    }
/**
 *
 * @author Jacek Byzdra
 * email: jacekbwwa@gmail.com
 */
    public void gotoQuiz() {
        try {

            quizStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Parent page = (Parent) FXMLLoader.load(WelcomeApp.class.getResource("quiz.fxml"), null, new JavaFXBuilderFactory());
            Scene scene = new Scene(page);
            quizStage.setScene(scene);
            Image icon = new Image(getClass().getResourceAsStream("vdIcon.png"));
            quizStage.getIcons().add(icon);
            quizStage.setTitle("Visual data - Quiz");
            quizStage.initModality(Modality.WINDOW_MODAL);
            quizStage.initOwner(stage);
            quizStage.setResizable(false);
            quizStage.sizeToScene();
            quizStage.show();
        } catch (Exception ex) {
            Logger.getLogger(WelcomeApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void closeQuiz() {
        if (quizStage != null) {
            quizStage.close();
        }
    }

    public void gotoAdminPanel() {
        try {

            adminStage = new Stage();
            adminStage.setWidth(800);
            adminStage.setHeight(600);
            FXMLLoader loader = new FXMLLoader();
            Parent page = (Parent) FXMLLoader.load(WelcomeApp.class.getResource("admin.fxml"), null, new JavaFXBuilderFactory());
            Scene scene = new Scene(page);
            adminStage.setScene(scene);
            Image icon = new Image(getClass().getResourceAsStream("vdIcon.png"));
            adminStage.getIcons().add(icon);
            adminStage.setTitle("Visual data - Administration panel");
            adminStage.initModality(Modality.WINDOW_MODAL);
            adminStage.initOwner(stage);
            adminStage.setResizable(true);
            //adminStage.sizeToScene();
            adminStage.show();
        } catch (Exception ex) {
            Logger.getLogger(WelcomeApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Parent replaceSceneContent(String fxml) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        Parent page = (Parent) FXMLLoader.load(WelcomeApp.class.getResource(fxml), null, new JavaFXBuilderFactory());
        Scene scene = stage.getScene();
        if (scene == null) {
            scene = new Scene(page);
            stage.setScene(scene);
        } else {
            stage.getScene().setRoot(page);
        }
        //stage.sizeToScene();
        return page;
    }

    // display information /message
    public void showMessage(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}


/**
 *
 * @author Jacek Byzdra
 * email: jacekbwwa@gmail.com
 */