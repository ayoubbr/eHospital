package ma.youcode.ehospital.repository;

import ma.youcode.ehospital.model.Consultation;
import ma.youcode.ehospital.model.Doctor;
import ma.youcode.ehospital.model.Patient;

import java.util.List;

public interface IConsultationRepository {

    Consultation save(Consultation consultation);

    Consultation update(Consultation consultation);

    void delete(Consultation consultation);

    List<Consultation> findAll();

    Consultation findById(int id);

    void cancel(Consultation consultation);

    List<Consultation> findByPatient(Patient patient);

    List<Consultation> findByDoctor(Doctor doctor);
}
