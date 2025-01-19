package onlinebank.services;

import onlinebank.dao.AutoLoanDAO;
import onlinebank.dao.UserDAO;
import onlinebank.models.AutoLoan;
import onlinebank.models.AutoloanPayment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AutoLoanService {
    @Autowired
    private final AutoLoanDAO autoLoanDAO;
    @Autowired
    private final UserDAO userDAO;

    public AutoLoanService(AutoLoanDAO autoLoanDAO, UserDAO userDAO) {
        this.autoLoanDAO = autoLoanDAO;
        this.userDAO = userDAO;
    }

    @Transactional(readOnly = true)
    public List<AutoLoan> getAllAutoloans() {
        return autoLoanDAO.getAllAutoloans();
    }

       @Transactional
    public void save(AutoLoan autoloan) {
        if (!userDAO.existsByPassportNumber(autoloan.getPassportNumber())) {
            throw new IllegalArgumentException("No user found with passport number " + autoloan.getPassportNumber());
        }
        int totalLoansAndMortgages = userDAO.countLoansAndMortgagesByPassportNumber(autoloan.getPassportNumber());
        if (totalLoansAndMortgages >= 3) {
            throw new IllegalArgumentException("The user already has too many loans or mortgages.");
        }
        autoLoanDAO.save(autoloan);
    }

    @Transactional
    public void update(int passportNumber, double mortgageSumm, AutoLoan updatedMortgage) {
        autoLoanDAO.update(passportNumber, mortgageSumm, updatedMortgage);
    }

    @Transactional(readOnly = true)
    public AutoLoan show(int passportNumber, double mortgageSumm) {
        return autoLoanDAO.show(passportNumber, mortgageSumm);
    }

    @Transactional
    public void delete(int passportNumber, double mortgageSumm) {
        autoLoanDAO.delete(passportNumber, mortgageSumm);
    }

    @Transactional
    public void makePayment(AutoloanPayment autoloanPayment) {
        AutoLoan autoLoan = autoLoanDAO.findById(autoloanPayment.getId());
        if (autoLoan == null) {
            throw new IllegalArgumentException("This autoloan doesn't exist");
        }
        if (autoloanPayment.getAmount() <= 0) {
            throw new IllegalArgumentException("Payment amount must be greater than zero");
        }
        if (autoLoan.getCurrentMortgageSumm() - autoloanPayment.getAmount() < 0) {
            throw new IllegalArgumentException("Payment exceeds the remaining loan balance");
        }
        // Update the rest of autoloan
        double newBalance = autoLoan.getCurrentMortgageSumm() - autoloanPayment.getAmount();
        if (newBalance <= 0) {
            // if there is no loan - delete it
            autoLoanDAO.delete(autoLoan.getId());
        } else {
            // or update the rest
            autoLoan.setCurrentMortgageSumm(newBalance);
            autoLoanDAO.update(autoLoan.getId(), autoLoan);
        }
    }
}