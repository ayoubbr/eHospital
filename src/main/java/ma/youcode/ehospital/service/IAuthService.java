package ma.youcode.ehospital.service;

import ma.youcode.ehospital.model.Person;

public interface IAuthService {

    void register(Person person);

    Person login(Person person);

    void logout();
}
