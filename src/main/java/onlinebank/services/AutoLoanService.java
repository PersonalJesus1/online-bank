package onlinebank.services;

import onlinebank.dao.AutoLoanDAO;
import onlinebank.models.AutoLoan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutoLoanService {
    @Autowired
    private final AutoLoanDAO autoLoanDAO;

    @Autowired
    public AutoLoanService(AutoLoanDAO autoLoanDAO) {
        this.autoLoanDAO = autoLoanDAO;
    }

    public List<AutoLoan> getAllAutoloans() {
        return autoLoanDAO.getAllAutoloans();
    }

    public void save(AutoLoan autoLoan) {
        autoLoanDAO.save(autoLoan);
    }

    public void update(int passportNumber, double mortgageSumm, AutoLoan updatedMortgage) {
        autoLoanDAO.update(passportNumber, mortgageSumm, updatedMortgage);
    }

    public AutoLoan show(int passportNumber, double mortgageSumm) {
        return autoLoanDAO.show(passportNumber, mortgageSumm);
    }

    public void delete(int passportNumber, double mortgageSumm) {
        autoLoanDAO.delete(passportNumber, mortgageSumm);
    }
}