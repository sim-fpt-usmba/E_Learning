package ma.ac.usmba.fpt.e_learning.Model;

public class Module {
   private String nom_module;
    public Module() {

    }

    public Module(String nom_module) {
        this.nom_module = nom_module;

    }

    public String getNom_module() {
        return nom_module;
    }

    public void setNom_module(String nom_module) {
        this.nom_module = nom_module;
    }
}
