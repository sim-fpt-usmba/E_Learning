package ma.ac.usmba.fpt.e_learning.Model;

import java.util.HashMap;

public class Quiz {
    private String question;
    private static HashMap<String,Boolean> reponses;
    public Quiz() {
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public static HashMap<String, Boolean> getReponses() {
        return reponses;
    }

    public static void setReponses(HashMap<String, Boolean> reponses) {
        Quiz.reponses = reponses;
    }
}
