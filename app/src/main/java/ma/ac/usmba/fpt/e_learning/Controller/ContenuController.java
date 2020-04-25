package ma.ac.usmba.fpt.e_learning.Controller;

import android.os.Environment;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import ma.ac.usmba.fpt.e_learning.Model.ContenuModel;

public class ContenuController {

    public static ContenuModel getContenu(){
        Date date = new Date();
        String description = "Test description : \n\n\nBonjour les étudiants ci-joint la derniére partie du cour " +
                "Développement mobile hybride.\n\n\nSi Vous avez des questions n'hésitez pas de me contacter\n\n\n" +
                "Cordialement.";
        ArrayList<String> files = new ArrayList<>();
        files.add(new File("Nom du fichier 1").toString());
        files.add(new File("Nom du fichier 2").toString());
        files.add(new File("Nom du fichier 3").toString());
        files.add(new File("Nom du fichier 4").toString());

        return new ContenuModel(date,description,files);
    }
}
