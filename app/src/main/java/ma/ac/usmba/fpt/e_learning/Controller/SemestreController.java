package ma.ac.usmba.fpt.e_learning.Controller;

import java.util.ArrayList;

import ma.ac.usmba.fpt.e_learning.Model.Module;
import ma.ac.usmba.fpt.e_learning.Model.Seance;
import ma.ac.usmba.fpt.e_learning.Model.Semestre;

public class SemestreController {
    public static ArrayList<Semestre> setModules (){
        ArrayList<Semestre> semesters=new ArrayList<Semestre>();
        semesters.add(new Semestre("Semestre 4"));
        semesters.add(new Semestre("Semestre 6"));
        Module m1=new Module("Module 1");
        Module m2=new Module("Module 2");
        Module m3=new Module("Module 3");
        Module m4=new Module("Module 3");
        Module m5=new Module("Module 5");
        Module m6=new Module("Module 6");
        m1.add_seance(new Seance("Seance 1"));
        m1.add_seance(new Seance("Seance 2"));
        m2.add_seance(new Seance("Seance 1"));
        m2.add_seance(new Seance("Seance 2"));
        m3.add_seance(new Seance("Seance 1"));
        m3.add_seance(new Seance("Seance 2"));
        semesters.get(0).add_module(m1);
        semesters.get(0).add_module(m2);
        semesters.get(1).add_module(m3);
        semesters.get(1).add_module(m4);
        semesters.get(1).add_module(m5);
        semesters.get(1).add_module(m6);
        return semesters;
    }
}
