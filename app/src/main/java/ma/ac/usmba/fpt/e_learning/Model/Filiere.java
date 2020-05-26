package ma.ac.usmba.fpt.e_learning.Model;

import java.util.ArrayList;

public class Filiere {
    private int id;
    private String name;
    private String description;
    public ArrayList<Module> modules;

    public Filiere() {

    }

    public Filiere(int id_filiere) {
        this.id = id_filiere;
    }

    public Filiere(int id_filiere, String nom_filere) {
        this.id = id_filiere;
        this.name = nom_filere;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Module> getModules() {
        return modules;
    }

    public void setModules(ArrayList<Module> modules) {
        this.modules = modules;
    }
    public String toString() {

        return " " + id  + name ;
    }
}
