package ma.youcode.ehospital.repository;

import ma.youcode.ehospital.model.Department;
import ma.youcode.ehospital.model.Doctor;

import java.util.List;

public interface IDoctorRepository {
    Doctor save(Doctor doctor);

    Doctor update(Doctor doctor);

    void delete(Doctor doctor);

    List<Doctor> findAll();

    Doctor findById(int id);

    List<Doctor> findDoctorsByDepartment(Department department);
}
