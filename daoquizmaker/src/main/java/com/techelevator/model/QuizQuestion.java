package com.techelevator.model;

public class QuizQuestion {

	private String question;
	private String[] answers;
	private int correctAnswer;

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    @Override
	public String toString() {
		StringBuilder result = new StringBuilder(question);

		for (int i = 0; i < answers.length; i++) {
			result.append("\n").append(i + 1).append(") ").append(answers[i]);
		}

		return result.toString();
	}
}
