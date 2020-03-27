package ma.ac.usmba.fpt.e_learning.Controller;

import java.util.ArrayList;

import ma.ac.usmba.fpt.e_learning.Model.Module;
import ma.ac.usmba.fpt.e_learning.Model.Seance;

public class ModuleController {

    public static ArrayList<Module> getModule() {
        ArrayList<Module> modules = new ArrayList<>();
        for (int m = 1; m < 5; m++) {
            Module module = new Module();
            module.setName("Module " + m);
            ArrayList<Seance> seances = new ArrayList<>();
            for (int s = 1; s < 7; s++) {
                Seance seance = new Seance();
                seance.setId(""+ s);
                seance.setTitle("Seance" + s);
                seances.add(seance);
            }
            module.setSeances(seances);
            modules.add(module);
        }
        return modules;
    }
}
