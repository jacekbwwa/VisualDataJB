
package jb;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Jacek Byzdra
 * email: jacekbwwa@gmail.com
 */
public class AdminController implements Initializable {

    @FXML private TableView tableViewQst;
    @FXML private TableColumn questionIdCol;
    @FXML private TableColumn questionCol;
    @FXML private TableColumn nrCol;
    
    @FXML private TextField txtQuestion;
    @FXML private TextField txtAnswerA;
    @FXML private TextField txtAnswerB;
    @FXML private TextField txtAnswerC;
    @FXML private TextField txtAnswerD;
    
    @FXML private RadioButton rbA;
    @FXML private RadioButton rbB;
    @FXML private RadioButton rbC;
    @FXML private RadioButton rbD;
    
    @FXML private TableView tableViewUser;
    @FXML private TableColumn colUserNo;
    @FXML private TableColumn colUsername;
    @FXML private TableColumn colEmail;
    @FXML private TableColumn colAdmin;
        
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
               
        final ToggleGroup group = new ToggleGroup();  // create radio buttons group
        rbA.setToggleGroup(group); // only one radioButton from group can be selected
        rbB.setToggleGroup(group);
        rbC.setToggleGroup(group);
        rbD.setToggleGroup(group);
        
        buildTableView(tableViewQst); // build table for quiz questions
        buildUserTableView(tableViewUser); // build table for users
    }    
          
    private  void buildTableView(TableView tableView){  
         
        // here define how to fill data for each cell; we get value from property of quizQuestion
        questionIdCol.setCellValueFactory(new PropertyValueFactory<>("Id"));
        questionCol.setCellValueFactory(new PropertyValueFactory<>("question"));         

        // set sort type here:
        questionIdCol.setSortType(TableColumn.SortType.ASCENDING);
        nrCol.setSortable(false);

        nrCol.setCellFactory( new Callback<TableColumn, TableCell>(){
            @Override public TableCell call( TableColumn tc ){
                return new TableCell(){
                    @Override public void updateItem( Object ob, boolean empty ){
                        super.updateItem( ob, empty );
                        setGraphic( null );
                        if(empty){
                            setText(null);
                        }else{
                            setText(getIndex() + 1 + "");
                        }                       
                    }
                };
            }
        });
      
        // Display row data
        ObservableList<QuizQuestion> list = getQuestionList();
        tableView.setItems(list);
      
        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                //Check whether item is selected and set value of selected item to Label
                if(tableView.getSelectionModel().getSelectedItem() != null) {    
                   TableViewSelectionModel selectionModel = tableView.getSelectionModel();
                   QuizQuestion q = (QuizQuestion)selectionModel.getSelectedItem();
                   onQuestionSelect(q);
                }
            }
        });
    }
    
    private void onQuestionSelect(QuizQuestion q){
        txtQuestion.setText(q.getQuestion());
        DBConnection.getQuestionAnswers(q);
        String correctAnswer = q.getCorrectAnswer();
        
        ArrayList<String> answers = q.getAnswers();
        TextField[] answersTxt = {txtAnswerA, txtAnswerB, txtAnswerC, txtAnswerD};
        RadioButton[] answersRb = {rbA, rbB, rbC, rbD};
        for(int i = 0; i < answers.size(); i++){
            
            answersTxt[i].setText(answers.get(i));
            if(answers.get(i).equals(correctAnswer)){
                answersRb[i].setSelected(true);
            }
        }
    }
    
    // Method returns quiz questins list which is a source for table view
    private ObservableList<QuizQuestion> getQuestionList(){
        ObservableList<QuizQuestion> list = FXCollections.observableArrayList(); 
        ArrayList<QuizQuestion> tmp = DBConnection.getQuizQuestions();
        for(int i = 0; i < tmp.size(); i++){
            QuizQuestion q = tmp.get(i);
            list.add(q);
        }
        return list;
    }
	
	/**
 * FXML Controller class
 *
 * @author Jacek Byzdra
 * email: jacekbwwa@gmail.com
 */
            
    // Method cleans all fields described single quiz question
    private void clearAnswerFields(){
        txtQuestion.setText("");
        txtAnswerA.setText("");
        txtAnswerB.setText("");
        txtAnswerC.setText("");
        txtAnswerD.setText("");
        rbA.setSelected(false);
        rbB.setSelected(false);
        rbC.setSelected(false);
        rbD.setSelected(false);
    }
    
    // Method holds action: click button delete quiz question
    @FXML public void handleDeleteQuestion() {
        int selectedIndex = tableViewQst.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            // confirmation delete:
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("VisualDataJB");
            alert.setHeaderText("Do you want delete selected question?");
            alert.setContentText("");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){                
                TableViewSelectionModel selectionModel = tableViewQst.getSelectionModel();
                QuizQuestion q = (QuizQuestion)selectionModel.getSelectedItem();
                if(DBConnection.deleteQuizQuestion(q)){
                    refreshQuizQuestionTable();                
                    WelcomeApp.getInstance().showMessage("VisualDataJB", "Question is deleted","");                
                }
            }            
        } else {
            // Nothing selected.
            WelcomeApp.getInstance().showMessage("VisualDataJB", "No question selected", "Please select the question to remove");            
        }
    }
    
    // Method holds action: click button which saves changed quiz question
    @FXML public void handleUpdateQuestion() {
        if(!validationSelectedQuestionFields()){
            return;
        }
        QuizQuestion newQuestion = buildQuizQuestion();
        TableViewSelectionModel selectionModel = tableViewQst.getSelectionModel();
        QuizQuestion oryginalQuestion = (QuizQuestion)selectionModel.getSelectedItem();
        if(DBConnection.updateQuizQuestion(newQuestion, oryginalQuestion)){
            refreshQuizQuestionTable();
            WelcomeApp.getInstance().showMessage("VisualDataJB", "Question is updated","");
        }        
    }
    
    private boolean validationSelectedQuestionFields(){
        if(txtQuestion.getText().trim().length() ==0){
            WelcomeApp.getInstance().showMessage("VisualDataJB", "Question is not entered", "Please enter the quiz question ");
            return false;
        }
        if(txtAnswerA.getText().trim().length() ==0 || 
                txtAnswerB.getText().trim().length() ==0 || 
                txtAnswerC.getText().trim().length() ==0 || 
                txtAnswerD.getText().trim().length() ==0 ){
            WelcomeApp.getInstance().showMessage("VisualDataJB", "Answers incompleted", "Please enter all required answers ");
            return false;
        }
        if(!rbA.isSelected() && !rbB.isSelected() && !rbC.isSelected() && !rbD.isSelected()){
            WelcomeApp.getInstance().showMessage("VisualDataJB", "Answers incompleted", "Please select which answer is correct");
            return false;
        }
        return true;
    }
    
    private QuizQuestion buildQuizQuestion(){
        QuizQuestion q = new QuizQuestion();
        q.setQuestion(txtQuestion.getText());
        q.addAnswer(txtAnswerA.getText());
        q.addAnswer(txtAnswerB.getText());
        q.addAnswer(txtAnswerC.getText());
        q.addAnswer(txtAnswerD.getText());
        if(rbA.isSelected()){
            q.setCorrectAnswer(txtAnswerA.getText());
        }else {
            if(rbB.isSelected()){
                q.setCorrectAnswer(txtAnswerB.getText());
            }else {
                if(rbC.isSelected()){
                    q.setCorrectAnswer(txtAnswerC.getText());
                } else {
                    if(rbD.isSelected()){
                        q.setCorrectAnswer(txtAnswerD.getText());
                    }
                }
            }
        }
        return q;
    }
    
    private void refreshQuizQuestionTable(){
        // refresh table:   
        clearAnswerFields();
        ObservableList<QuizQuestion> list = getQuestionList();
        tableViewQst.setItems(list);        
    }
    
    @FXML public void handleInsertQuestion() {
        // validation:
        if(!validationSelectedQuestionFields()){
            return;
        }
        
        // buid new question:
        QuizQuestion q = buildQuizQuestion();
        if(DBConnection.addNewQuizQuestion(q)){
            refreshQuizQuestionTable();
            WelcomeApp.getInstance().showMessage("VisualDataJB", "Question is added","");
        }
    }
    
    // method to build table viwe for users:
    public  void buildUserTableView(TableView tableView){  
         
        // here define how to fill data for each cell; we get value from property of quizQuestion
        colUsername.setCellValueFactory(new PropertyValueFactory<>("userName"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colAdmin.setCellValueFactory(new PropertyValueFactory<>("admin"));

        // set sort type here:
        colUsername.setSortType(TableColumn.SortType.ASCENDING);
        colUserNo.setSortable(false);
        
        tableViewUser.setEditable(true);

        colUserNo.setCellFactory( new Callback<TableColumn, TableCell>(){
            @Override public TableCell call( TableColumn tc ){
                return new TableCell(){
                    @Override public void updateItem( Object ob, boolean empty ){
                        super.updateItem( ob, empty );
                        setGraphic( null );
                        if(empty){
                            setText(null);
                        }else{
                            setText(getIndex() + 1 + "");
                        }                       
                    }
                };
            }
        });                        
        
        // Display row data
        ObservableList<User> list = getUsersList();
        tableView.setItems(list);      
    }
    
    private ObservableList<User> getUsersList(){
        ObservableList<User> list = FXCollections.observableArrayList(); 
        ArrayList<User> tmp = DBConnection.getUsers();
        for(int i = 0; i < tmp.size(); i++){
            User q = tmp.get(i);
            list.add(q);
        }
        return list;
    }
    
    @FXML public void handleDeleteUser() {
        int selectedIndex = tableViewUser.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            TableViewSelectionModel selectionModel = tableViewUser.getSelectionModel();
            User user = (User)selectionModel.getSelectedItem();
            if(user.getAdmin()){
                WelcomeApp.getInstance().showMessage("VisualDataJB", "No possible delete user with Admin role",""); 
                return;
            }
            // cnfirmation delete:
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("VisualDataJB");
            alert.setHeaderText("Do you want delete selected user?");
            alert.setContentText("");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){                                
                if(DBConnection.deleteUser(user)){
                    refreshUserTable();                
                    WelcomeApp.getInstance().showMessage("VisualDataJB", "User is deleted","");                
                }
            }            
        } else {
            // Nothing selected.
            WelcomeApp.getInstance().showMessage("VisualDataJB", "No user selected", "Please select the user to remove");            
        }
    }
    
    private void refreshUserTable(){
        // refresh table:           
        ObservableList<User> list = getUsersList();
        tableViewUser.setItems(list);        
    }
    
    @FXML public void handleUpdateUser() {      
        int selectedIndex = tableViewUser.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            TableViewSelectionModel selectionModel = tableViewUser.getSelectionModel();
            User user = (User)selectionModel.getSelectedItem();
            if(DBConnection.updateUserRoleAdmin(user)){
                refreshUserTable();
                WelcomeApp.getInstance().showMessage("VisualDataJB", "User is updated","");
            }   
        }else {
            // Nothing selected.
            WelcomeApp.getInstance().showMessage("VisualDataJB", "No user selected", "Please select the user to change role Admin");            
        }
    }
}

/**
 * FXML Controller class
 *
 * @author Jacek Byzdra
 * email: jacekbwwa@gmail.com
 */