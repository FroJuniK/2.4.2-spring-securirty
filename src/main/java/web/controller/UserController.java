package web.controller;

import hiber.model.Role;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService service;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @GetMapping(value = "user")
    public String userPage(@AuthenticationPrincipal User user, ModelMap model) {
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping(value = "admin")
    public String allUsers(ModelMap model) {
        model.addAttribute("users", service.getAllUsers());
        return "users";
    }

    @GetMapping(value = "admin/add")
    public String addUserForm(ModelMap model) {
        model.addAttribute("roles", service.getAllRoles());
        return "addUser";
    }

    @PostMapping(value = "admin/add")
    public String addUser(@ModelAttribute User user, @RequestParam("usrRoles") Set<Role> roles) {
        Set<Role> roleSet = new HashSet<>();
        for (Role role : roles) {
            roleSet.add(service.getRoleByName(role.getRole()));
        }
        user.setRoles(roleSet);
        service.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "admin/edit")
    public String editUserForm(@RequestParam Long id, ModelMap model) {
        model.addAttribute("user", service.getUserById(id));
        model.addAttribute("roles", service.getAllRoles());
        return "editUser";
    }

    @PostMapping(value = "admin/edit")
    public String editUser(@ModelAttribute User user, @RequestParam("usrRoles") Set<Role> roles) {
        Set<Role> roleSet = new HashSet<>();
        for (Role role : roles) {
            roleSet.add(service.getRoleByName(role.getRole()));
        }
        user.setRoles(roleSet);
        service.editUser(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "admin/delete")
    public String deleteUser(@RequestParam Long id) {
        service.deleteUser(id);
        return "redirect:/admin";
    }
}
