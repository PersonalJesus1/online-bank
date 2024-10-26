package onlinebank.services;

import onlinebank.dao.DebitCardDAO;
import onlinebank.models.AutoLoan;
import onlinebank.models.DebitCard;
import onlinebank.models.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DebitCardService {
    private final DebitCardDAO debitCardDAO;

    public DebitCardService(DebitCardDAO debitCardDAO) {
        this.debitCardDAO = debitCardDAO;
    }

    public List<DebitCard> getAllDebitcards() {
        return debitCardDAO.getAllDebitcards();
    }

    public void save(DebitCard debitCard) {
        debitCardDAO.save(debitCard);

    }
    public void update(String cardNumber, DebitCard updatedDebitcard) {
        debitCardDAO.update(cardNumber, updatedDebitcard);
    }

    public DebitCard show(String cardNumber) {
        return debitCardDAO.show(cardNumber);
    }

    public void delete(String cardNumber) {
        debitCardDAO.delete(cardNumber);
    }

}
