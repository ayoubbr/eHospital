package ma.youcode.ehospital.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "admins")
public class Admin extends Person {
    public Admin(int id, String firstName, String lastName, String email, String password, String role) {
        super(id, firstName, lastName, email, password, role);
    }

    public Admin() {
    }
}
