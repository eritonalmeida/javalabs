package app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Login extends AbstractController {

    @GetMapping("/login")
    public ModelAndView index() {

        view.setViewName("login.html");
        
        return view;
    }
}
