package ma.ac.usmba.fpt.e_learning.Controller;

import java.util.ArrayList;

import ma.ac.usmba.fpt.e_learning.Model.Filiere;

public class ProfFiliereController {
    private String id_filiere;
    private String nom_filere;

    public static ArrayList<Filiere> set_Filiere() {
        ArrayList<Filiere> filieres = new ArrayList<Filiere>();
        filieres.add(new Filiere("SMI", "Filière SMI"));
        filieres.add(new Filiere("SMA", "Filière SMA"));
        filieres.add(new Filiere("SMP", "Filière SMP"));
        filieres.add(new Filiere("SMC", "Filière SMC"));
        filieres.add(new Filiere("SMG", "Filière SMG"));
        return filieres;
    }
}