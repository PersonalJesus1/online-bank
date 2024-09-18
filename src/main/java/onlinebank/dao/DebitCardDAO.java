package onlinebank.dao;

import onlinebank.models.*;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DebitCardDAO {

    private JdbcTemplate jdbcTemplate;

    public DebitCardDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }

    public List<DebitCard> getAllDebitcards() {
        return jdbcTemplate.query("select * from debitcard", new BeanPropertyRowMapper<>(DebitCard.class));
    }
}
