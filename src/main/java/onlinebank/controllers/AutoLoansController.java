package onlinebank.controllers;

import jakarta.validation.Valid;
import onlinebank.models.AutoLoan;
import onlinebank.models.DebitCard;
import onlinebank.services.AutoLoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/autoloans")
public class AutoLoansController {
    private final AutoLoanService autoLoanService;

    @Autowired
    public AutoLoansController(AutoLoanService autoLoanService) {
        this.autoLoanService = autoLoanService;
    }

    @GetMapping()
    public String getAllAutoloans(Model model) {
        model.addAttribute("autoloan", autoLoanService.getAllAutoloans());
        return "autoloans/allAutoloans";
    }
    @GetMapping("/new")
    public String newAutoloan(Model model) {
        model.addAttribute("autoloan", new AutoLoan());
        return "autoloans/newAutoloan";
    }

    @PostMapping
    public String create(@ModelAttribute("autoloan") @Valid AutoLoan autoloan, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "autoloans/newAutoloan";
        }
        autoLoanService.save(autoloan);
        return "redirect:/autoloans";
    }

    @GetMapping("/{passportNumber}/edit")
    public String edit(Model model, @PathVariable("passportNumber") int passportNumber) {
        model.addAttribute("autoloan", autoLoanService.show(passportNumber));
        return "autoloans/edit";
    }

    @PatchMapping("/{passportNumber}/edit")
    public String updateUserupdateUser(@ModelAttribute("autoloan") @Valid AutoLoan autoloan, BindingResult bindingResult,
                                       @PathVariable("passportNumber") int passportNumber) {
        autoLoanService.update(passportNumber, autoloan);
        return "redirect:/autoloans";
    }

    // Контроллер для подтверждения удаления
    @GetMapping("/confirm-delete")
    public String confirmDelete(@RequestParam("passportNumber") int passportNumber, Model model) {
        AutoLoan autoloan = autoLoanService.show(passportNumber);  // Получаем пользователя по номеру паспорта
        if (autoloan == null) {
            return "autoloans/not-found";  // Если пользователь не найден
        }
        model.addAttribute("autoloan", autoloan);
        return "autoloans/confirm-delete";  // Возвращает страницу подтверждения удаления
    }

    @DeleteMapping("/{passportNumber}/delete")
    public String delete(@PathVariable("passportNumber") int passportNumber) {
        autoLoanService.delete(passportNumber);
        return "redirect:/autoloans";
    }
}
