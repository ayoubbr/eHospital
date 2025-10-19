package ma.youcode.ehospital.service.impl;

import ma.youcode.ehospital.exception.ObjectNotFound;
import ma.youcode.ehospital.exception.TimeSlotIsNotAvailableException;
import ma.youcode.ehospital.exception.ValidationException;
import ma.youcode.ehospital.model.*;
import ma.youcode.ehospital.repository.*;
import ma.youcode.ehospital.service.IAdminService;

import java.time.LocalTime;
import java.util.List;

public class AdminServiceImpl implements IAdminService {

    IDoctorRepository doctorRepository;
    IConsultationRepository consultationRepository;
    IDepartmentRepository departmentRepository;
    IRoomRepository roomRepository;
    IPatientRepository patientRepository;
    IAdminRepository adminRepository;

    public AdminServiceImpl(IDoctorRepository doctorRepository,
                            IConsultationRepository consultationRepository,
                            IDepartmentRepository departmentRepository,
                            IRoomRepository roomRepository,
                            IPatientRepository patientRepository,
                            IAdminRepository adminRepository) {
        this.doctorRepository = doctorRepository;
        this.consultationRepository = consultationRepository;
        this.departmentRepository = departmentRepository;
        this.roomRepository = roomRepository;
        this.patientRepository = patientRepository;
        this.adminRepository = adminRepository;
    }

    @Override
    public void createDoctor(Doctor doctor) throws ValidationException {
        validate(doctor);
        if (doctorRepository.findByEmail(doctor.getEmail()) != null) {
            throw new ValidationException("doctor email already exists");
        }
        doctorRepository.save(doctor);
    }

    @Override
    public void updateDoctor(Doctor doctor) throws ValidationException {
        validate(doctor);
        if (doctorRepository.findById(doctor.getId()) == null) {
            throw new ObjectNotFound("Doctor not found");
        }
        doctorRepository.update(doctor);
    }

