package onlinebank.controllers;

import onlinebank.services.AutoLoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
