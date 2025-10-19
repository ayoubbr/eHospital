package ma.youcode.ehospital.repository;

import ma.youcode.ehospital.model.Admin;

import java.util.List;

public interface IAdminRepository {

    List<Admin> findAll();
    Admin save(Admin admin);
}
