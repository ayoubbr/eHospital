package ma.youcode.ehospital.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import ma.youcode.ehospital.model.Doctor;
import ma.youcode.ehospital.model.Room;
import ma.youcode.ehospital.repository.IRoomRepository;
import ma.youcode.ehospital.util.JPAUtil;

import java.util.List;

public class RoomRepositoryImpl implements IRoomRepository {
    @Override
    public Room save(Room room) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(room);
        em.getTransaction().commit();
        return room;
    }

    @Override
    public Room update(Room room) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.merge(room);
        em.getTransaction().commit();
        return room;
    }

    @Override
    public void delete(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        Room room = em.find(Room.class, id);
        em.getTransaction().begin();
        em.remove(room);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<Room> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        List<Room> rooms = em.createQuery("from Room", Room.class).getResultList();
        em.close();
        return rooms;
    }

    @Override
    public Room findById(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        Room room = em.find(Room.class, id);
        em.close();
        return room;
    }

    @Override
    public Room findByName(String name) {
        EntityManager em = JPAUtil.getEntityManager();
        Query query = em.createQuery("from Room where name = :name");
        query.setParameter("name", name);
        Room room = null;
        try {
            room = (Room) query.getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        em.close();
        return room;
    }
}
