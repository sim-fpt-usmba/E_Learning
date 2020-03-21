package ma.ac.usmba.fpt.e_learning.Model;

import java.util.ArrayList;

public class Module {
    private String nom_module;
    private ArrayList<Seance> listof_seances;
    public Module() {

    }

    public Module(String nom_module) {
        this.nom_module = nom_module;
        listof_seances=new ArrayList<Seance>();
    }

    public ArrayList<Seance> getListof_seances() {
        return listof_seances;
    }

    public void setListof_seances(ArrayList<Seance> listof_seances) {
        this.listof_seances = listof_seances;
    }

    public String getNom_module() {
        return nom_module;
    }

    public void setNom_module(String nom_module) {
        this.nom_module = nom_module;
    }

    public void add_seance(Seance sean){
        if (!exists(this,sean))
            listof_seances.add(sean);
    }
    public void remove_seance(Seance sean){
        if (exists(this,sean))
            listof_seances.remove(sean);
    }
    public boolean exists(Module m, Seance sean){
        for(Seance x:m.getListof_seances()){
            if((x.getNom_seance()).equals(sean.getNom_seance()))
                return true;
            break;
        }
        return false;
    }
}
