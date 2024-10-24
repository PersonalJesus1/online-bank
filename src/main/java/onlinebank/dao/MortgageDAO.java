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
        // Сначала добавляем новую запись в таблицу mortgage
        String sql = "INSERT INTO mortgage (mortgagesumm, currentmortgagesumm, mortgageterm, passportnumber) VALUES (?, ?, CAST(? AS mortgagetermenum), ?)";
        jdbcTemplate.update(sql,
                mortgage.getMortgageSumm(),
                mortgage.getCurrentMortgageSumm(),
                mortgage.getMortgageTerm().name(),  // Преобразуем enum в строку
                mortgage.getPassportNumber());

        // Затем обновляем mortgagelist в таблице bankuser
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
                mortgage.getMortgageTerm().name(),  // Преобразуем enum в строку
                mortgage.getPassportNumber());
    }
}
