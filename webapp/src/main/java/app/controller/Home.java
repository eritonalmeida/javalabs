package app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Home extends AbstractController {

    @GetMapping("/")
    public ModelAndView index() {

        view.setViewName("home.html");

        return view;
    }
}
