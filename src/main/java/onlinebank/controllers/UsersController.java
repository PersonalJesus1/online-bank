package onlinebank.controllers;

import jakarta.validation.Valid;
import onlinebank.models.User;
import onlinebank.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController {
    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        users.forEach(user -> {
            user.setMortgageList(userService.showMortgages(user.getPassportNumber()));
            user.setAutoLoanList(userService.showAutoLoans(user.getPassportNumber()));
        });
        model.addAttribute("users", users);
        return "users/allUsers";
    }


    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "users/newUser";
    }

    @PostMapping
    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
            return "users/newUser";
        }
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("/{passportNumber}/edit")
    public String edit(Model model, @PathVariable("passportNumber") int passportNumber) {
        User user = userService.show(passportNumber);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        model.addAttribute("user", user);
        return "users/edit";
    }

    @PatchMapping("/{passportNumber}/edit")
    public String updateUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                             @PathVariable("passportNumber") int passportNumber) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
            return "users/edit";
        }
        userService.update(passportNumber, user);
        return "redirect:/users";
    }

    @GetMapping("/{passportNumber}/delete")
    public String confirmDelete(@PathVariable("passportNumber") int passportNumber, Model model) {
        User user = userService.show(passportNumber);
        if (user == null) {
            return "users/not-found";
        }
        model.addAttribute("user", user);
        return "users/confirm-delete";
    }

    @PostMapping("/{passportNumber}/delete")
    public String delete(@PathVariable("passportNumber") int passportNumber) {
        userService.delete(passportNumber);
        return "redirect:/users";
    }
}