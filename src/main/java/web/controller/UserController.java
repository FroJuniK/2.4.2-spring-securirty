package web.controller;

import hiber.model.Role;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.Set;

@Controller
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping(value = "/")
    public String allUsers(ModelMap model) {
        model.addAttribute("users", service.getAllUsers());
        return "users";
    }

    @GetMapping(value = "/add")
    public String addUserForm(ModelMap model) {
        model.addAttribute("roles", service.getAllRoles());
        return "addUser";
    }

    @PostMapping(value = "/add")
    public String addUser(@ModelAttribute User user, @RequestParam("usrRoles") Set<Role> roles) {
        Set<Role> roleSet = new HashSet<>();
        for (Role role : roles) {
            roleSet.add(service.getRoleByName(role.getRole()));
        }
        user.setRoles(roleSet);
        service.addUser(user);
        return "redirect:/";
    }

    @GetMapping(value = "/edit")
    public String editUserForm(@RequestParam Long id, ModelMap model) {
        model.addAttribute("user", service.getUserById(id));
        model.addAttribute("roles", service.getAllRoles());
        return "editUser";
    }

    @PostMapping(value = "/edit")
    public String editUser(@ModelAttribute User user, @RequestParam("usrRoles") Set<Role> roles) {
        Set<Role> roleSet = new HashSet<>();
        for (Role role : roles) {
            roleSet.add(service.getRoleByName(role.getRole()));
        }
        user.setRoles(roleSet);
        service.editUser(user);
        return "redirect:/";
    }

    @GetMapping(value = "/delete")
    public String deleteUser(@RequestParam Long id) {
        service.deleteUser(id);
        return "redirect:/";
    }
}
