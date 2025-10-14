package ma.youcode.ehospital.repository;


import ma.youcode.ehospital.model.Room;

import java.util.List;

public interface IRoomRepository {

    Room save(Room room);

    Room update(Room room);

    void delete(int id);

    List<Room> findAll();

    Room findById(int id);

    Room findByName(String name);
}
