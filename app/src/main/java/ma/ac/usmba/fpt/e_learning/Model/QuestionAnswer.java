package ma.ac.usmba.fpt.e_learning.Model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class QuestionAnswer implements Serializable {
    private String question;
    private  HashMap<String,Boolean> answers;
    private String studentAnswer;
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

    public String getStudentAnswer() {
        return studentAnswer;
    }

    public void setStudentAnswer(String studentAnswer) {
        this.studentAnswer = studentAnswer;
    }

    public String getWriteAnswer() {
        String writeAnswer = "";
        for (Map.Entry<String, Boolean> entry : answers.entrySet()) {
            if (entry.getValue()) {
                writeAnswer = entry.getKey();
                break;
            }
        }
        return writeAnswer;
    }

    public boolean isCorrect() {
        if (studentAnswer.equals(getWriteAnswer())) {
            return true;
        }
        return false;
    }
}
