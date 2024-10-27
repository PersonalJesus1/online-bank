package onlinebank.controllers;

import jakarta.validation.Valid;
import onlinebank.models.DebitCard;
import onlinebank.services.DebitCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{cardNumber}/edit")
    public String edit(Model model, @PathVariable("cardNumber") String cardNumber) {
        model.addAttribute("debitcard", debitCardService.show(cardNumber));
        return "debitcards/edit";
    }

    @PatchMapping("/{cardNumber}/edit")
    public String updateUserupdateUser(@ModelAttribute("debitcard") @Valid DebitCard debitcard, BindingResult bindingResult,
                                       @PathVariable("cardNumber") String cardNumber) {
        debitCardService.update(cardNumber, debitcard);
        return "redirect:/debitcards";
    }

    @GetMapping("/confirm-delete")
    public String confirmDelete(@RequestParam("cardNumber") String cardNumber, Model model) {
        DebitCard debitcard = debitCardService.show(cardNumber);
        if (debitcard == null) {
            return "debitcards/not-found";
        }
        model.addAttribute("debitcard", debitcard);
        return "debitcards/confirm-delete";
    }

    @DeleteMapping("/{cardNumber}/delete")
    public String delete(@PathVariable("cardNumber") String cardNumber) {
        debitCardService.delete(cardNumber);
        return "redirect:/debitcards";
    }
}