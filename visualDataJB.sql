create database visualDataJB;
use visualDataJB;

CREATE TABLE user
(
UserId int NOT NULL AUTO_INCREMENT,
Username varchar(255) NOT NULL,
Password varchar(255) NOT NULL,
FirstName varchar(255),
LastName varchar(255),
Email varchar(255),
PRIMARY KEY (UserId) 
);

CREATE TABLE admin
(
UserId int NOT NULL,
PRIMARY KEY (UserId),
FOREIGN KEY (UserId) REFERENCES user(UserId)
);

CREATE TABLE quizQuestion
(
Id int NOT NULL AUTO_INCREMENT,
question varchar(1000) NOT NULL,
question_nr int NOT NULL,
PRIMARY KEY (Id) 
);

ALTER TABLE quizQuestion
  ADD UNIQUE INDEX uniqueindex_01 (question);

CREATE TABLE quizAnswer
(
Id int NOT NULL AUTO_INCREMENT,
Q_Id int not null,
answer varchar(1000) NOT NULL,
answer_nr int NOT NULL,
correct boolean not null,
PRIMARY KEY (Id) ,
FOREIGN KEY (Q_Id) REFERENCES quizQuestion(Id)
);

/*delete from quizAnswer;
delete from quizQuestion;*/
insert into user(username, password, firstname, lastname, email)  
values ("Jacek", "Jacek", "Jacek", "Byzdra", "jacek@jacek.com");
insert into admin(userId) values (1);

insert into quizQuestion(question_nr , question)  values (4, "Stack is also called as");
insert into quizAnswer(Q_Id, answer, answer_nr, correct)  values (1, "Last in first out", 1, true);
insert into quizAnswer(Q_Id, answer, answer_nr, correct)  values (1, "First in last out", 2, false);
insert into quizAnswer(Q_Id, answer, answer_nr, correct)  values (1, "Last in last out", 3, false);
insert into quizAnswer(Q_Id, answer, answer_nr, correct)  values (1, "First in first out", 4, false);

insert into quizQuestion(question_nr , question)  values (6, "...... is not the component of data structure");
insert into quizAnswer(Q_Id, answer, answer_nr, correct)  values (2, "Operations", 1, false);
insert into quizAnswer(Q_Id, answer, answer_nr, correct)  values (2, "Storage Structres", 2, false);
insert into quizAnswer(Q_Id, answer, answer_nr, correct)  values (2, "Algorithms", 3, false);
insert into quizAnswer(Q_Id, answer, answer_nr, correct)  values (2, "None of above", 4, true);


insert into quizQuestion(question_nr , question)  values (100, "A list which displays the relationship of adjacency between elements is said to be");
insert into quizAnswer(Q_Id, answer, answer_nr, correct)  values (3, "linear", 1, true);
insert into quizAnswer(Q_Id, answer, answer_nr, correct)  values (3, "non linear", 2, false);
insert into quizAnswer(Q_Id, answer, answer_nr, correct)  values (3, "linked list", 3, false);
insert into quizAnswer(Q_Id, answer, answer_nr, correct)  values (3, "trees", 4, false);

insert into quizQuestion(question_nr , question)  values (99, "The data structure which is one ended is .....");
insert into quizAnswer(Q_Id, answer, answer_nr, correct)  values (4, "queue", 1, false);
insert into quizAnswer(Q_Id, answer, answer_nr, correct)  values (4, "stack", 2, true);
insert into quizAnswer(Q_Id, answer, answer_nr, correct)  values (4, "tree", 3, false);
insert into quizAnswer(Q_Id, answer, answer_nr, correct)  values (4, "graph", 4, false);

insert into quizQuestion(question_nr , question)  values (98, "Which of the following is an application of stack?");
insert into quizAnswer(Q_Id, answer, answer_nr, correct)  values (5, "finding factorial", 1, false);
insert into quizAnswer(Q_Id, answer, answer_nr, correct)  values (5, "tower of Hanoi", 2, false);
insert into quizAnswer(Q_Id, answer, answer_nr, correct)  values (5, "infix to postfix conversion", 3, false);
insert into quizAnswer(Q_Id, answer, answer_nr, correct)  values (5, "all of the above", 4, true);


insert into quizQuestion(question_nr , question)  values (97, "The time complexity of quick sort is ....");
insert into quizAnswer(Q_Id, answer, answer_nr, correct)  values (6, "O(n)", 1, false);
insert into quizAnswer(Q_Id, answer, answer_nr, correct)  values (6, "O(n2)", 2, false);
insert into quizAnswer(Q_Id, answer, answer_nr, correct)  values (6, "O(n log n)", 3, true);
insert into quizAnswer(Q_Id, answer, answer_nr, correct)  values (6, "O(log n)", 4, false);

insert into quizQuestion(question_nr , question)  values (96, "In a priority queue, insertion and deletion takes place at ...");
insert into quizAnswer(Q_Id, answer, answer_nr, correct)  values (7, "front, rear end", 1, false);
insert into quizAnswer(Q_Id, answer, answer_nr, correct)  values (7, "only at rear end", 2, false);
insert into quizAnswer(Q_Id, answer, answer_nr, correct)  values (7, "only at front end)", 3, false);
insert into quizAnswer(Q_Id, answer, answer_nr, correct)  values (7, "any position", 4, true);




