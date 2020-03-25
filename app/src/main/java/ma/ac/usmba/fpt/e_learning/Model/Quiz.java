package ma.ac.usmba.fpt.e_learning.Model;

import java.io.Serializable;
import java.util.HashMap;

public class Quiz implements Serializable {
    private String question;
    private  HashMap<String,Boolean> reponses;
    public Quiz() {
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public  HashMap<String, Boolean> getReponses() {
        return reponses;
    }

    public  void setReponses(HashMap<String, Boolean> reponses) {
        this.reponses = reponses;
    }
}
