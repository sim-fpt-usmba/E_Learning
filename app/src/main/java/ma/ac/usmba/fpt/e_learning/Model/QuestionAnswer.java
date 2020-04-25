package ma.ac.usmba.fpt.e_learning.Model;

import java.io.Serializable;
import java.util.HashMap;

public class QuestionAnswer implements Serializable {
    private String question;
    private  HashMap<String,Boolean> answers;
    public QuestionAnswer() {
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public  HashMap<String, Boolean> getAnswers() {
        return answers;
    }

    public  void setAnswers(HashMap<String, Boolean> answers) {
        this.answers = answers;
    }
}
