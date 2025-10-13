package ma.youcode.ehospital.service.impl;

import ma.youcode.ehospital.exception.ValidationException;
import ma.youcode.ehospital.model.Consultation;
import ma.youcode.ehospital.model.Doctor;
import ma.youcode.ehospital.model.Patient;
import ma.youcode.ehospital.repository.IConsultationRepository;
import ma.youcode.ehospital.repository.IDoctorRepository;
import ma.youcode.ehospital.service.IDoctorService;

import java.util.List;

public class DoctorServiceImpl implements IDoctorService {

    IConsultationRepository consultationRepository;
    IDoctorRepository doctorRepository;

    public DoctorServiceImpl(IConsultationRepository consultationRepository, IDoctorRepository doctorRepository) {
        this.consultationRepository = consultationRepository;
        this.doctorRepository = doctorRepository;
    }

    @Override
    public List<Consultation> getConsultations(Doctor doctor) {
        if (doctor == null) {
            throw new ValidationException("Doctor is null");
        }

        return consultationRepository.findByDoctor(doctor);
    }

    @Override
    public void updateConsultation(Consultation consultation, Doctor doctor) {
        PatientServiceImpl.validateConsultation(consultation);

        if (!consultation.getDoctor().getEmail().equals(doctor.getEmail())) {
            throw new ValidationException("Doctor's email doesn't match");
        }

        consultationRepository.update(consultation);
    }

    @Override
    public void createReport(Consultation consultation) {
        // TO DO Later
    }

    @Override
    public List<Consultation> getPatientHistory(Patient patient) {
        if (patient == null) {
            throw new ValidationException("Patient is null");
        }
        return consultationRepository.findByPatient(patient);
    }
}
