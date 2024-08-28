package onlinebank.dao;

import onlinebank.models.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MortgageDAO {
    private List<Mortgage> mortgages;

    {
        mortgages = new ArrayList<>();
        mortgages.add(new Mortgage(250000, 250000, mortgageTerm.FIFTEENYEARS, 5895256));
        mortgages.add(new Mortgage(280000, 280000, mortgageTerm.FIFTEENYEARS, 8532954));
        mortgages.add(new Mortgage(230000, 230000, mortgageTerm.TENYEARS, 7581599));
        mortgages.add(new Mortgage(300000, 300000, mortgageTerm.TWENTYYEARS, 8523965));
    }

    public List<Mortgage> getAllMortgages() {
        return mortgages;
    }
}
