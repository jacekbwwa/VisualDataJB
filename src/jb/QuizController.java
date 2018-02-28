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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author Jacek Byzdra
 * email: jacekbwwa@gmail.com
 */
public class QuizController implements Initializable {

    @FXML
    private AnchorPane anchorPaneWelcome;
    @FXML
    private Label labelWelcome1;
    @FXML
    private Label labelWelcome2;
    @FXML
    private Label labelWelcome3;
    @FXML
    private AnchorPane anchorPaneQuestion;
    @FXML
    private Label labelQuestion;
    @FXML
    private RadioButton rbA;
    @FXML
    private RadioButton rbB;
    @FXML
    private RadioButton rbC;
    @FXML
    private RadioButton rbD;
    @FXML
    private Label labelValidateA;
    @FXML
    private Label labelValidateB;
    @FXML
    private Label labelValidateC;
    @FXML
    private Label labelValidateD;
    @FXML
    private AnchorPane anchorPaneSummary;
    @FXML
    private Label labelSummary;

    ArrayList<QuizQuestion> allQuizQuestions = null; // created when start play
    ArrayList<QuizQuestion> currentListQuestions = null; // created by random from allQuizQuestions
    ArrayList<QuizQuestion> userAnswers = new ArrayList(); // created here

    int currentQuestion = 0; // number of current quiz question
    boolean review = false; // means review mode

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        anchorPaneWelcome.setVisible(true);
        anchorPaneQuestion.setVisible(false);
        anchorPaneSummary.setVisible(false);

        labelWelcome1.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
        labelWelcome2.setFont(Font.font("Verdana", FontWeight.BOLD, 22));
        labelWelcome3.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
        labelSummary.setFont(Font.font("Verdana", FontWeight.BOLD, 32));

        final ToggleGroup group = new ToggleGroup();
        rbA.setToggleGroup(group); // only one from group can be selected
        rbB.setToggleGroup(group);
        rbC.setToggleGroup(group);
        rbD.setToggleGroup(group);

