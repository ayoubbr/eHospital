package ma.youcode.ehospital.service;

import ma.youcode.ehospital.model.Consultation;
import ma.youcode.ehospital.model.Doctor;
import ma.youcode.ehospital.model.Patient;

import java.util.List;

public interface IDoctorService {

    List<Consultation> getConsultations(Doctor doctor);

    void updateConsultation(Consultation consultation, Doctor doctor);

    void createReport(Consultation consultation);

    List<Consultation> getPatientHistory(Patient patient);

}
