package ma.youcode.ehospital;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import ma.youcode.ehospital.exception.ObjectNotFound;
import ma.youcode.ehospital.exception.ValidationException;
import ma.youcode.ehospital.model.*;
import ma.youcode.ehospital.repository.*;
import ma.youcode.ehospital.repository.impl.*;
import ma.youcode.ehospital.service.IAdminService;
import ma.youcode.ehospital.service.IDoctorService;
import ma.youcode.ehospital.service.IPatientService;
import ma.youcode.ehospital.service.impl.AdminServiceImpl;
import ma.youcode.ehospital.service.impl.DoctorServiceImpl;
import ma.youcode.ehospital.service.impl.PatientServiceImpl;
import ma.youcode.ehospital.util.JPAUtil;

import javax.print.Doc;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        IDoctorRepository doctorRepository = new DoctorRepositoryImpl();
        IPatientRepository patientRepository = new PatientRepositoryImpl();
        IConsultationRepository consultationRepository = new ConsultationRepositoryImpl();
        IDepartmentRepository departmentRepository = new DepartmentRepositoryImpl();
        IRoomRepository roomRepository = new RoomRepositoryImpl();


        IAdminService adminService = new AdminServiceImpl(doctorRepository, consultationRepository,
                departmentRepository, roomRepository);

        IPatientService patientService = new PatientServiceImpl(consultationRepository,
                doctorRepository, roomRepository);

        IDoctorService doctorService = new DoctorServiceImpl(consultationRepository, doctorRepository);


//        // START TEST
//        Room room = new Room();
//        room.setName("Room 1");
//        room.setCapacity(5);
//        adminService.createRoom(room);
//        Department department = new Department();
//        department.setName("Department 1");
//        adminService.createDepartment(department);
//        Doctor doctor = new Doctor();
//        doctor.setFirstName("Doctor 1");
//        doctor.setLastName("Doctor 2");
//        doctor.setEmail("doctor1@email.com");
//        doctor.setPassword("password");
//        doctor.setRole("Doctor");
//        doctor.setSpecialty("Doctor Specialty");
//        doctor.setDepartment(department);
//        adminService.createDoctor(doctor);
//        Patient patient = new Patient();
//        patient.setFirstName("Patient 1");
//        patient.setLastName("Patient 2");
//        patient.setEmail("patient1@gmail.com");
//        patient.setPassword("password");
//        patient.setRole("Patient");
//        patient.setHeight(1.88f);
//        patient.setWeight(77f);
//        patientRepository.save(patient);
//        Consultation consultation = new Consultation();
//        consultation.setDate(LocalDateTime.of(2025, 10, 13, 15, 0, 0));
//        consultation.setReport("Consultation 1 report");
//        consultation.setStatus(Status.RESERVED);
//        consultation.setDoctor(doctor);
//        consultation.setPatient(patient);
//        consultation.setRoom(room);
//        consultationRepository.save(consultation);


        // START TEST 2
//        Room room = new Room();
//        room.setName("Room 2");
//        room.setCapacity(9);
//        adminService.createRoom(room);
//        Department department = new Department();
//        department.setName("Department 2");
//        adminService.createDepartment(department);
//        Doctor doctor = new Doctor();
//        doctor.setFirstName("Doctor 2");
//        doctor.setLastName("Doctor 2");
//        doctor.setEmail("doctor2@email.com");
//        doctor.setPassword("password");
//        doctor.setRole("Doctor");
//        doctor.setSpecialty("Doctor Specialty");
//        doctor.setDepartment(department);
//        adminService.createDoctor(doctor);
//        Patient patient = new Patient();
//        patient.setFirstName("Patient 2");
//        patient.setLastName("Patient 2");
//        patient.setEmail("patient2@gmail.com");
//        patient.setPassword("password");
//        patient.setRole("Patient");
//        patient.setHeight(1.88f);
//        patient.setWeight(77f);
//        patientRepository.save(patient);
//        Consultation consultation = new Consultation();
//        consultation.setDate(LocalDateTime.of(2025, 10, 13, 15, 30));
//        consultation.setReport("Consultation 2 report");
//        consultation.setStatus(Status.RESERVED);
//        consultation.setDoctor(doctor);
//        consultation.setPatient(patient);
//        consultation.setRoom(room);
//        consultationRepository.save(consultation);

        // TEST 3
//        Consultation consultation = new Consultation();
//        Room room = roomRepository.findById(3);
//        Patient patient = patientRepository.findById(2);
//        Consultation consultation = consultationRepository.findById(1);
//        consultation.setDate(LocalDateTime.of(2025, 10, 13, 11, 0));
//        consultation.setReport("Consultation 7 report");
//        consultation.setStatus(Status.RESERVED);
//        consultation.setDoctor(doctor);
//        consultation.setPatient(patient);
//        consultation.setRoom(room);
//        patientService.updateConsultation(consultation);

//        Room room = new Room();
//        room.setCapacity(10);
//        room.setName("Room 3");
//        roomRepository.save(room);

//        List<Consultation> consultations = doctorService.getConsultations(doctor);
//
//        consultations.forEach(consultation -> {
//        System.out.println(consultation.getDoctor().getFirstName());
//            System.out.println(consultation.getId());
//        });

//        Consultation byId = consultationRepository.findById(4);
//
//        byId.setStatus(Status.ANNULLED);
//        doctorService.updateConsultation(byId, doctor);


        Doctor doctor = doctorRepository.findById(1);
//        Doctor doctor = new Doctor();

        doctor.setFirstName("Doctor 1");
        doctor.setLastName("Doctor 2");
        doctor.setEmail("doctor1@email.com");
        doctor.setPassword("password");
        doctor.setRole("Doctor");
        doctor.setSpecialty("Doctor Specialty");

        try {
            adminService.deleteDoctor(doctor);
        } catch (ValidationException | ObjectNotFound e) {
            System.out.println("Error: " + e.getMessage());
        }
//        adminService.deleteDoctor(doctor);
//        adminService.updateDoctor(doctor);
//        adminService.getDoctors();
//        adminService.getDoctorById(1);
//        adminService.setDoctorToDepartment(doctor, departmentRepository.findById(1));


    }
}
