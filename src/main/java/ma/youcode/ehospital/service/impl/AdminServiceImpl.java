package ma.youcode.ehospital.service.impl;

import ma.youcode.ehospital.exception.ObjectNotFound;
import ma.youcode.ehospital.exception.ValidationException;
import ma.youcode.ehospital.model.Consultation;
import ma.youcode.ehospital.model.Department;
import ma.youcode.ehospital.model.Doctor;
import ma.youcode.ehospital.model.Room;
import ma.youcode.ehospital.repository.*;
import ma.youcode.ehospital.service.IAdminService;
import ma.youcode.ehospital.service.IDoctorService;

import java.util.List;

public class AdminServiceImpl implements IAdminService {

    IDoctorRepository doctorRepository;
    IPatientRepository patientRepository;
    IConsultationRepository consultationRepository;
    IDepartmentRepository departmentRepository;
    IRoomRepository roomRepository;

    public AdminServiceImpl(IDoctorRepository doctorRepository,
                            IPatientRepository patientRepository,
                            IConsultationRepository consultationRepository,
                            IDepartmentRepository departmentRepository,
                            IRoomRepository roomRepository) {
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.consultationRepository = consultationRepository;
        this.departmentRepository = departmentRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public void createDoctor(Doctor doctor) throws ValidationException {
        validate(doctor);
        doctorRepository.save(doctor);
    }

    @Override
    public void updateDoctor(Doctor doctor) throws ValidationException {
        validate(doctor);
        doctorRepository.update(doctor);
    }


    @Override
    public void deleteDoctor(Doctor doctor) throws ValidationException {
        if (doctor == null) {
            throw new ValidationException("Doctor is null");
        }

        doctorRepository.delete(doctor.getId());
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

        if (departmentRepository.findByName(department.getName()) != null) {
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
        return null;
    }

    @Override
    public void setDoctorToDepartment(Doctor doctor, Department department) {

    }

    @Override
    public void createRoom(Room room) {

    }

    @Override
    public void updateRoom(Room room) {

    }

    @Override
    public void deleteRoom(Room room) {

    }

    @Override
    public List<Room> getRooms() {
        return List.of();
    }

    @Override
    public Room getRoomById(int id) {
        return null;
    }

    @Override
    public void getConsultations() {

    }

    @Override
    public Consultation getConsultationById(int id) {
        return null;
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

        if (doctorRepository.findByEmail(doctor.getEmail()) != null) {
            throw new ValidationException("doctor email already exists");
        }
    }
}
