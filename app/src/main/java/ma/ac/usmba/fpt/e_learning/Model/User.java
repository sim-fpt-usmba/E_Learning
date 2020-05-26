package ma.ac.usmba.fpt.e_learning.Model;

public class User {
    public int id;
    public int user_id;
    public String name;
    public String role;
    public String email;
    public String avatar;
    public String phone;
    public String cin;
    public String access_token;
    public String token_type;
    public String expires_at;

    public User() {
    }

    public User(int id, int user_id, String name, String role, String email, String avatar, String phone, String cin) {
        this.id = id;
        this.user_id = user_id;
        this.name = name;
        this.role = role;
        this.email = email;
        this.avatar = avatar;
        this.phone = phone;
        this.cin = cin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }
}
