package ma.ac.usmba.fpt.e_learning.Model;

import java.util.ArrayList;
import java.util.List;

public class Quiz {
    private User user;
    private ArrayList<QuestionAnswer> questionAnswers;

    public Quiz() {
    }

    public Quiz(User user, ArrayList<QuestionAnswer> questionAnswers) {
        this.user = user;
        this.questionAnswers = questionAnswers;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<QuestionAnswer> getQuestionAnswers() {
        return questionAnswers;
    }

    public void setQuestionAnswers(ArrayList<QuestionAnswer> questionAnswers) {
        this.questionAnswers = questionAnswers;
    }

    public String getScore() {
        int score = 0;
        for (QuestionAnswer answer : questionAnswers) {
            if (answer.isCorrect()) {
                score++;
            }
        }
        return score + "/" + questionAnswers.size();
    }

}
