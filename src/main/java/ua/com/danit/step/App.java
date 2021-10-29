package ua.com.danit.step;

import ua.com.danit.step.webserver.Webserver;

public class App {
  public static void main(String[] args) throws Exception {
    new Webserver(8081,"src/main/resources/web-content/templates").start();
  }
}
