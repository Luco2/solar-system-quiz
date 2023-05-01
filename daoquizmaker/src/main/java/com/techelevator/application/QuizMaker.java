package com.techelevator.application;

import com.techelevator.dao.QuizQuestionDao;
import com.techelevator.model.QuizQuestion;

import java.util.List;
import java.util.Scanner;

public class QuizMaker {

    private QuizQuestionDao dao;
    private final Scanner userInput = new Scanner(System.in);

    /*
     * Input parameter to the constructor is the QuizQuestionDao interface and
     * NOT ApiQuizQuestionDao, jdbcQuizQuestionDao, or FileQuizQuestionDao.
     *
     * This input variable never has to change its type, leaving this code as a
     * whole unaffected by the kind of object loading the questions
     */
    public QuizMaker(QuizQuestionDao loader) {
        this.dao = loader;
    }

    public void run() {
        // Get quizzes
        List<String> quizzes = dao.getQuizzes();

        // Ask user to select a quiz
       String quizName = askQuiz(quizzes);

        // Get quiz questions
        List<QuizQuestion> quiz = dao.getQuestionsForQuiz(quizName);

        // Ask the quiz questions
        int numberCorrectResponses = deliverQuiz(quiz);

        System.out.println("You got " + numberCorrectResponses + " answer(s) correct out of the "
                + quiz.size() + " question(s) asked.");
    }

    private String askQuiz(List<String> quizzes){
        String quizName = null;

        for( int i = 0; i < quizzes.size(); i++){
            System.out.println(i + ". " + quizzes.get(i));
        }

        System.out.println("Please enter the quiz number you want to take: ");
        do {
            try {
                int quizNumber = Integer.parseInt(userInput.nextLine());

                if( quizNumber < 0 || quizNumber > quizzes.size()-1 ){
                    throw new NumberFormatException();
                }

                quizName = quizzes.get(quizNumber);

            }catch(NumberFormatException e){
                System.out.println("Please enter a valid number from 0 to " + (quizzes.size() - 1));
            }
        } while(quizName == null);

        System.out.println("Starting Solar System Quiz");

        return quizName;
    }

    private int deliverQuiz(List<QuizQuestion> quizQuestions) {
        int numberOfCorrectAnswers = 0;

        for (QuizQuestion quizQuestion : quizQuestions) {
            int answerNum = promptForInt(quizQuestion + "\nYour answer? ");
            int correctAnswer = quizQuestion.getCorrectAnswer();

            if (correctAnswer == answerNum) {
                System.out.println("Correct!");
                numberOfCorrectAnswers += 1;
            } else {
                System.out.println("Sorry that isn't correct!" + " The correct answer was " + correctAnswer);
            }
        }

        return numberOfCorrectAnswers;
    }

    private int promptForInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String reply = userInput.nextLine();
                return Integer.parseInt(reply);
            } catch (NumberFormatException e) {
                System.out.println("---Only numbers, please.---");
            }
        }
    }
}
