package onlinebank.services;

import onlinebank.dao.MortgageDAO;
import onlinebank.models.DebitCard;
import onlinebank.models.Mortgage;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MortgageService {
    private final MortgageDAO mortgageDAO;

    public MortgageService(MortgageDAO mortgageDAO) {
        this.mortgageDAO = mortgageDAO;
    }

    public List<Mortgage> getAllMortgages() {
        return mortgageDAO.getAllMortgages();
    }

    public void save(Mortgage mortgage) {
        mortgageDAO.save(mortgage);

    }
}
