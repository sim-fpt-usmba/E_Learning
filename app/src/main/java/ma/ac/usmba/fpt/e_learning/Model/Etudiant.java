package ma.ac.usmba.fpt.e_learning.Model;

import java.io.Serializable;

@SuppressWarnings("ALL")
public class Etudiant extends User implements Serializable {
    private String cne;
    private Filiere filiere;

    public Etudiant() {
    }

    public Filiere getFiliere() {
        return filiere;
    }

    public void setFiliere(Filiere filiere) {
        this.filiere = filiere;
    }

    public Etudiant(int id, int user_id, String name, String role, String email, String avatar, String phone, String cin, String cne, Filiere f) {
        super(id, user_id, name, role, email, avatar, phone, cin);
        this.cne = cne;
        this.filiere = f;
    }

    public Etudiant(String userLogin) {
    }

    public String getCne() {
        return cne;
    }

    public void setCne(String cne) {
        this.cne = cne;
    }

    public String build_access_token() {
        return token_type + " " + access_token;
    }
}
