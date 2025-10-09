package ma.youcode.ehospital.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import ma.youcode.ehospital.model.Department;
import ma.youcode.ehospital.repository.IDepartmentRepository;
import ma.youcode.ehospital.util.JPAUtil;

import java.util.List;

public class DepartmentRepositoryImpl implements IDepartmentRepository {
    @Override
    public Department save(Department department) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(department);
        transaction.commit();
        em.close();
        return department;
    }

    @Override
    public Department update(Department department) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.merge(department);
        transaction.commit();
        em.close();
        return department;
    }

    @Override
    public void delete(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Department department = em.find(Department.class, id);
        em.remove(department);
        transaction.commit();
        em.close();
    }

    @Override
    public List<Department> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        List<Department> departments = em.createQuery("from Department", Department.class).getResultList();
        transaction.commit();
        em.close();
        return departments;
    }

    @Override
    public Department findById(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        Department department = em.find(Department.class, id);
        em.close();
        return department;
    }
}
