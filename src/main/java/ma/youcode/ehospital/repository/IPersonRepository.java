package ma.youcode.ehospital.repository;

import ma.youcode.ehospital.model.Person;

import java.util.List;

public interface IPersonRepository {

    Person save(Person person);

    Person update(Person person);

    void delete(int id);

    List<Person> findAll();

    Person findByEmailAndPassword(String email, String password);

    Person findByEmail(String email);

    Person findById(int id);
}
