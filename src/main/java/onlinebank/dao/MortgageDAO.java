package onlinebank.dao;

import onlinebank.Extractors.MortgageExtractor;
import onlinebank.models.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MortgageDAO {
    private JdbcTemplate jdbcTemplate;

    public MortgageDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Mortgage> getAllMortgages() {
        return jdbcTemplate.query("select * from mortgage", new MortgageExtractor());
    }

    public void save(Mortgage mortgage) {
        // Insert a new account into table mortgage
        jdbcTemplate.update(
                "INSERT INTO mortgage (mortgagesumm, currentmortgagesumm, mortgageterm, passportnumber) VALUES (?, ?, ?::mortgagetermenum, ?)",
                mortgage.getMortgageSumm(),
                mortgage.getCurrentMortgageSumm(),
                mortgage.getMortgageTerm().name(),
                mortgage.getPassportNumber()
        );

        // Update mortgagelist in table bankuser
        String updateBankUserSql = "UPDATE bankuser " +
                "SET mortgagelist = COALESCE(mortgagelist, '[]'::jsonb) || " +
                "jsonb_build_array(jsonb_build_object(" +
                "'mortgageSumm', ?, " +
                "'currentMortgageSumm', ?, " +
                "'mortgageTerm', ?)) " +
                "WHERE passportnumber = ?";

        jdbcTemplate.update(updateBankUserSql,
                mortgage.getMortgageSumm(),
                mortgage.getCurrentMortgageSumm(),
                mortgage.getMortgageTerm().name(),
                mortgage.getPassportNumber()
        );
    }

    public Mortgage show(int passportNumber, double mortgageSumm) {
        return jdbcTemplate.query("SELECT * FROM mortgage WHERE passportNumber=? AND mortgageSumm=?",
                        new Object[]{passportNumber, mortgageSumm}, new MortgageExtractor())
                .stream().findAny().orElse(null);
    }

    public void update(int passportNumber, double mortgageSumm, Mortgage updatedMortgage) {
        jdbcTemplate.update("UPDATE mortgage SET mortgagesumm = ?, currentmortgagesumm = ?, mortgageterm = ?::mortgagetermenum " +
                        "WHERE passportnumber = ? AND mortgagesumm = ?",
                updatedMortgage.getMortgageSumm(),
                updatedMortgage.getCurrentMortgageSumm(),
                updatedMortgage.getMortgageTerm().name(),
                passportNumber,
                mortgageSumm);
    }

    public void delete(int passportNumber, double mortgageSumm) {
        jdbcTemplate.update("DELETE FROM mortgage WHERE passportNumber=? AND mortgageSumm=?",
                passportNumber, mortgageSumm);
    }
}