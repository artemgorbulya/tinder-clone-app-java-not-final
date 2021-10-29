package ua.com.danit.step.controllers;

import ua.com.danit.step.entities.Profile;
import ua.com.danit.step.services.ProfileService;

import java.util.List;

public class ProfileController {

    private final ProfileService proficeservice;

    public ProfileController(ProfileService proficeservice){
        this.proficeservice = proficeservice;
    }

    public void save(Profile profile) {
        proficeservice.save(profile);
    }

    public Profile get(String id) {
        return proficeservice.get(id);
    }

    public Profile get(int id) {
        return proficeservice.get(id);
    }

    public List<Profile> getAll() {
        return proficeservice.getAll();
    }

    public boolean remove(Profile profile) {
        return proficeservice.remove(profile);
    }

    public boolean remove(String id) {
        return proficeservice.remove(id);
    }
}
