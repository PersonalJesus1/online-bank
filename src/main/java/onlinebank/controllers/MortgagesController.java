package onlinebank.controllers;

import jakarta.validation.Valid;
import onlinebank.models.Mortgage;
import onlinebank.services.MortgageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{passportNumber}/{mortgageSumm}/edit")
    public String edit(Model model,
                       @PathVariable("passportNumber") int passportNumber,
                       @PathVariable("mortgageSumm") double mortgageSumm) {
        model.addAttribute("mortgage", mortgageService.show(passportNumber, mortgageSumm));
        return "mortgages/edit";
    }

    @PatchMapping("/{passportNumber}/{mortgageSumm}/edit")
    public String updateMortgage(@ModelAttribute("mortgage") @Valid Mortgage mortgage, BindingResult bindingResult,
                                 @PathVariable("passportNumber") int passportNumber,
                                 @PathVariable("mortgageSumm") double mortgageSumm) {
        if (bindingResult.hasErrors()) {
            return "mortgages/edit";
        }
        mortgageService.update(passportNumber, mortgageSumm, mortgage);
        return "redirect:/mortgages";
    }

    @GetMapping("/confirm-delete")
    public String confirmDelete(@RequestParam("passportNumber") int passportNumber,
                                @RequestParam("mortgageSumm") double mortgageSumm, Model model) {
        Mortgage mortgage = mortgageService.show(passportNumber, mortgageSumm);
        if (mortgage == null) {
            return "mortgages/not-found";
        }
        model.addAttribute("mortgage", mortgage);
        return "mortgages/confirm-delete";
    }

    @DeleteMapping("/{passportNumber}/{mortgageSumm}/delete")
    public String delete(@PathVariable("passportNumber") int passportNumber,
                         @PathVariable("mortgageSumm") double mortgageSumm) {
        mortgageService.delete(passportNumber, mortgageSumm);
        return "redirect:/mortgages";
    }
}