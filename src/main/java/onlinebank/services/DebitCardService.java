package onlinebank.services;

import onlinebank.dao.DebitCardDAO;
import onlinebank.dao.UserDAO;
import onlinebank.models.DebitCard;
import onlinebank.models.DepositRequest;
import onlinebank.models.TransferRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DebitCardService {
    private final DebitCardDAO debitCardDAO;
    private final UserDAO userDAO;

    public DebitCardService(DebitCardDAO debitCardDAO, UserDAO userDAO) {
        this.debitCardDAO = debitCardDAO;
        this.userDAO = userDAO;
    }

    @Transactional(readOnly = true)
    public List<DebitCard> getAllDebitcards() {
        return debitCardDAO.getAllDebitcards();
    }

    @Transactional
    public void save(DebitCard debitCard) {
        if (!userDAO.existsByPassportNumber(debitCard.getPassportNumber())) {
            throw new IllegalArgumentException("No user found with passport number " + debitCard.getPassportNumber());
        }
        debitCardDAO.save(debitCard);  // Правильный вызов через экземпляр DAO
    }

    @Transactional
    public void update(String cardNumber, DebitCard updatedDebitcard) {
        debitCardDAO.update(cardNumber, updatedDebitcard);
    }

    @Transactional(readOnly = true)
    public DebitCard show(String cardNumber, int passportNumber) {
        return debitCardDAO.show(cardNumber, passportNumber);
    }

    @Transactional
    public void delete(String cardNumber, int passportNumber) {
        debitCardDAO.delete(cardNumber, passportNumber);
    }

    @Transactional
    public void transferMoney(TransferRequest transferRequest) {
        DebitCard sender = debitCardDAO.findByCardNumber(transferRequest.getSenderCardNumber());
        DebitCard receiver = debitCardDAO.findByCardNumber(transferRequest.getReceiverCardNumber());
        if (sender == null || receiver == null) {
            throw new IllegalArgumentException("The number of the card of sender or of receiver is wrong.");
        }
        if (sender.getCardBalance() < transferRequest.getAmount()) {
            throw new IllegalArgumentException("There is not enough money on the card.");
        }
        sender.setCardBalance(sender.getCardBalance() - transferRequest.getAmount());
        receiver.setCardBalance(receiver.getCardBalance() + transferRequest.getAmount());
        debitCardDAO.update(sender.getCardNumber(), sender);
        debitCardDAO.update(receiver.getCardNumber(), receiver);
    }

    @Transactional
    public void depositMoney(DepositRequest depositRequest) {
        DebitCard receiver = debitCardDAO.findByCardNumber(depositRequest.getCardNumber());
        if (receiver == null) {
            throw new IllegalArgumentException("The receiver card number is invalid.");
        }
        if (depositRequest.getAmount() <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than zero.");
        }
        receiver.setCardBalance(receiver.getCardBalance() + depositRequest.getAmount());
        debitCardDAO.update(receiver.getCardNumber(), receiver);
    }
    @Transactional(readOnly = true)
    public boolean existsByCardNumber(String cardNumber) {
        return debitCardDAO.existsByCardNumber(cardNumber);
    }
}