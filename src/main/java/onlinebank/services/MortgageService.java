package onlinebank.services;

import onlinebank.Extractors.MortgageExtractor;
import onlinebank.dao.MortgageDAO;
import onlinebank.models.DebitCard;
import onlinebank.models.Mortgage;
import onlinebank.models.MortgageTerm;
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

    public void update(int passportNumber, double mortgageSumm, Mortgage updatedMortgage){
        mortgageDAO.update(passportNumber, mortgageSumm, updatedMortgage);
    }

    public Mortgage show(int passportNumber, double mortgageSumm) {
        return mortgageDAO.show(passportNumber, mortgageSumm);
    }


    public void delete(int passportNumber, double mortgageSumm) {
        mortgageDAO.delete(passportNumber, mortgageSumm);
    }
}
