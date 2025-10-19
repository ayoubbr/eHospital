package ma.youcode.ehospital.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import ma.youcode.ehospital.model.Person;
import ma.youcode.ehospital.repository.IPersonRepository;
import ma.youcode.ehospital.util.JPAUtil;

public class PersonRepositoryImpl implements IPersonRepository {

    @Override
    public Person save(Person person) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(person);
        tx.commit();
        em.close();
        return person;
    }

    @Override
    public Person findByEmail(String email) {
        EntityManager em = JPAUtil.getEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p WHERE p.email = :email", Person.class);
        query.setParameter("email", email);
        Person person = query.getResultStream().findFirst().orElse(null);
        em.close();
        return person;
    }

    @Override
    public Person findByEmailAndPassword(String email, String password) {
        EntityManager em = JPAUtil.getEntityManager();
        TypedQuery<Person> query = em.createQuery(
                "SELECT p FROM Person p WHERE p.email = :email AND p.password = :password",
                Person.class
        );
        query.setParameter("email", email);
        query.setParameter("password", password);
        Person person = query.getResultStream().findFirst().orElse(null);
        em.close();
        return person;
    }
}
