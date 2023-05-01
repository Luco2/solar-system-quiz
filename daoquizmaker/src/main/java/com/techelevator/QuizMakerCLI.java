package com.techelevator;

import com.techelevator.application.QuizMaker;
import com.techelevator.dao.JdbcQuizQuestionDao;
import com.techelevator.dao.QuizQuestionDao;
import org.apache.commons.dbcp2.BasicDataSource;

import java.util.Scanner;

public class QuizMakerCLI {
    /*
     * TODO: Make sure to create the QuizMaker database and then
     *  run the quiz_db.sql file to build the correct tables
     */
    static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/QuizMaker";

    public static void main(String[] args) {

        QuizQuestionDao loader = null;

        String response = askInput();

            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setUrl(DATABASE_URL);
            dataSource.setUsername("postgres");
            dataSource.setPassword("postgres1");
            loader = new JdbcQuizQuestionDao(dataSource);
      if(response.equalsIgnoreCase("q")){
            loader = new JdbcQuizQuestionDao(dataSource);
        } else {

        }
        QuizMaker quizMaker = new QuizMaker(loader);
        quizMaker.run();
    }

    public static String askInput(){
        String ask = "Enter Q to load quiz questions.\n";
        System.out.println(ask);
        String response = new Scanner(System.in).nextLine();

        return response;
    }
}
