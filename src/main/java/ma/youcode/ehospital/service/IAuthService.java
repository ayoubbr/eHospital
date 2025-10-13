package ma.youcode.ehospital.service;

public interface IAuthService {

    void register(String username, String password);

    void login(String username, String password);

    void logout();
}
