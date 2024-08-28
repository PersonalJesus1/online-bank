package onlinebank.controllers;

import onlinebank.services.DebitCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
}
