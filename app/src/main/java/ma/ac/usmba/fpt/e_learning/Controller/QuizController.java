package ma.ac.usmba.fpt.e_learning.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import ma.ac.usmba.fpt.e_learning.Model.Quiz;

public class QuizController {
    public static ArrayList<Quiz> getQuizzes(){
        ArrayList<Quiz> quizzes = new ArrayList<>();
        for(int i = 1 ; i < 5 ; i++){
            Quiz q = new Quiz();
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
            q.setReponses(reponses);
            quizzes.add(q);
        }
        return quizzes;
    }
}
