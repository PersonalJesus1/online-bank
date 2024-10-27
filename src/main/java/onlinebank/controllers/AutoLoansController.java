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
        model.addAttribute("autoloans", autoLoanService.getAllAutoloans());
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

    @GetMapping("/{passportNumber}/{mortgageSumm}/edit")
    public String edit(Model model,
                       @PathVariable("passportNumber") int passportNumber,
                       @PathVariable("mortgageSumm") double mortgageSumm) {
        model.addAttribute("autoloan", autoLoanService.show(passportNumber, mortgageSumm));
        return "autoloans/edit";
    }

    @PatchMapping("/{passportNumber}/{mortgageSumm}/edit")
    public String updateMortgage(@ModelAttribute("autoloan") @Valid AutoLoan autoloan, BindingResult bindingResult,
                                 @PathVariable("passportNumber") int passportNumber,
                                 @PathVariable("mortgageSumm") double mortgageSumm) {
        if (bindingResult.hasErrors()) {
            return "autoloans/edit";
        }
        autoLoanService.update(passportNumber, mortgageSumm, autoloan);
        return "redirect:/autoloans";
    }

    @GetMapping("/confirm-delete")
    public String confirmDelete(@RequestParam("passportNumber") int passportNumber,
                                @RequestParam("mortgageSumm") double mortgageSumm,  Model model) {
        AutoLoan autoloan = autoLoanService.show(passportNumber, mortgageSumm);
        if (autoloan == null) {
            return "autoloans/not-found";
        }
        model.addAttribute("autoloan", autoloan);
        return "autoloans/confirm-delete";
    }

    @DeleteMapping("/{passportNumber}/{mortgageSumm}/delete")
    public String delete(@PathVariable("passportNumber") int passportNumber,
                         @PathVariable("mortgageSumm") double mortgageSumm) {
        autoLoanService.delete(passportNumber, mortgageSumm);
        return "redirect:/autoloans";
    }
}
