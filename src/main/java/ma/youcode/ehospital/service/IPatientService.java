package ma.youcode.ehospital.service;

import ma.youcode.ehospital.model.Consultation;
import ma.youcode.ehospital.model.Department;
import ma.youcode.ehospital.model.Doctor;
import ma.youcode.ehospital.model.Patient;

import java.util.List;

public interface IPatientService {
    void createConsultation(Patient patient, Consultation consultation);

    void updateConsultation(Patient patient, Consultation consultation);

    void cancelConsultation(Patient patient, Consultation consultation);

    List<Consultation> getAllConsultations(Patient patient);

    List<Doctor> getDoctorsByDepartment(Department department);

}
