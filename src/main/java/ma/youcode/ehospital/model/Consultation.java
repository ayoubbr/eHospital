package ma.youcode.ehospital.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "consultations")
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, columnDefinition = "TIMESTAMP(0)")
    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    private String report;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    public Consultation(int id, LocalDateTime date, LocalTime hour, Status status, String report, Patient patient, Doctor doctor, Room room) {
        this.id = id;
        this.date = date;
        this.status = status;
        this.report = report;
        this.patient = patient;
        this.doctor = doctor;
        this.room = room;
    }

    public Consultation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "Consultation{" +
                "id=" + id +
                ", date=" + date +
                ", status=" + status +
                ", report='" + report + '\'' +
                ", patient=" + patient +
                ", doctor=" + doctor +
                ", room=" + room +
                '}';
    }
}
