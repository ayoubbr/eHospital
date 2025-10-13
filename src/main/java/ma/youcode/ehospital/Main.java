package ma.youcode.ehospital;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import ma.youcode.ehospital.exception.ValidationException;
import ma.youcode.ehospital.model.*;
import ma.youcode.ehospital.repository.*;
import ma.youcode.ehospital.repository.impl.*;
import ma.youcode.ehospital.service.IAdminService;
import ma.youcode.ehospital.service.impl.AdminServiceImpl;

import javax.print.Doc;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        IDoctorRepository doctorRepository = new DoctorRepositoryImpl();
        IPatientRepository patientRepository = new PatientRepositoryImpl();
        IConsultationRepository consultationRepository = new ConsultationRepositoryImpl();
        IDepartmentRepository departmentRepository = new DepartmentRepositoryImpl();
        IRoomRepository roomRepository = new RoomRepositoryImpl();


        IAdminService adminService = new AdminServiceImpl(doctorRepository,
                patientRepository,
                consultationRepository,
                departmentRepository,
                roomRepository);

        Department department = departmentRepository.findById(1);
        Doctor doctor = doctorRepository.findById(3);


//        doctor.setEmail("doctor2@email.com");
//        doctor.setPassword("pass");
//        doctor.setFirstName("Doctor");
//        doctor.setLastName("Doctor");
//        doctor.setSpecialty("Eyes");
//        doctor.setRole("Doctor");
//        doctor.setDepartment(department);
//
//        try {
//            adminService.createDoctor(doctor);
//        } catch (ValidationException e) {
//            System.out.println("Error: " + e.getMessage());
//        }
//        try {
//            adminService.deleteDoctor(doctor);
//        } catch (ValidationException e) {
//            System.out.println("Error: " + e.getMessage());
//        }
        List<Doctor> doctors = adminService.getDoctors();
        System.out.println(doctors);

    }
}
