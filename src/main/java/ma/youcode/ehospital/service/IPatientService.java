package ma.youcode.ehospital.service;

import ma.youcode.ehospital.model.Consultation;
import ma.youcode.ehospital.model.Department;
import ma.youcode.ehospital.model.Doctor;
import ma.youcode.ehospital.model.Patient;

import java.util.List;

public interface IPatientService {
    void createConsultation(Consultation consultation);

    void updateConsultation(Consultation consultation);

    void cancelConsultation(Consultation consultation);

    List<Consultation> getAllConsultations(Patient patient);

    List<Doctor> getDoctorsByDepartment(Department department);

}
