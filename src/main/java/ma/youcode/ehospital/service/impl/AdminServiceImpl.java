package ma.youcode.ehospital.service.impl;

import ma.youcode.ehospital.exception.ObjectNotFound;
import ma.youcode.ehospital.exception.TransactionFailed;
import ma.youcode.ehospital.exception.ValidationException;
import ma.youcode.ehospital.model.Consultation;
import ma.youcode.ehospital.model.Department;
import ma.youcode.ehospital.model.Doctor;
import ma.youcode.ehospital.model.Room;
import ma.youcode.ehospital.repository.*;
import ma.youcode.ehospital.service.IAdminService;

import java.util.List;

public class AdminServiceImpl implements IAdminService {

    IDoctorRepository doctorRepository;
    IConsultationRepository consultationRepository;
    IDepartmentRepository departmentRepository;
    IRoomRepository roomRepository;

    public AdminServiceImpl(IDoctorRepository doctorRepository,
                            IConsultationRepository consultationRepository,
                            IDepartmentRepository departmentRepository,
                            IRoomRepository roomRepository) {
        this.doctorRepository = doctorRepository;
        this.consultationRepository = consultationRepository;
        this.departmentRepository = departmentRepository;
        this.roomRepository = roomRepository;
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
        } catch (TransactionFailed e) {
            System.out.println("Error in deleting Doctor " + e.getMessage());
        }
    }

    @Override
    public List<Doctor> getDoctors() throws ValidationException {
        if (doctorRepository.findAll().isEmpty()) {
            throw new ValidationException("No Doctor found");
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

        Department departmentDB = departmentRepository.findById(department.getId());

        if (departmentRepository.findByName(department.getName()) != null
                && department.getId() != departmentDB.getId()) {
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
    public void getConsultations() {
        if (consultationRepository.findAll().isEmpty()) {
            throw new ObjectNotFound("No Consultation found");
        }
        consultationRepository.findAll();
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
            throw new ValidationException("doctor password must be at least 8 characters");
        }

    }

    private void validateRoom(Room room) {
        if (room == null) {
            throw new ValidationException("Room is null");
        }
        if (room.getName() == null || room.getName().isEmpty()) {
            throw new ValidationException("Room name is not valid");
        }

        if (room.getCapacity() < 0) {
            throw new ValidationException("Room capacity is negative");
        }

        if (room.getCapacity() > 10) {
            throw new ValidationException("Room capacity exceeds 10");
        }
    }
}
