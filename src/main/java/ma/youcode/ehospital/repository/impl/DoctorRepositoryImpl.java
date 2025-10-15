package ma.youcode.ehospital.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import ma.youcode.ehospital.exception.TransactionFailed;
import ma.youcode.ehospital.model.Department;
import ma.youcode.ehospital.model.Doctor;
import ma.youcode.ehospital.repository.IDoctorRepository;
import ma.youcode.ehospital.util.JPAUtil;

import java.util.List;

public class DoctorRepositoryImpl implements IDoctorRepository {
    @Override
    public Doctor save(Doctor doctor) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(doctor);
        em.getTransaction().commit();
        em.close();
        return doctor;
    }

    @Override
    public Doctor update(Doctor doctor) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        doctor = em.merge(doctor);
        em.getTransaction().commit();
        em.close();
        return doctor;
    }

    @Override
    public void delete(int id) throws TransactionFailed {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            Doctor doctor = em.find(Doctor.class, id);
            em.remove(doctor);
            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new TransactionFailed("Transaction failed");
        } finally {
            em.close();
        }
    }

    @Override
    public List<Doctor> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        List<Doctor> doctors = em.createQuery("from Doctor").getResultList();
        em.close();
        return doctors;
    }

    @Override
    public Doctor findById(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        Doctor doctor = em.find(Doctor.class, id);
        em.close();
        return doctor;
    }

    @Override
    public Doctor findByEmail(String email) {
        EntityManager em = JPAUtil.getEntityManager();
        Doctor doctor;
        Query query = em.createQuery("from Doctor where email = :email");
        query.setParameter("email", email);
        try {
            doctor = (Doctor) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
        return doctor;
    }

    @Override
    public List<Doctor> findDoctorsByDepartment(Department department) {
        EntityManager em = JPAUtil.getEntityManager();
        Query query = em.createQuery("from Doctor where department = :department");
        query.setParameter("department", department);
        List<Doctor> doctors = query.getResultList();
        em.close();
        return doctors;
    }
}
