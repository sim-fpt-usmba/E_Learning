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
        ArrayList<File> files = new ArrayList<>();
        files.add(new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"fichier de test 1"));
        files.add(new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"fichier de test 2"));

        return new ContenuModel(date,description,files);
    }
}
