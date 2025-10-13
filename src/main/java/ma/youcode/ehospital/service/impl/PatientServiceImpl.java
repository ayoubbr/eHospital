package ma.youcode.ehospital.service.impl;

import ma.youcode.ehospital.exception.ValidationException;
import ma.youcode.ehospital.model.*;
import ma.youcode.ehospital.repository.IConsultationRepository;
import ma.youcode.ehospital.repository.IDoctorRepository;
import ma.youcode.ehospital.service.IPatientService;

import java.util.List;

public class PatientServiceImpl implements IPatientService {

    IConsultationRepository consultationRepo;
    IDoctorRepository doctorRepo;

    public PatientServiceImpl(IConsultationRepository consultationRepo, IDoctorRepository doctorRepo) {
        this.consultationRepo = consultationRepo;
        this.doctorRepo = doctorRepo;
    }

    @Override
    public void createConsultation(Consultation consultation) {
        validateConsultation(consultation);
        consultationRepo.save(consultation);
    }


    @Override
    public void updateConsultation(Consultation consultation) {
        validateConsultation(consultation);
        consultationRepo.update(consultation);
    }

    @Override
    public void cancelConsultation(Consultation consultation) {
        validateConsultation(consultation);
        consultation.setStatusByName(Status.ANNULLED);
        consultationRepo.update(consultation);
    }

    @Override
    public List<Consultation> getAllConsultations(Patient patient) {
        if (patient == null) {
            throw new ValidationException("Patient is null");
        }
        if (consultationRepo.findByPatient(patient) == null) {
            throw new ValidationException("No consultation found for patient");
        }

        return consultationRepo.findByPatient(patient);
    }

    @Override
    public List<Doctor> getDoctorsByDepartment(Department department) {
        if (department == null) {
            throw new ValidationException("Department is null");
        }
        if (doctorRepo.findDoctorsByDepartment(department) == null) {
            throw new ValidationException("No Doctor found for department");
        }
        return doctorRepo.findDoctorsByDepartment(department);
    }

    static void validateConsultation(Consultation consultation) {
        if (consultation == null) {
            throw new ValidationException("Consultation is null");
        }
        if (consultation.getDoctor() == null) {
            throw new ValidationException("Doctor is null");
        }
        if (consultation.getPatient() == null) {
            throw new ValidationException("Patient is null");
        }
        if (consultation.getRoom() == null) {
            throw new ValidationException("Room is null");
        }
    }
}
