package com.example.upproject.Controllers;


import com.example.upproject.DAO.RoleDAO;
import com.example.upproject.DAO.UserDAO;
import com.example.upproject.Entities.User;
import com.example.upproject.Entities.Role;

import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;

@Controller
public class UserController {
    @Autowired
    UserDAO userDAO;
    @Autowired
    RoleDAO roleDAO;





    @GetMapping("/")
    public String index(Model model) throws SQLException {
        model.addAttribute("roles",roleDAO.findAll());
        model.addAttribute("users",userDAO.findAll());
        return "index";
    }
    @GetMapping("/delete")
    public String delete(@RequestParam(name = "id") String id,@RequestParam(name = "entity") String entity) throws SQLException {
        switch (entity){
            case "user":
                userDAO.deleteById(Long.parseLong(id));
                break;
            case "role":
                roleDAO.deleteById(Long.parseLong(id));
                break;
        }
        return "redirect:/";
    }
    @GetMapping("/add")
    public String addGet(@RequestParam(name = "entity") String entity ,Model model) {
        model.addAttribute("entity",entity);
        switch (entity){
            case "user":
                model.addAttribute("roles",roleDAO.findAll());
                break;
            case "role":
                model.addAttribute("users",userDAO.findAll());
                break;
        }
        return "add";
    }
    @PostMapping("/addRole")
    public String addRole(@RequestParam(name = "title") String title) {
        Role role = new Role(title);
        roleDAO.save(role);
        return "redirect:/";
    }
    @PostMapping("/addUser")
    public String addUser(@RequestParam(name = "first_name") String first_name,@RequestParam(name = "last_name") String last_name,@RequestParam(name = "email") String email,@RequestParam(name = "role") String role) {
        User user = new User(first_name,last_name,email);
        user.getRoles().add(roleDAO.findOne(Long.parseLong(role)));
        userDAO.save(user);
        return "redirect:/";
    }
    @GetMapping("/addRoleToUser")
    public String addRoleToUser(@RequestParam(name = "id") String id) {
        return "add-role-to-user";
    }
    @GetMapping("/edit")
    public String edit(@RequestParam(name = "entity") String entity,@RequestParam(name = "id") String id, Model model) {
        model.addAttribute("entity",entity);
        switch (entity){
            case "user":
                model.addAttribute("user",userDAO.findOne(Long.parseLong(id)));
                model.addAttribute("roles",roleDAO.findAll());
                break;
            case "role":
                model.addAttribute("role",roleDAO.findOne(Long.parseLong(id)));
                break;

        }
        return "update";
    }
    @PostMapping("/editUser")
    public String editUser(@RequestParam(name = "first_name") String first_name,@RequestParam(name = "last_name") String last_name,@RequestParam(name = "email") String email,@RequestParam(name = "role") String role,@RequestParam(name = "id") String id, Model model) {
        User user = userDAO.findOne(Long.parseLong(id));
        user.setFirst_name(first_name);
        user.setLast_name(last_name);
        user.setEmail(email);
        Role role1 = roleDAO.findOne(Long.parseLong(role));
        user.getRoles().clear();
        user.getRoles().add(role1);
        userDAO.update(user);
        return "redirect:/";
    }
    @PostMapping("/editRole")
    public String editRole(@RequestParam(name = "title") String title,@RequestParam(name = "id") String id,Model model) {
        Role role = roleDAO.findOne(Long.parseLong(id));
        role.setTitle(title);
        roleDAO.update(role);
        return "redirect:/";
    }
}
