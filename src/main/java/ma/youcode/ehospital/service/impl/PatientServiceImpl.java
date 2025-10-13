package ma.youcode.ehospital.service.impl;

import ma.youcode.ehospital.exception.ObjectNotFound;
import ma.youcode.ehospital.exception.TimeSlotIsNotAvailableException;
import ma.youcode.ehospital.exception.ValidationException;
import ma.youcode.ehospital.model.*;
import ma.youcode.ehospital.repository.IConsultationRepository;
import ma.youcode.ehospital.repository.IDoctorRepository;
import ma.youcode.ehospital.repository.IRoomRepository;
import ma.youcode.ehospital.repository.impl.RoomRepositoryImpl;
import ma.youcode.ehospital.service.IPatientService;

import java.time.LocalTime;
import java.util.List;

public class PatientServiceImpl implements IPatientService {

    IConsultationRepository consultationRepo;
    IDoctorRepository doctorRepo;
    IRoomRepository roomRepo;

    public PatientServiceImpl(IConsultationRepository consultationRepo, IDoctorRepository doctorRepo, IRoomRepository roomRepo) {
        this.consultationRepo = consultationRepo;
        this.doctorRepo = doctorRepo;
        this.roomRepo = roomRepo;
    }

    @Override
    public void createConsultation(Consultation consultation) {
        validateConsultation(consultation);

        if (!isRoomAvailable(consultation)) {
            throw new TimeSlotIsNotAvailableException("Room is not available in this time");
        }

        if (!isDoctorAvailable(consultation)) {
            throw new TimeSlotIsNotAvailableException("Doctor is not available in this time");
        }

        consultationRepo.save(consultation);
    }


    @Override
    public void updateConsultation(Consultation consultation) {
        validateConsultation(consultation);

        if (consultationRepo.findById(consultation.getId()) == null) {
            throw new ObjectNotFound("Consultation not found");
        }

        if (!isRoomAvailable(consultation)) {
            throw new TimeSlotIsNotAvailableException("Room is not available in this time");
        }

        if (!isDoctorAvailable(consultation)) {
            throw new TimeSlotIsNotAvailableException("Doctor is not available in this time");
        }
        consultationRepo.update(consultation);
    }

    @Override
    public void cancelConsultation(Consultation consultation) {
        if (consultationRepo.findById(consultation.getId()) == null) {
            throw new ObjectNotFound("Consultation not found");
        }

        validateConsultation(consultation);

        consultation.setStatus(Status.ANNULLED);
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

    private boolean isRoomAvailable(Consultation consultation) {
        Room roomById = roomRepo.findById(consultation.getRoom().getId());
        if (roomById == null) {
            throw new ValidationException("Room not found");
        }

        return isAvailable(consultation, roomById.getConsultations());
    }

    private boolean isDoctorAvailable(Consultation consultation) {
        Doctor doctorById = doctorRepo.findById(consultation.getDoctor().getId());
        if (doctorById == null) {
            throw new ValidationException("Doctor not found");
        }

        return isAvailable(consultation, doctorById.getConsultations());
    }

    private boolean isAvailable(Consultation consultation, List<Consultation> consultations) {
        LocalTime time = consultation.getDate().toLocalTime();
        int minute = time.getMinute();
        LocalTime startTime = LocalTime.of(9, 0);
        LocalTime endTime = LocalTime.of(18, 30);

        if (minute != 0 && minute != 30) {
            throw new TimeSlotIsNotAvailableException("Minute is not available");
        }

        if (time.isBefore(startTime) || time.isAfter(endTime)) {
            throw new TimeSlotIsNotAvailableException("TimeSlot is not available | Consultation time must be between 09:00 and 18:30");
        }

        return consultations.stream().noneMatch(c -> c.getDate().equals(consultation.getDate()));
    }
}
