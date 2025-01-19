package onlinebank.services;

import onlinebank.dao.MortgageDAO;
import onlinebank.dao.UserDAO;
import onlinebank.models.Mortgage;
import onlinebank.models.MortgagePayment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MortgageService {

    @Autowired
    private final MortgageDAO mortgageDAO;
    @Autowired
    private final UserDAO userDAO;

    public MortgageService(MortgageDAO mortgageDAO, UserDAO userDAO) {
        this.mortgageDAO = mortgageDAO;
        this.userDAO = userDAO;
    }

    @Transactional(readOnly = true)
    public List<Mortgage> getAllMortgages() {
        return mortgageDAO.getAllMortgages();
    }

    @Transactional
    public void save(Mortgage mortgage) {
        if (!userDAO.existsByPassportNumber(mortgage.getPassportNumber())) {
            throw new IllegalArgumentException("No user found with passport number " + mortgage.getPassportNumber());
        }
        int totalLoansAndMortgages = userDAO.countLoansAndMortgagesByPassportNumber(mortgage.getPassportNumber());
        if (totalLoansAndMortgages >= 3) {
            throw new IllegalArgumentException("The user already has too many loans or mortgages.");
        }
        mortgageDAO.save(mortgage);
    }

    @Transactional
    public void update(int passportNumber, double mortgageSumm, Mortgage updatedMortgage) {
        mortgageDAO.update(passportNumber, mortgageSumm, updatedMortgage);
    }

    @Transactional(readOnly = true)
    public Mortgage show(int passportNumber, double mortgageSumm) {
        return mortgageDAO.show(passportNumber, mortgageSumm);
    }

    @Transactional
    public void delete(int passportNumber, double mortgageSumm) {
        mortgageDAO.delete(passportNumber, mortgageSumm);
    }

    @Transactional
    public void makePayment(MortgagePayment mortgagePayment) {
        Mortgage mortgage = mortgageDAO.findById(mortgagePayment.getId());
        if (mortgagePayment == null) {
            throw new IllegalArgumentException("This mortgagePayment doesn't exist");
        }
        if (mortgagePayment.getAmount() <= 0) {
            throw new IllegalArgumentException("Payment amount must be greater than zero");
        }
        if (mortgage.getCurrentMortgageSumm() - mortgagePayment.getAmount() < 0) {
            throw new IllegalArgumentException("Payment exceeds the remaining loan balance");
        }
        // Update the rest of autoloan
        double newBalance = mortgage.getCurrentMortgageSumm() - mortgagePayment.getAmount();
        if (newBalance <= 0) {
            // if there is no loan - delete it
            mortgageDAO.delete(mortgage.getId());
        } else {
            // or update the rest
            mortgage.setCurrentMortgageSumm(newBalance);
            mortgageDAO.update(mortgage.getId(), mortgage);
        }
    }
    @Transactional(readOnly = true)
    public boolean existsByPassportNumber(int passportNumber) {
        return mortgageDAO.existsByPassportNumber(passportNumber);
    }
}