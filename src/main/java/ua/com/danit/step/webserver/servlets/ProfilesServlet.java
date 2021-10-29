package ua.com.danit.step.webserver.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.jetty.server.Response;
import ua.com.danit.step.controllers.ProfileController;
import ua.com.danit.step.entities.Profile;
import ua.com.danit.step.utils.FreemarkerEngine;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProfilesServlet extends HttpServlet {
  private final FreemarkerEngine fe;
  private final ProfileController profiles;
  private int countViewProfile = 0;

  public ProfilesServlet(FreemarkerEngine fe, ProfileController profiles ) {
    this.fe = fe;
    this.profiles = profiles;
  }

  public static void renderProfile(Profile profile, FreemarkerEngine fe, HttpServletResponse resp){
    HashMap<String, Object> data = new HashMap<>();
    data.put("app_error", "");
    data.put("avatar", profile.getAvatar());
    data.put("username", profile.getFullname());
    fe.render("like-page.ftl", data, resp);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    if (countViewProfile > (profiles.getAll().size()-1)) {
      resp.sendRedirect("/likes");
      countViewProfile=0;
    } else {
      Profile profile = profiles.get(countViewProfile);
      renderProfile(profile, fe, resp);
    }

  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

    ObjectMapper mapper = new ObjectMapper();
    Map<String, String> requestBody = mapper.readValue(req.getInputStream(), Map.class);
    String actionType = requestBody.get("type");

    if(actionType.equals("like")) {
      profiles.get(countViewProfile).setisLiked(true);

    } else if (actionType.equals("dislike")){
      profiles.get(countViewProfile).setisLiked(false);
    }

    countViewProfile++;

    for (Profile profile : profiles.getAll()) {
      System.out.println(profile);
    }

  }
}
