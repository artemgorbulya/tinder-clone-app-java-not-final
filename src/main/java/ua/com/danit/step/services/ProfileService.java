package ua.com.danit.step.services;

import ua.com.danit.step.DAO.CollectionProfileDAO;
import ua.com.danit.step.entities.Profile;
import java.util.List;

public class ProfileService {

    private final CollectionProfileDAO collectionprofiledao;

    public ProfileService (CollectionProfileDAO collectionprofiledao) {
        this.collectionprofiledao = collectionprofiledao;
    }

    public void save(Profile profile) {
        collectionprofiledao.save(profile);
    }

    public Profile get(String id) {
        return collectionprofiledao.get(id);
    }

    public Profile get(int id) {
        return collectionprofiledao.get(id);
    }

    public List<Profile> getAll() {
        return collectionprofiledao.getAll();
    }

    public boolean remove(Profile profile) {
        return collectionprofiledao.remove(profile);
    }

    public boolean remove(String id) {
        return collectionprofiledao.remove(id);
    }

}



