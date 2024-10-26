package onlinebank.dao;

import onlinebank.Extractors.AutoloanExtractor;
import onlinebank.Extractors.DebitcardExtractor;
import onlinebank.models.AutoLoan;
import onlinebank.models.DebitCard;
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
        // Сначала добавляем новую запись в таблицу autoloan
        jdbcTemplate.update("INSERT INTO autoloan (mortgagesumm, currentmortgagesumm, mortgagemonthsterm, passportnumber) VALUES (?, ?, ?, ?)",
                autoloan.getMortgageSumm(),
                autoloan.getCurrentMortgageSumm(),
                autoloan.getMortgageMonthsTerm(),
                autoloan.getPassportNumber());

        // Затем обновляем autoloanlist в таблице bankuser
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
    public AutoLoan show(int passportNumber) {
        return jdbcTemplate.query("SELECT * FROM autoloan WHERE passportNumber=?", new Object[]{passportNumber}, new AutoloanExtractor())
                .stream().findAny().orElse(null);
    }
    public void update(int passportNumber, AutoLoan  updatedAutoloan) {
        jdbcTemplate.update("UPDATE autoloan SET mortgageSumm=?, CurrentMortgageSumm=?, mortgageMonthsTerm=?  " +
                        "WHERE passportnumber=?", updatedAutoloan.getMortgageSumm(),
                updatedAutoloan.getCurrentMortgageSumm(), updatedAutoloan.getMortgageMonthsTerm(), passportNumber);
    }

    public void delete(int passportNumber) {
        jdbcTemplate.update("DELETE FROM autoloan WHERE passportNumber=?", passportNumber);
    }
}
