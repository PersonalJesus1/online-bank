package onlinebank.dao;

import onlinebank.models.AutoLoan;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AutoLoanDAO {

    private JdbcTemplate jdbcTemplate;

    public AutoLoanDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }

    public List<AutoLoan> getAllAutoloans() {
        return jdbcTemplate.query("select * from autoloan", new BeanPropertyRowMapper<>(AutoLoan.class));
    }

}
