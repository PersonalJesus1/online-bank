package onlinebank.Extractors;

import onlinebank.models.AutoLoan;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AutoloanExtractor implements ResultSetExtractor<List<AutoLoan>> {

    @Override
    public List<AutoLoan> extractData(ResultSet rs) throws SQLException {
        List<AutoLoan> autoloans = new ArrayList<>();
        while (rs.next()) {
            AutoLoan autoLoan = new AutoLoan();
            autoLoan.setMortgageSumm(rs.getDouble("mortgagesumm"));
            autoLoan.setCurrentMortgageSumm(rs.getDouble("currentmortgagesumm"));
            autoLoan.setMortgageMonthsTerm(rs.getInt("mortgagemonthsterm"));
            autoLoan.setPassportNumber(rs.getInt("passportnumber"));
            autoloans.add(autoLoan);
        }
        return autoloans;
    }
}