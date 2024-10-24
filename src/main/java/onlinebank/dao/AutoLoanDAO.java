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

    public void save(AutoLoan autoLoan) {
        // Сначала добавляем новую запись в таблицу autoloan
        jdbcTemplate.update("INSERT INTO autoloan (mortgagesumm, currentmortgagesumm, mortgagemonthsterm, passportnumber) VALUES (?, ?, ?, ?)",
                autoLoan.getMortgageSumm(),
                autoLoan.getCurrentMortgageSumm(),
                autoLoan.getMortgageMonthsTerm(),
                autoLoan.getPassportNumber());

        // Затем обновляем autoloanlist в таблице bankuser
        String updateBankUserSql = "UPDATE bankuser " +
                "SET autoloanlist = COALESCE(autoloanlist, '[]'::jsonb) || " +
                "jsonb_build_array(jsonb_build_object(" +
                "'mortgagesumm', ?, " +
                "'currentmortgagesumm', ?, " +
                "'mortgagemonthsterm', ?)) " +
                "WHERE passportnumber = ?";

        jdbcTemplate.update(updateBankUserSql,
                autoLoan.getMortgageSumm(),
                autoLoan.getCurrentMortgageSumm(),
                autoLoan.getMortgageMonthsTerm(),
                autoLoan.getPassportNumber());
    }

}
