package onlinebank.Extractors;

import onlinebank.models.Mortgage;
import onlinebank.models.MortgageTerm;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MortgageExtractor implements ResultSetExtractor<List<Mortgage>> {

    @Override
    public List<Mortgage> extractData(ResultSet rs) throws SQLException {
        List<Mortgage> mortgages = new ArrayList<>();
        while (rs.next()) {
            Mortgage mortgage = new Mortgage();
            mortgage.setMortgageSumm(rs.getDouble("mortgageSumm"));
            mortgage.setCurrentMortgageSumm(rs.getDouble("currentMortgageSumm"));
            mortgage.setMortgageTerm(MortgageTerm.valueOf(rs.getString("mortgageTerm")));
            mortgage.setPassportNumber(rs.getInt("passportNumber"));
            mortgages.add(mortgage);
        }
        return mortgages;
    }
}