    @Override
    public void deleteDoctor(Doctor doctor) throws ValidationException {
        if (doctor == null) {
            throw new ValidationException("Doctor is null");
        }

        if (doctorRepository.findById(doctor.getId()) == null) {
            throw new ObjectNotFound("Doctor not found");
        }

        try {
            doctorRepository.delete(doctor.getId());
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<Doctor> getDoctors() throws ValidationException {
        if (doctorRepository.findAll().isEmpty()) {
            throw new ObjectNotFound("No Doctor found");
        }

        return doctorRepository.findAll();
    }

    @Override
    public Doctor getDoctorById(int id) throws ValidationException, ObjectNotFound {
        if (id <= 0) {
            throw new ValidationException("Doctor id is not valid");
        }

        if (doctorRepository.findById(id) == null) {
            throw new ObjectNotFound("Doctor not found");
        }

        return doctorRepository.findById(id);
    }

    @Override
    public Patient getPatientById(int id) throws ValidationException, ObjectNotFound {
        if (id <= 0) {
            throw new ValidationException("Patient id is not valid");
        }

        if (patientRepository.findById(id) == null) {
            throw new ObjectNotFound("Patient not found");
        }

        return patientRepository.findById(id);
    }

    @Override
    public void createDepartment(Department department) throws ValidationException {
        if (department == null) {
            throw new ValidationException("Department is null");
        }

        if (department.getName() == null || department.getName().isEmpty()) {
            throw new ValidationException("Department name is not valid");
        }

        if (departmentRepository.findByName(department.getName()) != null) {
            throw new ValidationException("Department name already exists");
        }

        departmentRepository.save(department);
    }

    @Override
    public void updateDepartment(Department department) {
        if (department == null) {
            throw new ValidationException("Department is null");
        }

        if (departmentRepository.findById(department.getId()) == null) {
            throw new ObjectNotFound("Department not found");
        }

        Department departmentDB = departmentRepository.findByName(department.getName());

        if (departmentDB != null && department.getId() != departmentDB.getId()) {
            throw new ValidationException("Department already exists");
        }

        if (department.getName() == null || department.getName().isEmpty()) {
            throw new ValidationException("Department name is not valid");
        }

        departmentRepository.update(department);
    }

    @Override
    public void deleteDepartment(Department department) {
        if (department == null) {
            throw new ValidationException("Department is null");
        }

        if (!department.getDoctors().isEmpty()) {
            throw new ValidationException("Department doctors not empty");
        }

        departmentRepository.delete(department.getId());
    }

    @Override
    public List<Department> getDepartments() throws ObjectNotFound {
        if (departmentRepository.findAll().isEmpty()) {
            throw new ObjectNotFound("No Department found");
        }

        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartmentById(int id) {
        if (id <= 0) {
            throw new ValidationException("Department id is not valid");
        }
        if (departmentRepository.findById(id) == null) {
            throw new ObjectNotFound("Department not found");
        }
        return departmentRepository.findById(id);
    }

    @Override
    public void setDoctorToDepartment(Doctor doctor, Department department) {
        if (doctor == null) {
            throw new ValidationException("Doctor is null");
        }
        if (department == null) {
            throw new ValidationException("Department is null");
        }

        Doctor dbDoctor = doctorRepository.findById(doctor.getId());
        if (dbDoctor == null) {
            throw new ObjectNotFound("Doctor not found");
        }

        doctor.setDepartment(department);
        doctorRepository.update(doctor);
    }

    @Override
    public void createRoom(Room room) {
        validateRoom(room);

        Room dbRoom = roomRepository.findByName(room.getName());

        if (dbRoom == null) {
            roomRepository.save(room);
        } else {
            throw new ObjectNotFound("Room name already exists");
        }
    }

    @Override
    public void updateRoom(Room room) {
        validateRoom(room);
        if (roomRepository.findById(room.getId()) == null) {
            throw new ObjectNotFound("Room not found");
        }

        Room dbRoom = roomRepository.findById(room.getId());

        if (roomRepository.findByName(room.getName()) != null && dbRoom.getId() != room.getId()) {
            throw new ValidationException("Room name already exists");
        }

        roomRepository.update(room);
    }

    @Override
    public void deleteRoom(Room room) {
        if (room == null) {
            throw new ValidationException("Room is null");
        }

        if (!room.getConsultations().isEmpty()) {
            throw new ValidationException("Room consultations not empty");
        }

        roomRepository.delete(room.getId());
    }

    @Override
    public List<Room> getRooms() {
        if (roomRepository.findAll().isEmpty()) {
            throw new ObjectNotFound("No Room found");
        }
        return roomRepository.findAll();
    }

    @Override
    public Room getRoomById(int id) {
        if (id <= 0) {
            throw new ValidationException("Room id is not valid");
        }
        if (roomRepository.findById(id) == null) {
            throw new ObjectNotFound("Room not found");
        }
        return roomRepository.findById(id);
    }

    @Override
    public List<Consultation> getConsultations() {
        if (consultationRepository.findAll().isEmpty()) {
            throw new ObjectNotFound("No Consultation found");
        }
        return consultationRepository.findAll();
    }

    @Override
    public Consultation getConsultationById(int id) {
        if (id <= 0) {
            throw new ValidationException("Consultation id is not valid");
        }
        if (consultationRepository.findById(id) == null) {
            throw new ObjectNotFound("Consultation not found");
        }
        return consultationRepository.findById(id);
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

        consultationRepository.save(consultation);
    }

    @Override
    public void updateConsultation(Consultation consultation) {
        validateConsultation(consultation);

        if (consultationRepository.findById(consultation.getId()) == null) {
            throw new ObjectNotFound("Consultation not found");
        }

        if (!isRoomAvailable(consultation)) {
            throw new TimeSlotIsNotAvailableException("Room is not available in this time");
        }

        if (!isDoctorAvailable(consultation)) {
            throw new TimeSlotIsNotAvailableException("Doctor is not available in this time");
        }
        consultationRepository.update(consultation);
    }

    @Override
    public void deleteConsultation(Consultation consultation) {
        if (consultation == null) {
            throw new ValidationException("Consultation is null");
        }

        try {
            consultationRepository.delete(consultation.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Patient> getPatients() throws ValidationException {
        if (patientRepository.findAll().isEmpty()) {
            throw new ValidationException("No Patient found");
        }

        return patientRepository.findAll();
    }

    private void validate(Doctor doctor) {
        if (doctor == null) {
            throw new ValidationException("Doctor is null");
        }

        if (doctor.getEmail() == null || doctor.getEmail().isEmpty()) {
            throw new ValidationException("doctor email is null or empty");
        }

        if (doctor.getPassword() == null || doctor.getPassword().isEmpty()) {
            throw new ValidationException("doctor password is null or empty");
        }

        if (doctor.getFirstName() == null || doctor.getFirstName().isEmpty()) {
            throw new ValidationException("doctor first name is null or empty");
        }

        if (doctor.getLastName() == null || doctor.getLastName().isEmpty()) {
            throw new ValidationException("doctor last name is null or empty");
        }

        if (doctor.getRole() == null || doctor.getRole().isEmpty()) {
            throw new ValidationException("doctor role is null or empty");
        }

        if (doctor.getPassword().length() < 4) {
            throw new ValidationException("doctor password must be at least 4 characters");
        }

    }

    private void validateRoom(Room room) {
        if (room == null) {
            throw new ValidationException("Room is null");
        }
        if (room.getName() == null || room.getName().isEmpty()) {
            throw new ValidationException("Room name is not valid");
        }

        if (room.getCapacity() < 1) {
            throw new ValidationException("Room capacity is not valid");
        }

        if (room.getCapacity() > 10) {
            throw new ValidationException("Room capacity exceeds 10");
        }
    }

    private boolean isRoomAvailable(Consultation consultation) {
        Room roomById = roomRepository.findById(consultation.getRoom().getId());
        if (roomById == null) {
            throw new ValidationException("Room not found");
        }

        return isAvailable(consultation, roomById.getConsultations());
    }

    private boolean isDoctorAvailable(Consultation consultation) {
        Doctor doctorById = doctorRepository.findById(consultation.getDoctor().getId());
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

        return consultations.stream()
                .filter(c -> c.getId() != consultation.getId())
                .noneMatch(c -> c.getDate().equals(consultation.getDate()));
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

    public boolean hasAnyAdmin() {
        return !adminRepository.findAll().isEmpty();
    }

    public void save(Admin admin) {
        adminRepository.save(admin);
    }

}
