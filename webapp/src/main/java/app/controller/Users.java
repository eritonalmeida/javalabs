package app.controller;

import java.util.List;
import java.util.Map;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import app.model.entity.UserEntity;
import app.model.repository.UserRepository;
import app.model.service.ValidatorService;
import app.model.service.ResourceBundleService;

@Controller
public class Users extends AbstractController {

    private final UserRepository repository;

    public Users() {
        repository = new UserRepository();
    }

    @GetMapping("/users")
    public ModelAndView index() {

        List<UserEntity> users = repository.findAll();

        view.addObject("users", users);
        view.setViewName("users/list.html");

        return view;
    }

    @GetMapping("/users/create")
    public ModelAndView create() {

        view.addObject("user", new UserEntity());
        view.setViewName("users/form.html");

        return view;
    }

    @GetMapping("/users/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Integer id) {

        UserEntity user = repository.findById(id);

        if (user == null) {
            view.setViewName("redirect:/users");
            return view;
        }

        view.addObject("user", user);
        view.setViewName("users/form.html");

        return view;
    }

    @PostMapping(value = "/users/save", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String save(@RequestParam(name = "id", required = false) Integer id, @RequestParam Map<String, String> form) {

        if (id != null) {
            return this.update(id, form);
        }

        UserEntity user = new UserEntity();

        this.fill(user, form);

        ValidatorService<UserEntity> validator = new ValidatorService();

        validator.process(user);
        if (validator.hasError()) {
            return this.jsonError(validator.getError());
        }

        UserEntity existslogin = repository.findByLogin(form.get("login"));
        if (existslogin != null) {
            String message = ResourceBundleService.getValidationMessage("user.login.exists");

            return this.jsonError(message);
        }

        user.setCreatedAt(new Date());

        repository.persist(user);

        String message = ResourceBundleService.getString("UsersMessages", "user.created");
        return this.jsonOk(message);
    }

    @GetMapping("/users/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Integer id) {

        UserEntity user = repository.findById(id);

        if (user != null) {
            repository.remove(user);
        }

        view.setViewName("redirect:/users");

        return view;
    }

    private String update(Integer id, Map<String, String> form) {

        UserEntity user = repository.findById(id);
        if (user == null) {
            String message = ResourceBundleService.getString("UsersMessages", "user.notfound");
            return this.jsonError(message);
        }

        this.fill(user, form);

        ValidatorService<UserEntity> validator = new ValidatorService();

        validator.process(user);
        if (validator.hasError()) {
            return this.jsonError(validator.getError());
        }

        repository.persist(user);
        
        String message = ResourceBundleService.getString("UsersMessages", "user.updated");
        return this.jsonOk(message);
    }

    private void fill(UserEntity user, Map<String, String> form) {

        user.setName(form.get("name"));
        user.setLogin(form.get("login"));
        user.setPassword(form.get("password"));
        user.setRoles(form.get("roles"));
        user.setStatus(form.get("status"));

        user.setUpdatedAt(new Date());
    }
}
