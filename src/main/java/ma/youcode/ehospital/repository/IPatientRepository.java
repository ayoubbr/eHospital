package ma.youcode.ehospital.repository;

import ma.youcode.ehospital.model.Doctor;
import ma.youcode.ehospital.model.Patient;

import java.util.List;

public interface IPatientRepository {

    Patient save(Patient patient);

    Patient update(Patient patient);

    void delete(int id);

    List<Patient> findAll();

    Patient findById(int id);

    List<Patient> findByDoctor(Doctor doctor);

}
