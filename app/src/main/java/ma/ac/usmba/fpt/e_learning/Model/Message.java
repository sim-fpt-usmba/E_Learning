package ma.ac.usmba.fpt.e_learning.Model;

public class Message {
    private int id;
    private String message;
    private String auteur;
    private String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    private String time;

    public Message() {
    }

    public Message(String message, String auteur) {
        this.message = message;
        this.auteur = auteur;
        //this.date = new Date();
    }

    public Message(String message, String auteur, String date, String time) {
        this.message = message;
        this.auteur = auteur;
        this.date = date;
        this.time = time;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String formatTime() {
        return this.time;
        //return DateFormat.getTimeInstance(DateFormat.SHORT).format(date);
    }
}
