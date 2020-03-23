package ma.ac.usmba.fpt.e_learning.Controller;

import java.util.ArrayList;

import ma.ac.usmba.fpt.e_learning.Model.Filiere;

public class ProfFiliereController {
    private String id_filiere;
    private String nom_filere;

    public static ArrayList<Filiere> set_Filiere() {
        ArrayList<Filiere> filieres = new ArrayList<Filiere>();
        filieres.add(new Filiere("SMI", "Sciences mathématique et informatique"));
        filieres.add(new Filiere("SMA", "Sciences mathématique appliqué"));
        filieres.add(new Filiere("SMP", "Sciences de la matière physique"));
        filieres.add(new Filiere("SMC", "Sciences de la matière chimie"));
        filieres.add(new Filiere("SMG", "Sciences de la matière gélogie"));
        return filieres;
    }
}
