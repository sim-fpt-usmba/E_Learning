package ma.ac.usmba.fpt.e_learning.Model;

import java.util.List;

public class Quiz {
    private User user;
    private List<QuestionAnswer> questionAnswers;

    public Quiz() {
    }

    public Quiz(User user, List<QuestionAnswer> questionAnswers) {
        this.user = user;
        this.questionAnswers = questionAnswers;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<QuestionAnswer> getQuestionAnswers() {
        return questionAnswers;
    }

    public void setQuestionAnswers(List<QuestionAnswer> questionAnswers) {
        this.questionAnswers = questionAnswers;
    }
}
