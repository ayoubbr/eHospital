package ma.youcode.ehospital.service;

import ma.youcode.ehospital.model.Consultation;
import ma.youcode.ehospital.model.Patient;

import java.util.List;

public interface IDoctorService {

    List<Consultation> getConsultations();

    void updateConsultation(Consultation consultation);

    void createReport(Consultation consultation);

    void getPatientHistory(Patient patient);

}
