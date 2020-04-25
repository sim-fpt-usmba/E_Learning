package ma.ac.usmba.fpt.e_learning.Model;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

public class ContenuModel {
    private Date date;
    private String description;
   // private ArrayList<AudioModel> audios;
    private ArrayList<String> files;

    public ContenuModel(){}

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<String> getFiles() {
        return files;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFiles(ArrayList<String> files) {
        this.files = files;
    }

    public ContenuModel(Date date, String description, ArrayList<String> files){
        this.date = date;
        this.description = description;
        this.files = files;
    }


}
