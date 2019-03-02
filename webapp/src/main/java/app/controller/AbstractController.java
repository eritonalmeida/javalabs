package app.controller;

import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
public abstract class AbstractController {

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpServletResponse response;

    @Autowired
    protected HttpSession session;

    protected ModelAndView view;

    public AbstractController() {
        view = new ModelAndView();
    }

    public String jsonOk(String message) {
        JsonObject object = Json.createObjectBuilder()
                .add("error", false)
                .add("message", message)
                .build();

        return object.toString();
    }

    public String jsonError(String message) {
        JsonObject object = Json.createObjectBuilder()
                .add("error", true)
                .add("message", message)
                .build();

        return object.toString();
    }
}
