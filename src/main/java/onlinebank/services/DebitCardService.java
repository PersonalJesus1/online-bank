package onlinebank.services;

import onlinebank.dao.DebitCardDAO;
import onlinebank.models.DebitCard;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class DebitCardService {
    private final DebitCardDAO debitCardDAO;

    public DebitCardService(DebitCardDAO debitCardDAO) {
        this.debitCardDAO = debitCardDAO;
    }
    public List<DebitCard> getAllDebitcards() {
        return debitCardDAO.getAllDebitcards();
    }
}
