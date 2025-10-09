package ma.youcode.ehospital.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import ma.youcode.ehospital.model.Consultation;
import ma.youcode.ehospital.model.Doctor;
import ma.youcode.ehospital.model.Patient;
import ma.youcode.ehospital.repository.IConsultationRepository;
import ma.youcode.ehospital.util.JPAUtil;

import java.util.List;

public class ConsultationRepositoryImpl implements IConsultationRepository {
    @Override
    public Consultation save(Consultation consultation) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(consultation);
        em.getTransaction().commit();
        em.close();
        return consultation;
    }

    @Override
    public Consultation update(Consultation consultation) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        consultation = em.merge(consultation);
        em.getTransaction().commit();
        em.close();
        return consultation;
    }

    @Override
    public void delete(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        Consultation consultation = em.find(Consultation.class, id);
        em.getTransaction().begin();
        em.remove(consultation);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<Consultation> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        List<Consultation> consultations = em.createQuery("from Consultation").getResultList();
        em.close();
        return consultations;
    }

    @Override
    public Consultation findById(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        Consultation consultation = em.find(Consultation.class, id);
        em.close();
        return consultation;
    }

    @Override
    public List<Consultation> findByPatient(Patient patient) {
        EntityManager em = JPAUtil.getEntityManager();
        Query query = em.createQuery("from Consultation where patient.id = :patientId");
        query.setParameter("patientId", patient.getId());
        List<Consultation> consultations = query.getResultList();
        em.close();
        return consultations;
    }

    @Override
    public List<Consultation> findByDoctor(Doctor doctor) {
        EntityManager em = JPAUtil.getEntityManager();
        Query query = em.createQuery("from Consultation where doctor.id = :doctorId");
        query.setParameter("doctorId", doctor.getId());
        List<Consultation> consultations = query.getResultList();
        em.close();
        return consultations;
    }
}
