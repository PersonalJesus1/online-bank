package onlinebank.controllers;

import jakarta.validation.Valid;
import onlinebank.models.DebitCard;
import onlinebank.models.DepositRequest;
import onlinebank.models.TransferRequest;
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
    public String create(@ModelAttribute("debitcard") @Valid DebitCard debitCard,
                         BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "debitcards/newDebitcard";
        }
        if (debitCardService.existsByCardNumber(debitCard.getCardNumber())) {
            model.addAttribute("error", "Card number already exists: " + debitCard.getCardNumber());
            return "debitcards/newDebitcard";
        }


        try {
            debitCardService.save(debitCard);
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "debitcards/newDebitcard";
        }

        return "redirect:/debitcards";
    }

    @GetMapping("/{passportNumber}/{cardNumber}/edit")
    public String edit(@PathVariable("cardNumber") String cardNumber,
                       @PathVariable("passportNumber") int passportNumber, Model model) {
        model.addAttribute("debitcard", debitCardService.show(cardNumber, passportNumber));
        return "debitcards/edit";
    }

    @PatchMapping("/{passportNumber}/{cardNumber}/edit")
    public String updateСardNumber(@ModelAttribute("debitcard") @Valid DebitCard debitcard, BindingResult bindingResult,
                                   @PathVariable("cardNumber") String cardNumber) {
        if (bindingResult.hasErrors()) {
            return "debitcards/edit";
        }
        debitCardService.update(cardNumber, debitcard);
        return "redirect:/debitcards";
    }

    @GetMapping("/{passportNumber}/{cardNumber}/delete")
    public String confirmDelete(@PathVariable("cardNumber") String cardNumber,
                                @PathVariable("passportNumber") int passportNumber, Model model) {
        DebitCard debitcard = debitCardService.show(cardNumber, passportNumber);
        if (debitcard == null) {
            return "debitcards/not-found";
        }
        model.addAttribute("debitcard", debitcard);
        return "debitcards/confirm-delete";
    }

    @PostMapping("/{passportNumber}/{cardNumber}/delete")
    public String delete(@PathVariable("cardNumber") String cardNumber,
                         @PathVariable("passportNumber") int passportNumber) {
        debitCardService.delete(cardNumber, passportNumber);
        return "redirect:/debitcards";
    }

    @GetMapping("/transfer")
    public String showTransferPage(Model model) {
        model.addAttribute("transferRequest", new TransferRequest());
        return "debitcards/transfer";
    }

    @PatchMapping("/transfer")
    public String processTransfer(@ModelAttribute("transferRequest") TransferRequest transferRequest, Model model) {
        try {
            debitCardService.transferMoney(transferRequest);
            return "redirect:/debitcards";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "debitcards/transfer";
        }
    }

    //deposit
    @GetMapping("{cardNumber}/deposit")
    public String showDepositPage(@PathVariable("cardNumber") String cardNumber, Model model) {
        DepositRequest depositRequest = new DepositRequest();
        depositRequest.setCardNumber(cardNumber);
        model.addAttribute("depositRequest", depositRequest);
        return "debitcards/deposit";
    }

    @PatchMapping("{cardNumber}/deposit")
    public String processDeposit(@ModelAttribute("depositRequest") DepositRequest depositRequest,
                                 @PathVariable("cardNumber") String cardNumber, Model model) {
        try {
            depositRequest.setCardNumber(cardNumber);
            debitCardService.depositMoney(depositRequest);
            return "redirect:/debitcards";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "debitcards/";
        }
    }
}