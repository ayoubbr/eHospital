package ma.youcode.ehospital.service.impl;

import ma.youcode.ehospital.exception.ValidationException;
import ma.youcode.ehospital.model.Doctor;
import ma.youcode.ehospital.model.Person;
import ma.youcode.ehospital.repository.IPersonRepository;
import ma.youcode.ehospital.service.IAuthService;

public class AuthServiceImpl implements IAuthService {

    private final IPersonRepository personRepository;

    public AuthServiceImpl(IPersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void register(Person person) {
        if (person.getEmail() == null || person.getPassword() == null ||
                person.getEmail().isEmpty() || person.getPassword().isEmpty()) {
            throw new ValidationException("Email or password cannot be empty");
        }

        if (person.getFirstName() == null || person.getLastName() == null ||
                person.getFirstName().isEmpty() || person.getLastName().isEmpty()) {
            throw new ValidationException("Firstname or lastname cannot be empty");
        }

        if (person.getEmail().equals("admin") && person.getPassword().equals("admin")) {
            throw new ValidationException("Invalid email or password");
        }

        if (person.getPassword().length() < 4 || person.getPassword().length() > 16) {
            throw new ValidationException("Password must be between 4 and 16 characters");
        }

        if (personRepository.findByEmail(person.getEmail()) != null) {
            throw new ValidationException("Email already exists");
        }

        personRepository.save(person);
    }

    @Override
    public Person login(Person person) {
        if (person.getEmail().isEmpty() || person.getPassword().isEmpty()) {
            throw new ValidationException("Empty email or password");
        }

        Person foundPerson = personRepository.findByEmailAndPassword(person.getEmail(), person.getPassword());

        if (foundPerson == null) {
            throw new ValidationException("Invalid email or password");
        }

        return foundPerson;
    }

    @Override
    public void logout() {
        // TO DO LATER
    }


}
