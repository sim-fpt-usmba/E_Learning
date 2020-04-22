package ma.ac.usmba.fpt.e_learning.Controller;

import java.util.ArrayList;

import ma.ac.usmba.fpt.e_learning.Model.Module;
import ma.ac.usmba.fpt.e_learning.Model.Semestre;

public class SemestreController {
    public static ArrayList<Semestre> getSemestres() {
        ArrayList<Semestre> semestres = new ArrayList<>();
        for (int s = 1; s < 3; s++) {
            Semestre semestre = new Semestre();
            semestre.setName("Semestre " + s);
            ArrayList<Module> modules = new ArrayList<>();
            for (int m = 1; m < 7; m++) {
                Module module = new Module();
                module.setName("Module " + m);
                module.setRef("M" + m);
                modules.add(module);
            }
            semestre.setModules(modules);
            semestres.add(semestre);
        }
        return semestres;
    }
}
