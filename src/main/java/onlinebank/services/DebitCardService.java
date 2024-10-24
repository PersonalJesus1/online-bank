package onlinebank.services;

import onlinebank.dao.DebitCardDAO;
import onlinebank.models.AutoLoan;
import onlinebank.models.DebitCard;
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
}
