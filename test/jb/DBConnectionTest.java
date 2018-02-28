/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jb;

import java.sql.Connection;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Admin
 */
public class DBConnectionTest {
    
    public DBConnectionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        System.out.println("* UtilsJUnit4_DBConnection_Test: setUp() method");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of connect method, of class DBConnection.
     */
    @Test
    public void testConnect() throws Exception {
        System.out.println("connect");        
        Connection result = DBConnection.connect();
        assertNotNull(result);
        System.out.println("Connection is builded correctly"); 
    }

    /**
     * Test of validateUser method, of class DBConnection.
     */
    @Test
    public void testValidateUser() {
        System.out.println("validateUser");
        String user = "_nobody";
        String password = "_nopassword";
        boolean expResult = false;
        boolean result = DBConnection.validateUser(user, password);
        assertEquals(expResult, result);       
    }      

    /**
     * Test of getUsers method, of class DBConnection.
     */
    @Test
    public void testGetUsers() {
        System.out.println("getUsers");
        boolean expResult = false;
        ArrayList<User> result = DBConnection.getUsers();        
        assertEquals(expResult, result.isEmpty());        
    }
    
    /**
     * Test of getQuizQuestions method, of class DBConnection.
     */
    @Test
    public void testGetQuizQuestions() {
        System.out.println("getQuizQuestions");
        boolean expResult = false;
        ArrayList<QuizQuestion> result = DBConnection.getQuizQuestions();        
        assertEquals(expResult, result.isEmpty());        
    }

    /**
     * Test of getQuestionAnswers method, of class DBConnection.
     */
    @Test
    public void testGetQuestionAnswers() {
        System.out.println("getQuestionAnswers");
        QuizQuestion question = new QuizQuestion();        
        question.setQuestion("Stack is also called as");
        DBConnection.getQuestionAnswers(question);
        boolean expResult = true;
        assertEquals(expResult, question.getAnswers().size() == 4); 
        
        // abstarct question - should not find:
        question = new QuizQuestion();
        question.setQuestion("abc abc");
        DBConnection.getQuestionAnswers(question);
        expResult = true;
        assertEquals(expResult, question.getAnswers().isEmpty());
    }    
    
}
