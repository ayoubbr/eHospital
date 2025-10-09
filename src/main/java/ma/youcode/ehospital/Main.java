package ma.youcode.ehospital;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import ma.youcode.ehospital.model.Department;
import ma.youcode.ehospital.model.Doctor;
import ma.youcode.ehospital.model.Patient;
import ma.youcode.ehospital.model.Status;
import ma.youcode.ehospital.repository.impl.DepartmentRepositoryImpl;
import ma.youcode.ehospital.repository.impl.DoctorRepositoryImpl;
import ma.youcode.ehospital.repository.impl.PatientRepositoryImpl;

import javax.print.Doc;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        DepartmentRepositoryImpl departmentRepository = new DepartmentRepositoryImpl();
//        Department department = new Department();
//        department.setName("Department 1");
//        departmentRepository.save(department);
        Department department = departmentRepository.findById(1);

        DoctorRepositoryImpl doctorRepository = new DoctorRepositoryImpl();
        Doctor doctor = doctorRepository.findById(1);
//        Doctor doctor = new Doctor();
//        doctor.setFirstName("Doctor");
//        doctor.setLastName("Doctor");
//        doctor.setEmail("doctor");
//        doctor.setPassword("doctor");
//        doctor.setRole("Doctor");
//        doctor.setSpecialty("Doctor");
//        doctor.setDepartment(department);
//        doctorRepository.save(doctor);


        PatientRepositoryImpl patientRepository = new PatientRepositoryImpl();
//        List<Patient> byDoctor = patientRepository.findByDoctor(doctor);
//        System.out.println(byDoctor);
//        Patient patient = new Patient();
//        patient.setFirstName("Ayoub");
//        patient.setLastName("Ben");
//        patient.setEmail("ben@gmail.com");
//        patient.setPassword("password");
//        patient.setRole("Patient");
//        patient.setHeight(1.88f);
//        patient.setWeight(68.0f);
//        patientRepository.save(patient);
//        Doctor doctor = ;
        List<Patient> byDoctor = patientRepository.findByDoctor(doctor);
        System.out.println(byDoctor);

    }
}
