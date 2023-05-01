START TRANSACTION;

DROP TABLE IF EXISTS quiz, question, quiz_question, answer CASCADE;

CREATE TABLE quiz (
    quiz_id serial,
    quiz_name varchar(20) NOT NULL,
    CONSTRAINT PK_quiz PRIMARY KEY(quiz_id)
);

CREATE TABLE question (
    question_id serial,
    question_text varchar(100) NOT NULL,
    correct_answer int NOT NULL,
    CONSTRAINT PK_question PRIMARY KEY(question_id)
);

CREATE TABLE quiz_question (
    quiz_id int,
    question_id int,
    question_number int NOT NULL,
    CONSTRAINT PK_quiz_question PRIMARY KEY(quiz_id, question_id),
	CONSTRAINT FK_quiz_id FOREIGN KEY(quiz_id) REFERENCES quiz(quiz_id),
	CONSTRAINT FK_question_id FOREIGN KEY(question_id) REFERENCES question(question_id)
);

CREATE TABLE answer (
    question_id int,
    answer_number int,
    answer_text varchar(50) NOT NULL,
    CONSTRAINT PK_answer PRIMARY KEY(question_id, answer_number),
	CONSTRAINT FK_answer_question FOREIGN KEY (question_id) REFERENCES question(question_id)
);

INSERT INTO quiz
(quiz_name)
VALUES
('Solar System Quiz');

--Question 1
INSERT INTO question
(question_text, correct_answer)
VALUES
('Phobos and Diemos 
 are the moons of which planet in our solar system?', 1);

INSERT INTO answer
(question_id, answer_number, answer_text)
VALUES
(lastval(), 1, 'Mars'),
(lastval(), 2, 'Venus'),
(lastval(), 3, 'Pluto');

INSERT INTO quiz_question
(quiz_id, question_id, question_number)
VALUES
((SELECT quiz_id FROM quiz WHERE quiz_name = 'test_quiz'), lastval(), 1);

--Question 2
INSERT INTO question
(question_text, correct_answer)
VALUES
('What is the fifth planet from the sun?', 3);

INSERT INTO answer
(question_id, answer_number, answer_text)
VALUES
(lastval(), 1, 'Mercury'),
(lastval(), 2, 'Neptune'),
(lastval(), 3, 'Jupiter');

INSERT INTO quiz_question
(quiz_id, question_id, question_number)
VALUES
((SELECT quiz_id FROM quiz WHERE quiz_name = 'test_quiz'), lastval(), 2);

--Question 3
INSERT INTO question
(question_text, correct_answer)
VALUES
('How may moons does the planet Mars have?', 2);

INSERT INTO answer
(question_id, answer_number, answer_text)
VALUES
(lastval(), 1, '3'),
(lastval(), 2, '2'),
(lastval(), 3, '0');

INSERT INTO quiz_question
(quiz_id, question_id, question_number)
VALUES
((SELECT quiz_id FROM quiz WHERE quiz_name = 'test_quiz'), lastval(), 3);

--Question 4
INSERT INTO question
(question_text, correct_answer)
VALUES
(' Which is the only planet in our solar 
 system to rotate clockwise?', 2);

INSERT INTO answer
(question_id, answer_number, answer_text)
VALUES
(lastval(), 1, 'Mars'),
(lastval(), 2, 'Venus'),
(lastval(), 3, 'Saturn');

INSERT INTO quiz_question
(quiz_id, question_id, question_number)
VALUES
((SELECT quiz_id FROM quiz WHERE quiz_name = 'test_quiz'), lastval(), 4);

--Question 5
INSERT INTO question
(question_text, correct_answer)
VALUES
('How many planets are there in our solar system?', 1);

INSERT INTO answer
(question_id, answer_number, answer_text)
VALUES
(lastval(), 1, '8'),
(lastval(), 2, '10'),
(lastval(), 3, '11');

INSERT INTO quiz_question
(quiz_id, question_id, question_number)
VALUES
((SELECT quiz_id FROM quiz WHERE quiz_name = 'test_quiz'), lastval(), 5);


COMMIT TRANSACTION;