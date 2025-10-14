package ma.youcode.ehospital.service.impl;

import ma.youcode.ehospital.exception.ValidationException;
import ma.youcode.ehospital.model.Doctor;
import ma.youcode.ehospital.model.Person;
import ma.youcode.ehospital.repository.IPersonRepository;
import ma.youcode.ehospital.service.IAuthService;

public class AuthServiceImpl implements IAuthService {

    IPersonRepository personRepository;

    public AuthServiceImpl(IPersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void register(Person person) {
        if (person.getEmail().equals("admin") && person.getPassword().equals("admin")) {
            throw new ValidationException("Invalid email or password");
        }

        if (person.getEmail().isEmpty() || person.getPassword().isEmpty()) {
            throw new ValidationException("Empty email or password");
        }

        if (person.getEmail().length() < 4 || person.getEmail().length() > 16) {
            throw new ValidationException("Email too short or too long");
        }

        if (person.getPassword().length() < 4 || person.getPassword().length() > 16) {
            throw new ValidationException("Password too short or too long");
        }

        Person byEmail = personRepository.findByEmail(person.getEmail());
        if (byEmail != null) {
            throw new ValidationException("Email already exists");
        }

        personRepository.save(person);
    }

    @Override
    public Person login(Person person) {
        if (person.getEmail().isEmpty() || person.getPassword().isEmpty()) {
            throw new ValidationException("Empty email or password");
        }

        Person person2 = personRepository.findByEmailAndPassword(person.getEmail(), person.getPassword());

        if (person2 == null) {
            throw new ValidationException("Invalid email or password");
        }

        return person;
    }

    @Override
    public void logout() {
        // TO DO LATER
    }
}
