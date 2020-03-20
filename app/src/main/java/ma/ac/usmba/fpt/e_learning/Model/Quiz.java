package ma.ac.usmba.fpt.e_learning.Model;

import java.util.ArrayList;
import java.util.HashMap;

public class Quiz {
    String question;
    HashMap<Boolean,String> answers;

    public Quiz(String question , HashMap answers){
        this.question = question;
        this.answers = answers;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public HashMap<Boolean, String> getAnswers() {
        return answers;
    }

    public void setAnswers(HashMap<Boolean, String> answers) {
        this.answers = answers;
    }
}
