package ma.ac.usmba.fpt.e_learning.Model;

public class Etudiant extends User{
    private String cne;

    public Etudiant() {
    }

    public Etudiant(int id, int user_id, String name, String role, String email, String avatar, String phone, String cin, String cne) {
        super(id, user_id, name, role, email, avatar, phone, cin);
        this.cne = cne;
    }

    public String getCne() {
        return cne;
    }

    public void setCne(String cne) {
        this.cne = cne;
    }
}
