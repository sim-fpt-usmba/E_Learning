package ma.ac.usmba.fpt.e_learning.Model;

import java.util.ArrayList;

public class Semestre {
    private String name;
    private ArrayList<Module> modules;

    public Semestre() {
    }

    public Semestre(String name, ArrayList<Module> modules) {
        this.name = name;
        this.modules = modules;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Module> getModules() {
        return modules;
    }

    public void setModules(ArrayList<Module> modules) {
        this.modules = modules;
    }
}