        labelValidateA.setVisible(false);
        labelValidateB.setVisible(false);
        labelValidateC.setVisible(false);
        labelValidateD.setVisible(false);
    }

    @FXML
    protected void btnStartClick() {
        playQuiz();
    }

    @FXML
    protected void btnCloseClick() {
        WelcomeApp.getInstance().closeQuiz();
    }

    @FXML
    protected void btnNextClick() {
        nextQuestion();
    }

    @FXML
    protected void btnAgainClick() {
        // start new game
        anchorPaneWelcome.setVisible(true);
        anchorPaneQuestion.setVisible(false);
        anchorPaneSummary.setVisible(false);
    }

    @FXML
    protected void btnReviewClick() {
        // hide summary
        anchorPaneSummary.setVisible(false);
        // show question:
        anchorPaneQuestion.setVisible(true);

        currentQuestion = 0;
        review = true;
        reviewQuizQuestion();
    }

    private void playQuiz() {
        // init quiz questions list:
        if (allQuizQuestions == null) {
            allQuizQuestions = DBConnection.getQuizQuestions(); // read from database
        }
        if (allQuizQuestions.isEmpty())//.size() == 0)
        {   // check if list is not empty
            WelcomeApp.getInstance().showMessage("Quiz", "No questions loaded", "There are no questions loaded in this quiz");
            return;
        }
        // random 5 questions to current quiz:
        currentListQuestions = randomQuizQuestions(5);
        if (currentListQuestions.isEmpty())//.size() == 0)
        {
            WelcomeApp.getInstance().showMessage("Quiz", "No questions loaded", "There are no questions loaded in this quiz");
            return;
        }

        // hide Welcome
        anchorPaneWelcome.setVisible(false);
        // show question:
        anchorPaneQuestion.setVisible(true);

        currentQuestion = 0;
        userAnswers.clear();

        showQuizQuestion();
    }

    private void showQuizQuestion() {
        String questionNr = Integer.toString(currentQuestion + 1);
        QuizQuestion q = currentListQuestions.get(currentQuestion);
        labelQuestion.setText(questionNr + ". " + q.getQuestion());
        if (q.getAnswers().isEmpty()) {
            DBConnection.getQuestionAnswers(q);
        }
        if (q.getAnswers().size() == 4) {
            int[] sequency = randomAnswers(); // each game different sequency of answer
            rbA.setText(q.getAnswers().get(sequency[0] - 1));
            rbB.setText(q.getAnswers().get(sequency[1] - 1));
            rbC.setText(q.getAnswers().get(sequency[2] - 1));
            rbD.setText(q.getAnswers().get(sequency[3] - 1));
        } else {
            WelcomeApp.getInstance().showMessage("Quiz", "Load quiz answers from database error", "Expected 4 answers not found");
            WelcomeApp.getInstance().closeQuiz();
        }
    }

    private int[] randomAnswers() { // no constat sequecy of answers, random sequency here	
        int[] numbers = new int[4]; // array for generate numbers
        int s = 0; // iterator for loop while

        while (s < 4) { // in loop random a number, range <1;4>
            int n = (int) (Math.random() * 4 + 1); // random a number

            boolean exist = false; // for check if number was already randomed
            for (int i = 0; i < 4; i++) {
                // chect in table secret if randomed number exist
                if (numbers[i] == n) {
                    exist = true;// number exist!
                }
            }
            if (!exist) { // if not exist in array secret, it is a new number , then add to secret lottery numbers:
                numbers[s] = n;
                // and increase iterator:
                s++;
            }
        }
        return numbers;
    }

    private void reviewQuizQuestion() {
        String questionNr = Integer.toString(currentQuestion + 1);
        QuizQuestion q = userAnswers.get(currentQuestion);
        labelQuestion.setText(questionNr + ". " + q.getQuestion());

        rbA.setText(q.getAnswers().get(0));
        rbB.setText(q.getAnswers().get(1));
        rbC.setText(q.getAnswers().get(2));
        rbD.setText(q.getAnswers().get(3));

        String correctAnswer = currentListQuestions.get(currentQuestion).getCorrectAnswer();
        String userAnswer = userAnswers.get(currentQuestion).getCorrectAnswer();
        // check if coorect answer and mark by label and color:
        if (rbA.getText().equals(userAnswer)) {
            rbA.setSelected(true);
            if (rbA.getText().equals(correctAnswer)) {
                showGreenLabel(labelValidateA);
            } else {
                showRedLabel(labelValidateA);
            }
        }
        if (rbB.getText().equals(userAnswer)) {
            rbB.setSelected(true);
            if (rbB.getText().equals(correctAnswer)) {
                showGreenLabel(labelValidateB);
            } else {
                showRedLabel(labelValidateB);
            }
        }
        if (rbC.getText().equals(userAnswer)) {
            rbC.setSelected(true);
            if (rbC.getText().equals(correctAnswer)) {
                showGreenLabel(labelValidateC);
            } else {
                showRedLabel(labelValidateC);
            }
        }
        if (rbD.getText().equals(userAnswer)) {
            rbD.setSelected(true);
            if (rbD.getText().equals(correctAnswer)) {
                showGreenLabel(labelValidateD);
            } else {
                showRedLabel(labelValidateD);
            }
        }

        if (rbA.getText().equals(correctAnswer)) {
            showGreenLabel(labelValidateA);
        }
        if (rbB.getText().equals(correctAnswer)) {
            showGreenLabel(labelValidateB);
        }
        if (rbC.getText().equals(correctAnswer)) {
            showGreenLabel(labelValidateC);
        }
        if (rbD.getText().equals(correctAnswer)) {
            showGreenLabel(labelValidateD);
        }
    }

    private void showGreenLabel(Label label) {
        label.setText("V");
        label.setStyle(" -fx-text-fill: green");
        label.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
        label.setVisible(true);
    }

    private void showRedLabel(Label label) {
        label.setText("X");
        label.setStyle(" -fx-text-fill: red");
        label.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
        label.setVisible(true);
    }

    private void nextReviewQuestion() {
        // next question:
        cleanSelection();
        currentQuestion++;
        if (currentQuestion < currentListQuestions.size()) {
            reviewQuizQuestion();
        } else {
            String info = summaryQuiz();
            //WelcomeApp.getInstance().showMessage("Quiz", info, info);
            anchorPaneQuestion.setVisible(false);
            anchorPaneSummary.setVisible(true);
            labelSummary.setText(info);
            review = false;
        }
    }

    private void nextQuestion() {
        if (review) {
            nextReviewQuestion();
        } else {
            // check if answer is selected:
            if (!rbA.isSelected() && !rbB.isSelected() && !rbC.isSelected() && !rbD.isSelected()) {
                WelcomeApp.getInstance().showMessage("Quiz", "No answer selected", "Please select your answer");
                return;
            }
            // save answer before next
            addUserAnswerToList();

            // new question:
            cleanSelection();
            currentQuestion++;
            if (currentQuestion < currentListQuestions.size()) {
                showQuizQuestion();
            } else {
                String info = summaryQuiz();
                //WelcomeApp.getInstance().showMessage("Quiz", info, info);
                anchorPaneQuestion.setVisible(false);
                anchorPaneSummary.setVisible(true);
                labelSummary.setText(info);
            }
        }
    }
