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
            return "users/newUser";
        }
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("/{passportNumber}/edit")
    public String edit(Model model, @PathVariable("passportNumber") int passportNumber) {
        model.addAttribute("user", userService.show(passportNumber));
        return "users/edit";
    }

    @PatchMapping("/{passportNumber}/edit")
    public String updateUserupdateUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                                       @PathVariable("passportNumber") int passportNumber) {
        userService.update(passportNumber, user);
        return "redirect:/users";
    }

    // Контроллер для подтверждения удаления
    @GetMapping("/confirm-delete")
    public String confirmDelete(@RequestParam("passportNumber") int passportNumber, Model model) {
        User user = userService.show(passportNumber);  // Получаем пользователя по номеру паспорта
        if (user == null) {
            return "users/not-found";  // Если пользователь не найден
        }
        model.addAttribute("user", user);
        return "users/confirm-delete";  // Возвращает страницу подтверждения удаления
    }

    @DeleteMapping("/{passportNumber}/delete")
    public String delete(@PathVariable("passportNumber") int passportNumber) {
        userService.delete(passportNumber);
        return "redirect:/users";
    }
}
