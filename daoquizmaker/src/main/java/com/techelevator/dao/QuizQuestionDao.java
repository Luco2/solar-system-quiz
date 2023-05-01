package com.techelevator.dao;

import com.techelevator.model.QuizQuestion;

import java.util.List;

public interface QuizQuestionDao {

    List<String> getQuizzes();

    List<QuizQuestion> getQuestionsForQuiz(String quizName);

}
