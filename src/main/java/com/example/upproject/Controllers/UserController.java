package com.example.upproject.Controllers;

import com.example.upproject.Config.MainConfig;
import com.example.upproject.DataBase.BaseConnection;
import com.example.upproject.DataBase.BaseInteractions;
import com.example.upproject.User.Position;
import com.example.upproject.User.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.Locale;

@Controller
public class UserController {


    @GetMapping("/")
    public String index(Model model) throws SQLException {
        model.addAttribute("users", this.getExecutant().getUsers());

        return "index";
    }

    @GetMapping("/signup")
    public String showSignUpForm() {
        return "add-user";
    }

    @PostMapping("/adduser")
    public String addUser(@RequestParam(name = "name") String name ,
                          @RequestParam(name = "position") String position,
                          Model model) throws SQLException {
        System.out.println(position.toUpperCase(Locale.ROOT));
        this.getExecutant().addData(String.format("(%s, '%s')",Position.valueOf(position.toUpperCase(Locale.ROOT)).ordinal(),name) ,"users( role_id , name)" );

        model.addAttribute("users", this.getExecutant().getUsers());
        return "index";
    }



    @GetMapping("/edit")
    public String editUser(@RequestParam(name = "id") String id ,
                           @RequestParam(name = "position") String position,
                           @RequestParam(name = "name") String name,
                           Model model) {
        model.addAttribute("id", id);
        model.addAttribute("position", position);
        model.addAttribute("name", name);

        return "update-user";
    }
    @PostMapping("/edit")
    public String saveUser(@RequestParam(name = "id") String id ,
                           @RequestParam(name = "position") String position,
                           @RequestParam(name = "name") String name,

                           Model model) throws SQLException {
        User user = new User(name,Integer.parseInt(id), Position.valueOf(position));
        this.getExecutant().updataData(user);


        model.addAttribute("users", this.getExecutant().getUsers());
        return "index";
    }

    @GetMapping("/delete")
    public String editUser(@RequestParam(name = "id") String id ,Model model) throws SQLException {
        this.getExecutant().deleteData(Integer.parseInt(id));
        model.addAttribute("users", this.getExecutant().getUsers());
        return "index";
    }


    public BaseInteractions getExecutant(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
        BaseInteractions executant = context.getBean("executant" , BaseInteractions.class);
        context.close();
        return executant;
    }

}
