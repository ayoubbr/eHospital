package ma.youcode.ehospital;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import ma.youcode.ehospital.model.*;
import ma.youcode.ehospital.repository.IDoctorRepository;
import ma.youcode.ehospital.repository.impl.*;

import javax.print.Doc;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Main {

    public static void main(String[] args) {

//        DepartmentRepositoryImpl departmentRepository = new DepartmentRepositoryImpl();
//        Department department = new Department();
//        department.setName("Department 1");
//        departmentRepository.save(department);
////
//        RoomRepositoryImpl roomRepository = new RoomRepositoryImpl();
//        Room room = new Room();
//        room.setName("Room 1");
//        room.setCapacity(10);
//        roomRepository.save(room);
////        Room room = roomRepository.findById(1);
////
//        PatientRepositoryImpl patientRepository = new PatientRepositoryImpl();
//        Patient patient = new Patient();
//        patient.setFirstName("John");
//        patient.setLastName("Doe");
//        patient.setEmail("email1");
//        patient.setPassword("password");
//        patient.setHeight(1.22f);
//        patient.setWeight(100.5f);
//        patient.setRole("Patient");
//        patientRepository.save(patient);
////
//        IDoctorRepository doctorRepository = new DoctorRepositoryImpl();
//        Doctor doctor = new Doctor();
//        doctor.setFirstName("Mr");
//        doctor.setLastName("Doctor");
//        doctor.setEmail("email2");
//        doctor.setPassword("password");
//        doctor.setSpecialty("Specialty");
//        doctor.setRole("Doctor");
//        doctor.setDepartment(department);
//        doctorRepository.save(doctor);
//
        ConsultationRepositoryImpl consultationRepository = new ConsultationRepositoryImpl();
//        Consultation consultation = consultationRepository.findById(1);
//        Consultation consultation = new Consultation();
//        consultation.setDate(LocalDate.parse("2022-10-10"));
//        consultation.setHour(LocalTime.now());
//        consultation.setReport("report");
//        consultation.setStatusByName(Status.RESERVED);
//        consultation.setDoctor(doctor);
//        consultation.setPatient(patient);

//        consultation.setRoom(room);
//        consultationRepository.update(consultation);
//        consultationRepository.save(consultation);

//        consultationRepository.findAll();
        System.out.println(consultationRepository.findAll());

        RoomRepositoryImpl roomRepository = new RoomRepositoryImpl();
        List<Room> rooms = roomRepository.findAll();
        System.out.println(rooms);


    }
}
