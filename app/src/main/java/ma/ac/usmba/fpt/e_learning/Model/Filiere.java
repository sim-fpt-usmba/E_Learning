package ma.ac.usmba.fpt.e_learning.Model;

public class Filiere {
private String id_filiere;
private String nom_filere;

    public Filiere() {

    }

    public Filiere(String id_filiere) {
        this.id_filiere = id_filiere;
    }

    public Filiere(String id_filiere, String nom_filere) {
        this.id_filiere = id_filiere;
        this.nom_filere = nom_filere;
    }

    public String getId_filiere() {
        return id_filiere;
    }

    public void setId_filiere(String id_filiere) {
        this.id_filiere = id_filiere;
    }

    public String getNom_filere() {
        return nom_filere;
    }

    public void setNom_filere(String nom_filere) {
        this.nom_filere = nom_filere;
    }
}
