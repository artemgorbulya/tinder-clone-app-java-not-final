package ua.com.danit.step.webserver.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import ua.com.danit.step.controllers.ProfileController;
import ua.com.danit.step.utils.FreemarkerEngine;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ChatServlet extends HttpServlet {

    private final FreemarkerEngine fe;
    private final ProfileController profiles;

    public ChatServlet(FreemarkerEngine fe, ProfileController profiles ) {
        this.fe = fe;
        this.profiles = profiles;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<String, Object> data = new HashMap<>();
        HashMap<String, Object> chat = new HashMap<>();

       boolean emptyChat = chat.size() < 1;

       chat.put("message", "test1");
        chat.put("message", "test2");

        String profileID = req.getPathInfo().replace("/", "");

        data.put("profileName", profiles.get(0).getFullname());
        data.put("emptyChat", emptyChat);
        data.put("emptyChatMessage", "No messages yet");
        data.put("chat", chat);

        fe.render("chat.ftl", data, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> requestBody = mapper.readValue(req.getInputStream(), Map.class);
        String message = requestBody.get("text");
        System.out.println(message);
    }

}
