package onlinebank.services;

import onlinebank.dao.AutoLoanDAO;
import onlinebank.models.AutoLoan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
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
}
