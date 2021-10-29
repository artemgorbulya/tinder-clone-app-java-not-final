package ua.com.danit.step.webserver;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ua.com.danit.step.DAO.CollectionProfileDAO;
import ua.com.danit.step.controllers.ProfileController;
import ua.com.danit.step.services.ProfileService;
import ua.com.danit.step.utils.FreemarkerEngine;
import ua.com.danit.step.webserver.servlets.*;

public class Webserver {
  private final int port;
  private final String templatesDirectory;

  public Webserver(int port, String templatesDirectory) {
    this.port = port;
    this.templatesDirectory = templatesDirectory;
  }

  public void start() throws Exception {
    Server srv= new Server(port);

    FreemarkerEngine cfg = FreemarkerEngine.directory(templatesDirectory);
    ProfileController pc = new ProfileController(new ProfileService(new CollectionProfileDAO()));

    ServletContextHandler handler = new ServletContextHandler();
    handler.addServlet(RootServlet.class, "/");

    handler.addServlet(new ServletHolder(new ProfilesServlet(cfg, pc)),"/users");
    handler.addServlet(new ServletHolder(new LikesListServlet(cfg, pc)),"/likes");
    handler.addServlet(new ServletHolder(new ChatServlet(cfg, pc)),"/messages/*");

    srv.setHandler(handler);
    srv.start();
    srv.join();
  }
}
