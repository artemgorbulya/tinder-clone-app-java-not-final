package ua.com.danit.step.webserver.servlets;


import ua.com.danit.step.controllers.ProfileController;
import ua.com.danit.step.entities.Profile;
import ua.com.danit.step.utils.FreemarkerEngine;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class LikesListServlet extends HttpServlet {
  private final FreemarkerEngine fe;
  private final ProfileController profiles;

  public LikesListServlet(FreemarkerEngine fe, ProfileController profiles ) {
    this.fe = fe;
    this.profiles = profiles;
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HashMap<String, Object> data = new HashMap<>();
    List<Profile> likedprofiles = new ArrayList<>();

    for (Profile profile : profiles.getAll()) {
      if (profile.getisLiked()) {
        likedprofiles.add(profile);
      }
    }

    data.put("profiles", likedprofiles);

    fe.render("people-list.ftl", data, resp);
  }
}
