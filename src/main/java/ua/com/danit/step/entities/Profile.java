package ua.com.danit.step.entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Objects;
import java.util.UUID;

public class Profile {
    private final String id;
    private final String name;
    private final String lastname;
    private final String fullname;
    private final String avatar;
    private final String lastLogin;
    private boolean isLiked;

    public Profile(String name, String lastname, String avatar) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.lastname = lastname;
        this.fullname = name + " " + lastname;
        this.avatar = avatar;
        this.lastLogin = LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
        this.isLiked = false;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFullname() {
        return fullname;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public boolean getisLiked() {
        return isLiked;
    }

    public void setisLiked(boolean liked) {
        isLiked = liked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile = (Profile) o;
        return isLiked == profile.isLiked && id.equals(profile.id) && name.equals(profile.name) && lastname.equals(profile.lastname) && fullname.equals(profile.fullname) && avatar.equals(profile.avatar) && Objects.equals(lastLogin, profile.lastLogin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastname, fullname, avatar, lastLogin, isLiked);
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", fullname='" + fullname + '\'' +
                ", avatar='" + avatar + '\'' +
                ", lastLogin='" + lastLogin + '\'' +
                ", isLiked=" + isLiked +
                '}';
    }
}
