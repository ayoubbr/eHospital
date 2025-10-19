package ma.youcode.ehospital.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import ma.youcode.ehospital.model.Admin;
import ma.youcode.ehospital.repository.IAdminRepository;
import ma.youcode.ehospital.util.JPAUtil;

import java.util.List;

public class AdminRepositoryImpl implements IAdminRepository {
    @Override
    public List<Admin> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        List<Admin> admins = em.createQuery("from Admin").getResultList();
        em.close();
        return admins;
    }

    @Override
    public Admin save(Admin admin) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(admin);
        transaction.commit();
        em.close();
        return admin;
    }
}
