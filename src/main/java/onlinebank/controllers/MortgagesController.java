package onlinebank.controllers;

import jakarta.validation.Valid;
import onlinebank.models.DebitCard;
import onlinebank.models.Mortgage;
import onlinebank.services.MortgageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mortgages")
public class MortgagesController {

    private final MortgageService mortgageService;

    @Autowired
    public MortgagesController(MortgageService mortgageService) {
        this.mortgageService = mortgageService;
    }

    @GetMapping()
    public String getAllMortgages(Model model) {
        model.addAttribute("mortgages", mortgageService.getAllMortgages());
        return "mortgages/allMortgages";
    }
    @GetMapping("/new")
    public String newMortgage(Model model) {
        model.addAttribute("mortgage", new Mortgage());
        return "mortgages/newMortgage";
    }

    @PostMapping
    public String create(@ModelAttribute("mortgage") @Valid Mortgage mortgage, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "mortgages/newMortgage";
        }
        mortgageService.save(mortgage);
        return "redirect:/mortgages";
    }
}
