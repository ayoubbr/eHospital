package ma.youcode.ehospital.service;

import ma.youcode.ehospital.model.*;

import java.util.List;

public interface IAdminService {

    void createDoctor(Doctor doctor);

    void updateDoctor(Doctor doctor);

    void deleteDoctor(Doctor doctor);

    List<Doctor> getDoctors();

    Doctor getDoctorById(int id);

    Patient getPatientById(int id);

    void createDepartment(Department department);

    void updateDepartment(Department department);

    void deleteDepartment(Department department);

    List<Department> getDepartments();

    Department getDepartmentById(int id);

    void setDoctorToDepartment(Doctor doctor, Department department);

    void createRoom(Room room);

    void updateRoom(Room room);

    void deleteRoom(Room room);

    List<Room> getRooms();

    Room getRoomById(int id);

    List<Consultation> getConsultations();

    Consultation getConsultationById(int id);

    void createConsultation(Consultation consultation);

    void updateConsultation(Consultation consultation);

    void deleteConsultation(Consultation consultation);

    List<Patient> getPatients();
}