/**
 *
 * @author Jacek Byzdra
 * email: jacekbwwa@gmail.com
 */
    private String summaryQuiz() {
        int c = currentListQuestions.size();
        int corrected = 0;
        for (int i = 0; i < c; i++) {
            String correctAnswer = currentListQuestions.get(i).getCorrectAnswer();
            String userAnswer = userAnswers.get(i).getCorrectAnswer();
            if (userAnswer.equals(correctAnswer)) {
                corrected++;
            }
        }
        String res = "Result: " + Integer.toString(corrected) + " / " + Integer.toString(c);
        return res;
    }

    private void addUserAnswerToList() {
        QuizQuestion q = new QuizQuestion();
        q.setQuestion(currentListQuestions.get(currentQuestion).getQuestion());
        if (rbA.isSelected()) {
            q.setCorrectAnswer(rbA.getText());
        } else if (rbB.isSelected()) {
            q.setCorrectAnswer(rbB.getText());
        } else if (rbC.isSelected()) {
            q.setCorrectAnswer(rbC.getText());
        } else if (rbD.isSelected()) {
            q.setCorrectAnswer(rbD.getText());
        }
        q.addAnswer(rbA.getText());
        q.addAnswer(rbB.getText());
        q.addAnswer(rbC.getText());
        q.addAnswer(rbD.getText());
        userAnswers.add(q);
    }

    private void cleanSelection() { // cleans selected answers
        rbA.setSelected(false);
        rbB.setSelected(false);
        rbC.setSelected(false);
        rbD.setSelected(false);

        labelValidateA.setVisible(false);
        labelValidateB.setVisible(false);
        labelValidateC.setVisible(false);
        labelValidateD.setVisible(false);
    }

    private ArrayList<QuizQuestion> randomQuizQuestions(int numberQuestions) {
        if (allQuizQuestions.size() <= numberQuestions) {
            return allQuizQuestions;
        }
        ArrayList<QuizQuestion> randomQuestions = new ArrayList();
        int i = 0;
        int r;
        while (i < numberQuestions) {
            r = (int) (1 + Math.random() * numberQuestions); // random number             
            boolean b = true;
            for (int j = 0; j < randomQuestions.size(); j++) { // check if rarndomed number is unique
                if (randomQuestions.get(j).getQuestion().equals(allQuizQuestions.get(r).getQuestion())) {
                    b = false;
                    break;
                }
            }
            if (b) {
                randomQuestions.add(allQuizQuestions.get(r));
                i++;
            }
        }
        return randomQuestions;
    }
}
