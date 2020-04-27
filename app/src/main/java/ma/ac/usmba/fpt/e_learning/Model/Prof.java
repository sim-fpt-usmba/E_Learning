package ma.ac.usmba.fpt.e_learning.Model;

public class Prof extends User {

    private String matricule;

    public Prof() {
    }

    public Prof(int id, int user_id, String name, String role, String email, String avatar, String phone, String cin, String matricule) {
        super(id, user_id, name, role, email, avatar, phone, cin);
        this.matricule = matricule;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }
}
