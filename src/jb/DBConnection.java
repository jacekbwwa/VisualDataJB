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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.*;
import java.util.ArrayList;

public class DBConnection {

    private static Connection conn;
    private static String user = "*****";//Username of database  
    private static String db_pass = "*****";//Password of database  

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/visualDataJB";

    public static Connection connect() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException cnfe) {
            System.err.println("Error: " + cnfe.getMessage());
        }

        conn = DriverManager.getConnection(DB_URL, user, db_pass);
        return conn;
    }

    private static Connection getConnection() throws SQLException, ClassNotFoundException {
        if (conn != null && !conn.isClosed()) {
            return conn;
        }
        connect();
        return conn;
    }

    public static boolean validateUser(String user, String password) {
        try {
            String sql = "SELECT username, password from user where username like '" + user + "'";
            String pass = "";
            ResultSet rs = getConnection().createStatement().executeQuery(sql);
            while (rs.next()) {
                pass = rs.getString("password");
            }
            rs.close();
            if (password.equals(pass)) {
                return true;
            }
        } catch (Exception e) {
            WelcomeApp.getInstance().showMessage("Error", "", e.getMessage());
            return false;
        }
        return false;
    }

    public static boolean registerUser(User user) {
        try {
            String sql = createInsertUserStatement(user);
            int res = getConnection().createStatement().executeUpdate(sql);
        } catch (SQLException e) {
            WelcomeApp.getInstance().showMessage("SQL Error", "", e.getMessage());
            return false;
        } catch (Exception e) {
            WelcomeApp.getInstance().showMessage("Error", "", e.getMessage());
            return false;
        }
        return true;
    }

    private static String createInsertUserStatement(User user) {
        String sql = "Insert into user (Username , Password , Email) values ('"
                + user.getUserName() + "', '" + user.getPassword() + "', '" + user.getEmail() + "')";
        return sql;
    }

    public static ArrayList<User> getUsers() { // return users from database
        try {
            String sql = "SELECT u.userId, username, email, a.userId as admin "
                    + "from user u left join admin a on u.UserId = a.UserId";
            ResultSet rs = getConnection().createStatement().executeQuery(sql);
            ArrayList<User> list = new ArrayList();
            while (rs.next()) {
                User q = new User(rs.getString("username"));
                q.setEmail(rs.getString("email"));
                q.setUserId(rs.getInt("userId"));
                if (rs.getInt("admin") == 0) {
                    q.setAdmin(false);
                } else {
                    q.setAdmin(true);
                }
                list.add(q);
            }
            rs.close();
            return list;
        } catch (Exception e) {
            WelcomeApp.getInstance().showMessage("Error", "", e.getMessage());
            return null;
        }
    }

    public static boolean deleteUser(User user) {
        try {
            String sql = "delete from user Where userId = " + Integer.toString(user.getUserId());
            int res = getConnection().createStatement().executeUpdate(sql);

            return true;
        } catch (Exception e) {
            WelcomeApp.getInstance().showMessage("Error", "", e.getMessage());
            return false;
        }
    }

    // change role admin for user:
    public static boolean updateUserRoleAdmin(User user) {
        try {
            String sql;
            int rs;
            if (user.getAdmin()) {
                // delete from table Admin userId
                sql = "delete from admin Where userId = " + Integer.toString(user.getUserId());
                rs = getConnection().createStatement().executeUpdate(sql);
            } else {
                // add to table admin userId
                sql = "insert into admin(userId)  values  (" + Integer.toString(user.getUserId()) + ")";
                rs = getConnection().createStatement().executeUpdate(sql);
            }

            return true;
        } catch (Exception e) {
            WelcomeApp.getInstance().showMessage("Error", "", e.getMessage());
            return false;
        }
    }

    public static boolean isAdmin(String user) {
        try {
            String sql = "SELECT u.userId, username, email, a.userId as admin "
                    + "from user u left join admin a on u.UserId = a.UserId where username like '" + user + "'";
            boolean admin = false;
            ResultSet rs = getConnection().createStatement().executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("admin"); // if admin is null it returns 0 value
                if (id > 0) {
                    admin = true;
                }
            }
            rs.close();
            return admin;
        } catch (Exception e) {
            WelcomeApp.getInstance().showMessage("Error", "", e.getMessage());
            return false;
        }
    }

    public static ArrayList<QuizQuestion> getQuizQuestions() { // return all quiz questions from database, read only questions
        try {
            String sql = "SELECT Id, question from quizQuestion";
            ResultSet rs = getConnection().createStatement().executeQuery(sql);
            ArrayList<QuizQuestion> questions = new ArrayList();
            while (rs.next()) {
                QuizQuestion q = new QuizQuestion();
                q.setQuestion(rs.getString("question"));
                q.setId(rs.getInt("Id"));
                questions.add(q);
            }
            rs.close();
            return questions;
        } catch (Exception e) {
            WelcomeApp.getInstance().showMessage("Error", "", e.getMessage());
            return null;
        }
    }

    public static void getQuestionAnswers(QuizQuestion question) {
        try {
            String sql = "SELECT aq.answer, aq.correct "
                    + "FROM quizquestion qq join quizAnswer aq "
                    + "on qq.Id = aq.Q_Id  "
                    + "Where qq.question = '" + question.getQuestion() + "'";
            ResultSet rs = getConnection().createStatement().executeQuery(sql);

            while (rs.next()) {
                question.addAnswer(rs.getString("answer"));
                boolean correct = rs.getBoolean("correct");
                if (correct) {
                    question.setCorrectAnswer(rs.getString("answer"));
                }
            }
            rs.close();
        } catch (Exception e) {
            WelcomeApp.getInstance().showMessage("Error", "", e.getMessage());
        }
    }

    public static boolean addNewQuizQuestion(QuizQuestion question) {
        try {
            // insert question
            String sql = "insert into quizQuestion (question_nr , question)  "
                    + "values (0, '" + question.getQuestion() + "'); ";
            int res = getConnection().createStatement().executeUpdate(sql);

            // check id, which is autoincrement
            int qId = 0;
            sql = "SELECT id from quizquestion Where question = '" + question.getQuestion() + "'";
            ResultSet rs = getConnection().createStatement().executeQuery(sql);

            while (rs.next()) {
                qId = rs.getInt("id");
            }
            rs.close();

            // insert answers:
            ArrayList<String> list = question.getAnswers();
            for (int i = 0; i < list.size(); i++) {
                boolean correct = false;
                if (question.getCorrectAnswer().equals(list.get(i))) {
                    correct = true;
                }
                sql = "insert into quizAnswer(Q_Id, answer, answer_nr, correct)  values "
                        + " (" + Integer.toString(qId) + ", '" + list.get(i) + "', "
                        + Integer.toString(i) + ", " + correct + ")";
                int r = getConnection().createStatement().executeUpdate(sql);
            }
            return true;
        } catch (Exception e) {
            WelcomeApp.getInstance().showMessage("Error", "", e.getMessage());
            return false;
        }
    }

    public static boolean deleteQuizQuestion(QuizQuestion question) {
        try {
            String sql = "delete from quizanswer Where q_id = " + Integer.toString(question.getId());
            int res = getConnection().createStatement().executeUpdate(sql);
            sql = "delete from quizQuestion Where id = " + Integer.toString(question.getId());
            res = getConnection().createStatement().executeUpdate(sql);
            return true;
        } catch (Exception e) {
            WelcomeApp.getInstance().showMessage("Error", "", e.getMessage());
            return false;
        }
    }
	
	/**
 *
 * @author Jacek Byzdra
 * email: jacekbwwa@gmail.com
 */

    public static boolean updateQuizQuestion(QuizQuestion newQuestion, QuizQuestion oryginalQuestion) {
        try {
            String sql = "delete from quizanswer Where q_id = " + Integer.toString(oryginalQuestion.getId());
            int res = getConnection().createStatement().executeUpdate(sql);

            sql = "update quizQuestion set question = '"
                    + newQuestion.getQuestion() + "' Where id = " + Integer.toString(oryginalQuestion.getId());
            res = getConnection().createStatement().executeUpdate(sql);

            // insert new answers:
            ArrayList<String> list = newQuestion.getAnswers();
            for (int i = 0; i < list.size(); i++) {
                boolean correct = false;
                if (newQuestion.getCorrectAnswer().equals(list.get(i))) {
                    correct = true;
                }
                sql = "insert into quizAnswer(Q_Id, answer, answer_nr, correct)  values "
                        + " (" + Integer.toString(oryginalQuestion.getId()) + ", '" + list.get(i) + "', "
                        + Integer.toString(i) + ", " + correct + ")";
                int r = getConnection().createStatement().executeUpdate(sql);
            }
            return true;
        } catch (Exception e) {
            WelcomeApp.getInstance().showMessage("Error", "", e.getMessage());
            return false;
        }
    }
}
