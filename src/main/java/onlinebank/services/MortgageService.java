package onlinebank.services;

import onlinebank.dao.MortgageDAO;
import onlinebank.models.Mortgage;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class MortgageService {
    private final MortgageDAO mortgageDAO;

    public MortgageService(MortgageDAO mortgageDAO) {
        this.mortgageDAO = mortgageDAO;
    }

    public List<Mortgage> getAllMortgages() {
        return mortgageDAO.getAllMortgages();
    }
}
