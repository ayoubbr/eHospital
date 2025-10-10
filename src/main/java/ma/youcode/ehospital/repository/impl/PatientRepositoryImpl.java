package ma.youcode.ehospital.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import ma.youcode.ehospital.model.Doctor;
import ma.youcode.ehospital.model.Patient;
import ma.youcode.ehospital.repository.IPatientRepository;
import ma.youcode.ehospital.util.JPAUtil;

import java.util.List;

public class PatientRepositoryImpl implements IPatientRepository {
    @Override
    public Patient save(Patient patient) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(patient);
        transaction.commit();
        em.close();
        return patient;
    }

    @Override
    public Patient update(Patient patient) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.merge(patient);
        transaction.commit();
        em.close();
        return patient;
    }

    @Override
    public void delete(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Patient patient = em.find(Patient.class, id);
        em.remove(patient);
        transaction.commit();
        em.close();
    }

    @Override
    public List<Patient> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        List<Patient> patients = em.createQuery("from Patient").getResultList();
        em.close();
        return patients;
    }

    @Override
    public Patient findById(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        Patient patient = em.find(Patient.class, id);
        em.close();
        return patient;
    }

    @Override
    public List<Patient> findByDoctor(Doctor doctor) {
        EntityManager em = JPAUtil.getEntityManager();
        String jpql = "SELECT DISTINCT p FROM Consultation c " +
                "JOIN c.patient p " +
                "JOIN FETCH p.consultations " +
                "WHERE c.doctor = :doctor";

        TypedQuery<Patient> query = em.createQuery(jpql, Patient.class);
        query.setParameter("doctor", doctor);
        List<Patient> resultList = query.getResultList();
        em.close();
        return resultList;
    }

}
