package ma.ac.usmba.fpt.e_learning.Model;

import java.text.DateFormat;
import java.util.Date;

public class Message {
    private String message;
    private String auteur;
    private Date date;

    public Message() {
    }

    public Message(String message, String auteur) {
        this.message = message;
        this.auteur = auteur;
        this.date = new Date();
    }

    public Message(String message, String auteur, Date date) {
        this.message = message;
        this.auteur = auteur;
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String formatTime() {
        return DateFormat.getTimeInstance(DateFormat.SHORT).format(date);
    }
}
