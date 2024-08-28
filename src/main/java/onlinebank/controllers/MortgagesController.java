package onlinebank.controllers;

import onlinebank.services.MortgageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
}
