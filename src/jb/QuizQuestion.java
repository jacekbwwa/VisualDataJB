/**
 *
 * @author Jacek Byzdra
 * email: jacekbwwa@gmail.com
 */
package jb;

import java.util.ArrayList;

/**
 *
 * @author Jacek Byzdra
 * email: jacekbwwa@gmail.com
 */
public class QuizQuestion {

    private String question;
    private ArrayList<String> answers;
    private String correctAnswer;
    private int Id;

    public QuizQuestion() {
        answers = new ArrayList();
    }

    public void setId(int id) {
        this.Id = id;
    }

    public int getId() {
        return this.Id;
    }

    public void setQuestion(String q) {
        this.question = q;
    }

    public String getQuestion() {
        return this.question;
    }

    public void setCorrectAnswer(String s) {
        this.correctAnswer = s;
    }

    public String getCorrectAnswer() {
        return this.correctAnswer;
    }

    public void addAnswer(String s) {
        answers.add(s);
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }
}
/**
 *
 * @author Jacek Byzdra
 * email: jacekbwwa@gmail.com
 */