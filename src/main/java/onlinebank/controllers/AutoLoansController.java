package onlinebank.controllers;

import jakarta.validation.Valid;
import onlinebank.models.AutoLoan;
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
}
