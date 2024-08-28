package onlinebank.dao;

import onlinebank.models.AutoLoan;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AutoLoanDAO {
    private List<AutoLoan> autoLoans;

    {
        autoLoans = new ArrayList<>();
        autoLoans.add(new AutoLoan(85000, 85000, 12, 2585944));
        autoLoans.add(new AutoLoan(120000, 120000, 15, 4185257));
        autoLoans.add(new AutoLoan(100000, 100000, 13, 8591258));
        autoLoans.add(new AutoLoan(115000, 115000, 14, 8529524));
    }

    public List<AutoLoan> getAllAutoloans() {
        return autoLoans;
    }

}
