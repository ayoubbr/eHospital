package ma.youcode.ehospital.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true, nullable = false)
    private String name;
    @Column(nullable = false)
    private int capacity;
    @OneToMany(mappedBy = "room", fetch = FetchType.EAGER)
    private List<Consultation> consultations;

    public Room(int id, String name, int capacity, List<Consultation> consultations) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.consultations = consultations;
    }

    public Room() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Consultation> getConsultations() {
        return consultations;
    }

    public void setConsultations(List<Consultation> consultations) {
        this.consultations = consultations;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", capacity=" + capacity +
                ", consultationsCount=" + (consultations != null ? consultations.size() : 0) +
                '}';
    }
}
