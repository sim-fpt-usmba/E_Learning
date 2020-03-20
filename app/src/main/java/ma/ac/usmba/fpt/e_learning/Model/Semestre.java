package ma.ac.usmba.fpt.e_learning.Model;

import java.util.ArrayList;

public class Semestre {
    private String nom_semestre;
    private ArrayList<Module> listof_module;
    public Semestre(){

    }
    public Semestre(String nom_semestre) {
        this.nom_semestre = nom_semestre;
        listof_module=new ArrayList<Module>();
    }

    public String get_semestre() {
        return nom_semestre;
    }

    public void set_semestre(String nom_semestre) {
        this.nom_semestre = nom_semestre;
    }

    public ArrayList<Module> getListof_module() {
        return listof_module;
    }

    public void setListof_module(ArrayList<Module> listof_module) {
        this.listof_module = listof_module;
    }

    public void add_module(Module m){
        if (!exists(this,m))
            listof_module.add(m);
    }
    public void remove_module(Module m){
        if (exists(this,m))
            listof_module.remove(m);
    }
    public boolean exists(Semestre sem,Module m){
        for(Module x:sem.getListof_module()){
            if((x.getNom_module()).equals(m.getNom_module()))
                return true;
            break;
        }
        return false;
    }
}
