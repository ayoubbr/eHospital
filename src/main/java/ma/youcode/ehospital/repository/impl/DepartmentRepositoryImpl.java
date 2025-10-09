package ma.youcode.ehospital.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import ma.youcode.ehospital.model.Department;
import ma.youcode.ehospital.repository.IDepartmentRepository;

import java.util.List;

public class DepartmentRepositoryImpl implements IDepartmentRepository {
    @Override
    public Department save(Department department) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(department);
        em.getTransaction().commit();
        em.close();
        emf.close();
        return department;
    }

    @Override
    public Department update(Department department) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(department);
        em.getTransaction().commit();
        em.close();
        emf.close();
        return department;
    }

    @Override
    public void delete(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Department department = em.find(Department.class, id);
        em.remove(department);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    @Override
    public List<Department> findAll() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Department> departments = em.createQuery("from Department", Department.class).getResultList();
        em.getTransaction().commit();
        em.close();
        emf.close();
        return departments;
    }

    @Override
    public Department findById(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager em = emf.createEntityManager();
        Department department = em.find(Department.class, id);
        em.close();
        emf.close();
        return department;
    }
}
