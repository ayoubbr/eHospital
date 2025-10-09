package ma.youcode.ehospital.repository;

import ma.youcode.ehospital.model.Department;

import java.util.List;

public interface IDepartmentRepository {
    Department save(Department department);

    Department update(Department department);

    void delete(Department department);

    List<Department> findAll();

    Department findById(int id);
}
