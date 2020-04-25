package ma.ac.usmba.fpt.e_learning.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import ma.ac.usmba.fpt.e_learning.Model.Etudiant;
import ma.ac.usmba.fpt.e_learning.Model.QuestionAnswer;
import ma.ac.usmba.fpt.e_learning.Model.Quiz;
import ma.ac.usmba.fpt.e_learning.Model.User;

public class QuizController {
    public static ArrayList<QuestionAnswer> getQuizzes(){
        ArrayList<QuestionAnswer> quizzes = new ArrayList<>();
        for(int i = 1 ; i < 5 ; i++){
            QuestionAnswer q = new QuestionAnswer();
            q.setQuestion("Question : " +(i) +" .................?");
            HashMap<String, Boolean> reponses = new HashMap<>();
            for(int j = 1 ; j < 5 ; j++){
                reponses.put("reponse "+j,false);
            }
            Random random = new Random();
            int rand = random.nextInt(4) + 1;
            int counter = 0;
            for(Map.Entry reponse : reponses.entrySet()){
                if(counter == rand){
                    reponses.put(String.valueOf(reponse.getKey()),true);
                }
            }
            q.setAnswers(reponses);
            quizzes.add(q);
        }
        return quizzes;
    }

    public static ArrayList<Quiz> getEtudiantAnswer() {
        ArrayList<Quiz> etudiantAnswers = new ArrayList<>();

        for (int e = 0; e < 25; e++) {
            User etudiant = new Etudiant();
            etudiant.setName("Etudiant " + e);
            Quiz quiz = new Quiz();
            quiz.setUser(etudiant);
            int nbrQuestions = new Random().nextInt(10);
            ArrayList<QuestionAnswer> questionAnswers = new ArrayList<>();
            for (int q = 0; q < nbrQuestions; q++) {
                QuestionAnswer questionAnswer = new QuestionAnswer();
                questionAnswer.setQuestion("Question " + q);
                HashMap<String, Boolean> answers = new HashMap<>();
                int randomAnswer = new Random().nextInt(3);
                for (int a = 0; a < 3; a++) {
                    if (a == randomAnswer) {
                        answers.put("Answer " + a, true);
                    } else {
                        answers.put("Answer " + a, false);
                    }
                }
                questionAnswer.setAnswers(answers);
                questionAnswers.add(questionAnswer);
            }
            quiz.setQuestionAnswers(questionAnswers);
            etudiantAnswers.add(quiz);
        }

        return etudiantAnswers;
    }
}
