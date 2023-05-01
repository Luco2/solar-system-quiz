package com.techelevator.dao;

import com.techelevator.model.QuizQuestion;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcQuizQuestionDao implements QuizQuestionDao {

    JdbcTemplate jdbcTemplate;

    public JdbcQuizQuestionDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<String> getQuizzes() {
        List<String> quizzes = new ArrayList<>();

        String sql = "SELECT quiz_name FROM quiz";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while (results.next()) {
            quizzes.add(results.getString("quiz_name"));
        }

        return quizzes;
    }

    @Override
    public List<QuizQuestion> getQuestionsForQuiz(String quizName) {
        String sql = "SELECT question.question_id, question_text, correct_answer\n" +
                "FROM question\n" +
                "JOIN quiz_question qq ON qq.question_id = question.question_id\n" +
                "JOIN quiz ON quiz.quiz_id = qq.quiz_id\n" +
                "WHERE quiz_name = ?\n" +
                "ORDER BY question_number;";

        SqlRowSet questionRows = jdbcTemplate.queryForRowSet(sql, quizName);
        List<QuizQuestion> results = new ArrayList<>();
        while (questionRows.next()) {
            results.add(mapRowToQuizQuestion(questionRows));
        }
        return results;
    }

    private String[] getAnswersForQuestion(int questionId) {
        String sql = "SELECT answer_text\n" +
                "FROM answer\n" +
                "WHERE question_id = ?\n" +
                "ORDER BY answer_number;";

        SqlRowSet answerRows = jdbcTemplate.queryForRowSet(sql, questionId);
        List<String> answers = new ArrayList<>();
        while (answerRows.next()) {
            answers.add(answerRows.getString("answer_text"));
        }
        return answers.toArray(new String[0]);
    }

    private QuizQuestion mapRowToQuizQuestion(SqlRowSet rowSet) {
        QuizQuestion quizQuestion = new QuizQuestion();
        quizQuestion.setQuestion(rowSet.getString("question_text"));
        quizQuestion.setCorrectAnswer(rowSet.getInt("correct_answer"));
        quizQuestion.setAnswers(getAnswersForQuestion(rowSet.getInt("question_id")));
        return quizQuestion;
    }
}
