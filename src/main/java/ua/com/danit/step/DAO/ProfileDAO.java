package ua.com.danit.step.DAO;

import ua.com.danit.step.entities.Profile;

import java.util.List;

public interface ProfileDAO {

    void save(Profile profile);
    Profile get(String id);
    Profile get(int id);
    List<Profile> getAll();
    boolean remove(String id);
    boolean remove(Profile profile);

}
