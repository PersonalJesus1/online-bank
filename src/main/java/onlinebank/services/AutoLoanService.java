
package onlinebank.services;

import onlinebank.dao.AutoLoanDAO;
import onlinebank.models.AutoLoan;
import onlinebank.models.DebitCard;
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
    public void update(int passportNumber, AutoLoan updatedAutoloan) {
        autoLoanDAO.update( passportNumber, updatedAutoloan);
    }

    public AutoLoan show(int passportNumber) {
        return autoLoanDAO.show(passportNumber);
    }

    public void delete(int passportNumber) {
        autoLoanDAO.delete(passportNumber);
    }
}
