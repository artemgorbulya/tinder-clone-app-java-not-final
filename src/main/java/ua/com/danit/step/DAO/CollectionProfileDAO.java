package ua.com.danit.step.DAO;

import ua.com.danit.step.entities.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CollectionProfileDAO implements ProfileDAO{

    private List<Profile> profilesBD = new ArrayList<>();

    public CollectionProfileDAO(){
        profilesBD.add(new Profile("name1", "lastname1", "https://wl-adme.cf.tsp.li/resize/728x/jpg/72a/ea0/057bce523498aa17e4f3a9d074.jpg"));
        profilesBD.add(new Profile("name2", "lastname2","https://wl-adme.cf.tsp.li/resize/728x/jpg/089/129/0433cf5d538803257c736925ca.jpg"));
        profilesBD.add(new Profile("name3", "lastname3","https://wl-adme.cf.tsp.li/resize/728x/jpg/028/701/d930bb55f78aa958dea814a4dd.jpg"));
        profilesBD.add(new Profile("name4", "lastname4","http://files1.adme.ru/files/news/part_127/1277165/9774815-1111-650-1464360713.jpg"));
        profilesBD.add(new Profile("name5", "lastname5","https://likeyou.io/wp-content/uploads/2019/02/Snimok-ekrana-2019-02-15-v-16.03.56.png"));

    }

    @Override
    public void save(Profile profile) {
        if (profile != null) {
            if (profilesBD.contains(profile)) {
                profilesBD.set(profilesBD.indexOf(profile), profile);
            } else {
                profilesBD.add(profile);
            }
        }

    }

    @Override
    public Profile get(String id) {
        Profile result = null;
        for (int i=0; i<profilesBD.size(); i++){
         if (Objects.equals(id, profilesBD.get(i).getId())){
             result = profilesBD.get(i);
         }
        }

        return result;
    }

    @Override
    public Profile get(int id) {

        return profilesBD.get(id);
    }

    @Override
    public List<Profile> getAll() {
        return profilesBD;
    }

    @Override
    public boolean remove(String id) {
        boolean result = false;
        for (int i=0; i<profilesBD.size(); i++){
            if (Objects.equals(id, profilesBD.get(i).getId())){
                result = profilesBD.remove(profilesBD.get(i));
            }
        }
        return result;

    }

    @Override
    public boolean remove(Profile profile) {
        return profilesBD.remove(profile);
    }
}
