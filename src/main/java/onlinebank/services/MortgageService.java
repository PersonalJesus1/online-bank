package onlinebank.services;

import onlinebank.dao.MortgageDAO;
import onlinebank.models.Mortgage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class MortgageService {
    @Autowired
    private final MortgageDAO mortgageDAO;

    @Autowired
    public MortgageService(MortgageDAO mortgageDAO) {
        this.mortgageDAO = mortgageDAO;
    }

    public List<Mortgage> getAllMortgages() {
        return mortgageDAO.getAllMortgages();
    }
}
