package onlinebank.dao;

import onlinebank.Extractors.AutoloanExtractor;
import onlinebank.models.AutoLoan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AutoLoanDAO {

    private JdbcTemplate jdbcTemplate;

    public AutoLoanDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<AutoLoan> getAllAutoloans() {
        return jdbcTemplate.query("select * from autoloan", new AutoloanExtractor());
    }

    public void save(AutoLoan autoloan) {
        // Insert a new account into table autoloan
        jdbcTemplate.update("INSERT INTO autoloan (mortgagesumm, currentmortgagesumm, mortgagemonthsterm, passportnumber) VALUES (?, ?, ?, ?)",
                autoloan.getMortgageSumm(),
                autoloan.getCurrentMortgageSumm(),
                autoloan.getMortgageMonthsTerm(),
                autoloan.getPassportNumber());

        // Update autoloanlist in table bankuser
        String updateBankUserSql = "UPDATE bankuser " +
                "SET autoloanlist = COALESCE(autoloanlist, '[]'::jsonb) || " +
                "jsonb_build_array(jsonb_build_object(" +
                "'mortgagesumm', ?, " +
                "'currentmortgagesumm', ?, " +
                "'mortgagemonthsterm', ?)) " +
                "WHERE passportnumber = ?";

        jdbcTemplate.update(updateBankUserSql,
                autoloan.getMortgageSumm(),
                autoloan.getCurrentMortgageSumm(),
                autoloan.getMortgageMonthsTerm(),
                autoloan.getPassportNumber());
    }

    public AutoLoan show(int passportNumber, double mortgageSumm) {
        return jdbcTemplate.query("SELECT * FROM autoloan WHERE passportNumber=? AND mortgageSumm=?",
                        new Object[]{passportNumber, mortgageSumm}, new AutoloanExtractor())
                .stream().findAny().orElse(null);
    }

    public void update(int passportNumber, double mortgageSumm, AutoLoan updatedAutoLoan) {
        jdbcTemplate.update("UPDATE autoloan SET mortgagesumm = ?, currentmortgagesumm = ?, mortgagemonthsterm = ? " +
                        "WHERE passportnumber = ? AND mortgagesumm = ?",
                updatedAutoLoan.getMortgageSumm(),
                updatedAutoLoan.getCurrentMortgageSumm(),
                updatedAutoLoan.getMortgageMonthsTerm(),
                passportNumber,
                mortgageSumm);
    }

    public void delete(int passportNumber, double mortgageSumm) {
        jdbcTemplate.update("DELETE FROM autoloan WHERE passportNumber=? AND mortgageSumm=?",
                passportNumber, mortgageSumm);
    }
}