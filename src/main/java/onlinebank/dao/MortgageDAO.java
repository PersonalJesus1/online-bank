package onlinebank.dao;

import onlinebank.models.*;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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
         return jdbcTemplate.query("select * from mortgage", new BeanPropertyRowMapper<>(Mortgage.class));

    }
}
