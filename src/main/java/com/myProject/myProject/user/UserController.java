package com.myProject.myProject.user;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@AllArgsConstructor
@NoArgsConstructor
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping("/users")
    public String showIndex(Model model){

        List<User> listUsers = service.listAll();
        model.addAttribute("listUsers",listUsers);

        return "users";
    }

    @GetMapping("/users/new")
    public String ShowNewUser(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle", "Add New User");

        return "user_form";
    }

    @PostMapping("/users/save")
    public String saveUser(User user, RedirectAttributes ra){
        service.save(user);
        ra.addFlashAttribute("message", "The user has been saved!");

        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        /*try{
            User user = service.get(id);
            model.addAttribute('user',user);
            model.addAttribute("pageTitle", "Edit user (ID = "+id+")");
            return "user_form";
        }catch (UserNotFoundException e) {
            ra.addFlashAttribute("message","The user has been edited.");
            return "redirect:/users";
        }*/
        try {
            User user = service.get(id);
            model.addAttribute("user", user);
            model.addAttribute("pageTitle", "Edit user (ID = "+id+")");
            ra.addFlashAttribute("message","The user has been edited.");
            return "user_form";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message",e.getMessage());
            return "redirect:/users";
        }
    }
    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes ra){

        try {
            service.deleteUser(id);
            ra.addFlashAttribute("message","The user (ID = "+id+") has been deleted.");
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message",e.getMessage());
        }
        return "redirect:/users";

    }

}
















