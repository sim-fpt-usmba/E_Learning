package ma.ac.usmba.fpt.e_learning.Model;

import java.util.ArrayList;

public class Module {
    private int id;
    private String name;
    private String ref;
    private String description;
    private String semester_id;
    private ArrayList<Seance> seances;

    public Module() {
    }

    public Module(String name, String ref, ArrayList<Seance> seances) {
        this.name = name;
        this.ref = ref;
        this.seances = seances;
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

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public ArrayList<Seance> getSeances() {
        return seances;
    }

    public void setSeances(ArrayList<Seance> seances) {
        this.seances = seances;
    }

    //TODO: write a function to return the ref and name but with a constraint on length
}
