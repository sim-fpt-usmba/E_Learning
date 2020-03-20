package ma.ac.usmba.fpt.e_learning.Model;

public class Seance {
    private String nom_seance;
    private String intitule_seance;
    public Seance() {

    }

    public Seance(String nom_seance) {
        this.nom_seance = nom_seance;
    }

    public Seance(String nom_seance,String intitule_seance) {
        this.nom_seance = nom_seance;
        this.intitule_seance = intitule_seance;
    }

    public String getIntitule_seance() {
        return intitule_seance;
    }

    public void setIntitule_seance(String intitule_seance) {
        this.intitule_seance = intitule_seance;
    }

    public String getNom_seance() {
        return nom_seance;
    }

    public void setNom_seance(String nom_seance) {
        this.nom_seance = nom_seance;
    }
}
