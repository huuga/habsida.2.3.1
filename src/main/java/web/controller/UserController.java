package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.entity.User;
import web.service.UserServiceImp;

@Controller
public class UserController {

    @Autowired
    UserServiceImp userServiceImp;


    @RequestMapping(value = "/")
    public String showUsers(Model model) {
        model.addAttribute("usersList", userServiceImp.getUsersList());
        return "index";
    }

    @RequestMapping(value = "/add")
    public String addUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "add-user";
    }

    @RequestMapping("/register")
    public String submitForm(@ModelAttribute("user") User user) {
        userServiceImp.addUser(user);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editUser(Model model, @PathVariable("id") String userId) {
        User editableUser = userServiceImp.findUserById(Long.parseLong(userId));
        model.addAttribute("editableUser", editableUser);
        return "user-edit";
    }

    @PostMapping("/edit/{id}")
    public String saveEditedUser(@ModelAttribute("editableUser") User editedUser,
                                 @PathVariable("id") String userId) {
        editedUser.setId(Long.parseLong(userId));
        userServiceImp.updateUser(editedUser);
        return "redirect:/";
    }

    @RequestMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") String userId) {
        userServiceImp.removeUser(Long.parseLong(userId));
        return "redirect:/";
    }
}
