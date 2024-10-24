package onlinebank.controllers;

import jakarta.validation.Valid;
import onlinebank.models.DebitCard;
import onlinebank.services.DebitCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/debitcards")
public class DebitCardsController {
    private final DebitCardService debitCardService;

    @Autowired
    public DebitCardsController(DebitCardService debitCardService) {
        this.debitCardService = debitCardService;
    }

    @GetMapping()
    public String getAllDebitcards(Model model) {
        model.addAttribute("debitcards", debitCardService.getAllDebitcards());
        return "debitcards/allDebitcards";
    }
    @GetMapping("/new")
    public String newDebitcard(Model model) {
        model.addAttribute("debitcard", new DebitCard());
        return "debitcards/newDebitcard";
    }

    @PostMapping
    public String create(@ModelAttribute("debitcard") @Valid DebitCard debitcard, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "debitcards/newDebitcard";
        }
        debitCardService.save(debitcard);
        return "redirect:/debitcards";
    }
}
